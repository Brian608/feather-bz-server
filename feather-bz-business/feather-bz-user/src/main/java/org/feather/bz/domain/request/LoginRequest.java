package org.feather.bz.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feather.bz.domain.base.BaseRequest;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.request
 * @className: LoginRequest
 * @author: feather
 * @description:
 * @since: 2025-05-07 17:21
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequest  extends BaseRequest {


    @Schema(description = "账号",name ="userName",example = "17780628079")
    @NotBlank(message = "账号不能为空")
    private String userName;

    @Schema(description = "验证码",name ="smsCaptcha",example = "123456")
    @NotBlank(message = "验证码不能为空")
    private String  smsCaptcha;



    @Schema(description = "记住我",name ="rememberMe",example = "true")
    @NotNull(message = "记住我不能为空")
    private Boolean rememberMe;
}
