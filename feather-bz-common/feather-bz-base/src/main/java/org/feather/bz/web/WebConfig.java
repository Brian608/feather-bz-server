package org.feather.bz.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.web
 * @className: WebConfig
 * @author: feather
 * @description:
 * @since: 2025-05-23 9:42
 * @version: 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 排除的路径列表（不需要登录验证的接口）
    private static final List<String> EXCLUDE_PATHS = Arrays.asList(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/doc.html",
            "/webjars/**",
            "/favicon.ico",
            "/user/login",
            "/user/sendCaptcha",
            "/user/register"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器，拦截所有请求
        registry.addInterceptor(new SaTokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }
}