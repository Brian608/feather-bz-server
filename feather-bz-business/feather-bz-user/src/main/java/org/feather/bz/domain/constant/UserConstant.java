package org.feather.bz.domain.constant;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.entity.constant
 * @className: UserConstant
 * @author: feather
 * @description:
 * @since: 2025-04-27 16:29
 * @version: 1.0
 */
public interface UserConstant {
    String USERNAME_BLOOM_FILTER_KEY = "feather:bz:user:username:bloomfilter";

    /**
     * 默认登录超时时间：7天
     */
   Integer DEFAULT_LOGIN_SESSION_TIMEOUT = 60 * 60 * 24 * 7;
}
