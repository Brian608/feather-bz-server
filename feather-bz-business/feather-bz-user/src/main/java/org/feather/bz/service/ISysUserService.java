package org.feather.bz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.feather.bz.domain.entity.SysUser;
import org.feather.bz.domain.entity.request.AddUserRequest;

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
}
