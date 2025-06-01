package org.feather.bz.interceptor;

import com.github.houbb.sensitive.core.api.SensitiveUtil;
import org.feather.bz.domain.base.JsonResult;
import org.feather.bz.domain.vo.UserVo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collection;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.interceptor
 * @className: SensitiveResponseBodyAdvice
 * @author: feather
 * @description: 脱敏响应体处理
 * @since: 2025-06-01 14:57
 * @version: 1.0
 */
@ControllerAdvice
public class SensitiveResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 只对特定类型的返回值执行处理逻辑，这里可以根据需要调整判断条件
        return JsonResult.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果返回的对象是UserInfo/InviteRankInfo，进行脱敏处理
        if (body instanceof JsonResult) {

            if (((JsonResult<?>) body).getData() == null) {
                return body;
            }

            if (((JsonResult<?>) body).getData() instanceof Collection<?>) {
                ((JsonResult<Collection>) body).setData(SensitiveUtil.desCopyCollection((Collection) ((JsonResult<?>) body).getData()));
                return body;
            }

            switch (((JsonResult<?>) body).getData()) {
                case UserVo userVo:
                    ((JsonResult<UserVo>) body).setData(SensitiveUtil.desCopy(userVo));
                    return body;
//                case InviteRankInfo inviteRankInfo:
//                    ((Result<InviteRankInfo>) body).setData(SensitiveUtil.desCopy(inviteRankInfo));
//                    return body;
                default:
                    return body;
            }
        }
        return body;
    }
}