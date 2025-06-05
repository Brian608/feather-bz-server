package org.feather.bz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.feather.bz.domain.entity.SysUser;
import org.feather.bz.domain.request.AddUserRequest;
import org.feather.bz.domain.request.LoginRequest;
import org.feather.bz.domain.vo.LoginVO;
import org.feather.bz.domain.vo.UserVo;

import java.util.List;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.service
 * @className: ISysUserService
 * @author: feather
 * @description:
 * @since: 2025-04-23 17:50
 * @version: 1.0
 */

public interface ISysUserService extends IService<SysUser> {

    /**
     * description: 注册用户
     * @param request
     * @return {@link Boolean}
     * @author: feather
     * @since: 2025-04-27 14:56
     **/
    Boolean register(AddUserRequest request);


    /**
     * description: 发送验证码
     * @param phone
     * @return {@link Boolean}
     * @author: feather
     * @since: 2025-04-28 9:19
     **/
    Boolean sendSmsCaptcha(String phone);


    /**
     * description: 用户信息
     * @param userId
     * @return {@link UserVo}
     * @author: feather
     * @since: 2025-04-28 14:30
     **/
    UserVo getUserInfo(Long userId);

    /**
     * description: 用户名查询用户信息
     * @param userName
     * @return {@link UserVo}
     * @author: feather
     * @since: 2025-05-07 17:57
     **/
    UserVo  getUserInfo(String userName);

    /**
     * description: 登录
     * @param request
     * @return {@link LoginVO}
     * @author: feather
     * @since: 2025-05-07 17:24
     **/
    LoginVO login(LoginRequest request);

    /**
     * description: 点赞
     * @param postId
     * @return
     * @author: feather
     * @since: 2025-06-04 16:34
     **/
    Boolean likePost(Long postId);

    /**
     * description: 取消点赞
     * @param postId
     * @return
     * @author: feather
     * @since: 2025-06-04 16:34
     **/
    Boolean unLikePost(Long postId);

    /**
     * description: 点赞列表
     * @param postId
     * @return {@link List< Long>}
     * @author: feather
     * @since: 2025-06-04 17:04
     **/

    List<String>  getLikes(Long postId);
}
