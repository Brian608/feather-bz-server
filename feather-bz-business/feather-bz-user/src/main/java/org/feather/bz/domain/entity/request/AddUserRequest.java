package org.feather.bz.domain.entity.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.feather.bz.domain.base.BaseRequest;
import org.feather.bz.domain.entity.enums.SexEnum;
import org.feather.bz.validator.InEnum;
import org.feather.bz.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.entity.request
 * @className: AddUserRequest
 * @author: feather
 * @description:
 * @since: 2025-04-27 14:44
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "注册用户对象")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddUserRequest  extends BaseRequest {

    @Length(max = 11,message = "姓名最长11位置")
  //  @Pattern(regexp = "^[a-zA-Z0-9]+$",message = "用户名只能包含数字和字母，并且不能超过50个字符")
    @IsMobile
    @NotNull(message = "用户名不能为空")
    @Schema(description = "用户名",name ="username",example = "17780778066")
    private String username;


    @NotNull(message = "密码不能为空")
    @Schema(description = "密码 md5后再base64",name ="password")
    private String password;



    /**
     * 真实姓名
     */
    @Length(max = 50,message = "真实姓名最长50")
    @NotNull(message = "真实姓名不能为空")
    @Schema(description = "真实姓名",name ="realName",example = "管理员")
    private String realName;


    /**
     * 邮箱地址 邮箱地址
     */
    @NotNull(message = "邮箱不能为空")
    @Length(max = 50)
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",message = "邮箱格式错误")
    @Schema(description = "邮箱",name ="email",example = "123@qq.com")
    private String email;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    @InEnum(value = SexEnum.class,message = "性别值只能在{value}范围内")
    @Schema(description = "性别",name ="sex",example = "2")
    @NotNull(message = "性别不能为空")
    private Integer sex;

//    @Schema(description = "头像base64",name ="avatar")
//    @NotNull(message = "头像不能为空")
//    private String avatar;



}
