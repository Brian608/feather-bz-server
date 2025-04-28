package org.feather.bz.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.feather.bz.domain.base.BaseEntity;
import org.feather.bz.domain.base.BaseRequest;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.vo
 * @className: UserVo
 * @author: feather
 * @description:
 * @since: 2025-04-28 14:24
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "注册用户对象")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVo  extends BaseEntity {



    @Schema(description = "用户名",name ="username",example = "17780778066")
    private String username;

    @Schema(description = "真实姓名",name ="realName",example = "管理员")
    private String realName;

    @Schema(description = "邮箱",name ="email",example = "123@qq.com")
    private String email;

    @Schema(description = "性别",name ="sex",example = "2")
    private Integer sex;

    @Schema(description = "性别",name ="sex",example = "2")
    private String sexText;
}
