package org.feather.bz.domain.vo;

import cn.dev33.satoken.stp.StpUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.feather.bz.domain.base.BaseRequest;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.vo
 * @className: LoginVO
 * @author: feather
 * @description:
 * @since: 2025-05-06 17:31
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginVO extends BaseRequest {

    private  UserVo userVo;

    /**
     * 访问令牌
     */
    private String token;

    /**
     * 令牌过期时间
     */
    private Long tokenExpiration;


    public LoginVO(UserVo userVo) {
        this.userVo=userVo;
        this.token = StpUtil.getTokenValue();
        this.tokenExpiration = StpUtil.getTokenTimeout();
    }
}
