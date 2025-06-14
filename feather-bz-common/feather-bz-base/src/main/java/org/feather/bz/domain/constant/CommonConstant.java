package org.feather.bz.domain.constant;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.constant
 * @className: CommonConstant
 * @author: feather
 * @description:
 * @since: 2025-04-23 21:28
 * @version: 1.0
 */
public interface CommonConstant {





        String SUCCESS_MSG = "success";

        int SUCCESS_CODE = 200;

        /**
         * 默认错误码
         */
        int DEFAULT_ERROR_CODE = -1;

        String CAPTCHA_KEY_PREFIX="feather:captcha";

        /**
         * 帖子点赞缓存key前缀
         */
        String CAPTCHA_LIKE_PREFIX="post:like";

}
