package org.feather.bz.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.feather.bz.SlidingWindowRateLimiter;
import org.feather.bz.domain.constant.CommonConstant;
import org.feather.bz.domain.constant.UserConstant;
import org.feather.bz.domain.entity.SysUser;
import org.feather.bz.domain.enums.BaseEnum;
import org.feather.bz.domain.enums.BizCodeEnum;
import org.feather.bz.domain.enums.SexEnum;
import org.feather.bz.domain.request.AddUserRequest;
import org.feather.bz.domain.request.LoginRequest;
import org.feather.bz.domain.vo.LoginVO;
import org.feather.bz.domain.vo.UserVo;
import org.feather.bz.exception.BizException;
import org.feather.bz.mapper.SysUserMapper;
import org.feather.bz.service.ISysUserService;
import org.feather.bz.utils.MD5Utils;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.feather.bz.domain.enums.BizCodeEnum.SEND_NOTICE_DUPLICATED;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.service.impl
 * @className: SysUserServiceImpl
 * @author: feather
 * @description:
 * @since: 2025-04-23 17:50
 * @version: 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final RedissonClient redissonClient;
    private RBloomFilter<String> userNameBloomFilter;

    private  final SysUserMapper userMapper;

    private final SlidingWindowRateLimiter slidingWindowRateLimiter;
    private final StringRedisTemplate stringRedisTemplate;



    /**
     * 服务启动时初始化布隆过滤器
     */
    @PostConstruct
    public void initBloomFilter() {
        log.info("初始化 Redis 布隆过滤器");

        userNameBloomFilter = redissonClient.getBloomFilter(UserConstant.USERNAME_BLOOM_FILTER_KEY);
        // 初始化布隆过滤器配置，注意：只有第一次需要init，以后存在就不要init
        if (!userNameBloomFilter.isExists()) {
            userNameBloomFilter.tryInit(100000L, 0.01); // 预期10万数据，误判率1%

            List<String> usernames = userMapper.getAllUsernames();
            if (usernames != null && !usernames.isEmpty()) {
                usernames.forEach(userNameBloomFilter::add);
                log.info("布隆过滤器初始化完成，导入用户数量：{}", usernames.size());
            }
        }
    }
    @Override
    public Boolean register(AddUserRequest request) {
        String username = request.getUsername();
        Assert.isFalse(userNameExist(username),()->new BizException(BizCodeEnum.USER_NAME_EXIST));
        SysUser sysUser=new SysUser();
        String salt = RandomUtil.randomString(8);
        sysUser.setSalt(salt);
        BeanUtils.copyProperties(request,sysUser);
        try {
            sysUser.setPassword(MD5Utils.getMD5Str(request.getPassword().concat(salt)));
        } catch (Exception e) {
            throw new BizException("用户加密异常");
        }
        // 保存用户后，将用户名加入布隆过滤器
        boolean saveSuccess = this.save(sysUser);
        if (saveSuccess && userNameBloomFilter != null) {
            userNameBloomFilter.add(username);
            log.info("注册成功,【{}】写入布隆过滤",username);
        }
        return true;
    }

    @Override
    public Boolean sendSmsCaptcha(String phone) {
        Boolean access = slidingWindowRateLimiter.tryAcquire(phone, 1, 60);
        Assert.isTrue(access,()->new BizException(SEND_NOTICE_DUPLICATED));

        // 生成验证码
        String captcha = RandomUtil.randomNumbers(6);
        log.info("手机号:[{}]发送验证码：[{}]",phone, captcha);

        // 验证码存入Redis
        stringRedisTemplate.opsForValue().set(CommonConstant.CAPTCHA_KEY_PREFIX + phone, captcha, 5, TimeUnit.MINUTES);
        return  true;
    }

    @Cached(name = ":user:cache:id:", cacheType = CacheType.BOTH, key = "#userId", cacheNullValue = true)
    @CacheRefresh(refresh = 60, timeUnit = TimeUnit.MINUTES)
    @Override
    public UserVo getUserInfo(Long userId) {
        SysUser sysUser =  this.getById(userId);
        Assert.notNull(sysUser,()->new BizException(BizCodeEnum.ACCOUNT_UNREGISTER));
        UserVo userVo=new UserVo();
        BeanUtils.copyProperties(sysUser,userVo);
        userVo.setSexText(BaseEnum.getByCode(userVo.getSex(),SexEnum.class).getMsg());
        return userVo;
    }
    @Cached(name = ":user:cache:userName:", cacheType = CacheType.BOTH, key = "#userName", cacheNullValue = true)
    @CacheRefresh(refresh = 60, timeUnit = TimeUnit.MINUTES)
    @Override
    public UserVo getUserInfo(String userName) {
        SysUser sysUser =  userMapper.getUserInfo(userName);
        Assert.notNull(sysUser,()->new BizException(BizCodeEnum.ACCOUNT_UNREGISTER));
        UserVo userVo=new UserVo();
        BeanUtils.copyProperties(sysUser,userVo);
        userVo.setSexText(BaseEnum.getByCode(userVo.getSex(),SexEnum.class).getMsg());
        return userVo;
    }

    @Override
    public LoginVO login(LoginRequest request) {
        String userName = request.getUserName();
        String smsCaptcha = request.getSmsCaptcha();
        String cachedCode = stringRedisTemplate.opsForValue().get(CommonConstant.CAPTCHA_KEY_PREFIX + userName);
          if (StringUtils.isBlank(cachedCode) ||!StringUtils.equalsIgnoreCase(cachedCode,smsCaptcha)){
              throw new BizException(BizCodeEnum.VERIFICATION_CODE_WRONG);
          }
        UserVo userInfo = getUserInfo(userName);
        //登录
        StpUtil.login(userInfo.getId(), new SaLoginModel().setIsLastingCookie(request.getRememberMe())
                .setTimeout(UserConstant.DEFAULT_LOGIN_SESSION_TIMEOUT));
        StpUtil.getTokenSession().set(String.valueOf(userInfo.getId()), userInfo);
        log.info("用户[{}]登录成功",userName);
        return new LoginVO(userInfo);

    }


    public boolean userNameExist(String username) {
        // 1. 如果布隆过滤器不可用，直接查数据库
        if (userNameBloomFilter == null) {
            log.warn("布隆过滤器未启用，直接查询数据库");
            return userMapper.userNameExist(username) > 0;
        }

        // 2. 布隆过滤器判断
        boolean mightExist = userNameBloomFilter.contains(username);

        if (!mightExist) {
            log.info("布隆过滤器启用，用户名【{}】布隆过滤器判断不存在,直接返回", username);
            return false;
        }

        // 3. 布隆过滤器可能存在，再查询数据库确认
        log.info("布隆过滤器启用，用户名【{}】可能存在，查询数据库二次确认", username);
        return userMapper.userNameExist(username) > 0;
    }

    @Override
    public Boolean likePost(Long postId) {
        String key = CommonConstant.CAPTCHA_LIKE_PREFIX + postId;
        // 使用 StringRedisTemplate 的原生方法
        return stringRedisTemplate.opsForZSet().add(
                key,
                StpUtil.getLoginIdAsString(),
                System.currentTimeMillis()
        );
    }

    @Override
    public Boolean unLikePost(Long postId) {
        String key = CommonConstant.CAPTCHA_LIKE_PREFIX + postId;
        Long result = stringRedisTemplate.opsForZSet().remove(
                key,
                StpUtil.getLoginIdAsString()
        );
        return result != null && result > 0;
    }

    @Override
    public List<String> getLikes(Long postId) {
        String key = CommonConstant.CAPTCHA_LIKE_PREFIX + postId;
        Set<String> userIds = stringRedisTemplate.opsForZSet().reverseRange(key, 0, -1);
        return userIds == null ? Collections.emptyList() : new ArrayList<>(userIds);
    }
}
