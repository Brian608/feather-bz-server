package org.feather.bz.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feather.bz.domain.base.BaseEntity;

import java.time.LocalDateTime;

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
    private String username;

    /**
     * 密码 密码
     */
    private String password;

    private String salt;


    /**
     * 真实姓名
     */
    private String realName;



    /**
     * 手机号 手机号
     */
    private String mobile;

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

