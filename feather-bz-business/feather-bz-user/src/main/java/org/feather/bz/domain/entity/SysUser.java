package org.feather.bz.domain.entity;

import com.github.houbb.sensitive.annotation.strategy.SensitiveStrategyPassword;
import com.github.houbb.sensitive.annotation.strategy.SensitiveStrategyPhone;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feather.bz.domain.base.BaseEntity;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.entity
 * @className: SysUser
 * @author: feather
 * @description:
 * @since: 2025-04-23 17:42
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser extends BaseEntity {

    /**
     * 用户名 用户名
     */
    @SensitiveStrategyPhone
    private String username;

    /**
     * 密码 密码
     */
    @SensitiveStrategyPassword
    private String password;

    private String salt;


    /**
     * 真实姓名
     */
    private String realName;


    /**
     * 邮箱地址 邮箱地址
     */
    private String email;

    private String avatar;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    private Integer sex;








}

