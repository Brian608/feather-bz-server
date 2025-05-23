package org.feather.bz.web;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.feather.bz.domain.enums.BizCodeEnum;
import org.feather.bz.exception.BizException;
import org.springframework.web.servlet.HandlerInterceptor;
/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.web
 * @className: SaTokenInterceptor
 * @author: feather
 * @description:
 * @since: 2025-05-23 9:40
 * @version: 1.0
 */
public class SaTokenInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();


        String authToken = request.getHeader("feather-token-id");
        if (StringUtils.isBlank(authToken)){
            throw  new BizException(BizCodeEnum.TOKEN_EXPIRE_ERROR);
        }
        // 对其他接口进行身份验证
        try {
            StpUtil.checkLogin();
            return true;
        } catch (NotLoginException e) {
            throw  new BizException(BizCodeEnum.TOKEN_EXPIRE_ERROR);
        }
    }
}
