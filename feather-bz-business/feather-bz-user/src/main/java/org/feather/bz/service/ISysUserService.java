package org.feather.bz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.catalina.User;
import org.feather.bz.domain.entity.SysUser;
import org.feather.bz.domain.request.AddUserRequest;
import org.feather.bz.domain.request.LoginRequest;
import org.feather.bz.domain.vo.LoginVO;
import org.feather.bz.domain.vo.UserVo;

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
}
