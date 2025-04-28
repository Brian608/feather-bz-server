package org.feather.bz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.feather.bz.domain.base.JsonResult;
import org.feather.bz.domain.entity.SysUser;
import org.feather.bz.domain.request.AddUserRequest;
import org.feather.bz.domain.vo.UserVo;
import org.feather.bz.service.ISysUserService;
import org.feather.bz.validator.IsMobile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.controller
 * @className: UserController
 * @author: feather
 * @description:
 * @since: 2025-04-23 17:55
 * @version: 1.0
 */
@Validated
@Tag(name = "用户管理", description = "提供用户相关接口")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private  final ISysUserService sysUserService;

    @Operation(summary = "注册用户",   description = "注册用户")
    @PostMapping("/register")
    public JsonResult<Boolean> register(@RequestBody @Validated AddUserRequest request)  {
        return JsonResult.buildSuccess(sysUserService.register(request));
    }
    @Operation(summary = "发送验证码",   description = "发送验证码")
    @GetMapping("/sendCaptcha")
    public JsonResult<Boolean> sendCaptcha( @IsMobile String phone) {
        return JsonResult.buildSuccess(sysUserService.sendSmsCaptcha(phone));
    }

    @Operation(summary = "用户信息",   description = "用户信息")
    @GetMapping("/getUserInfo/{userId}")
    public JsonResult<UserVo> getUserInfo(@PathVariable Long userId) {
        return JsonResult.buildSuccess(sysUserService.getUserInfo(userId));
    }

    @Operation(summary = "查询用户列表", description = "获取系统中所有用户的信息",method = "GET")
    @GetMapping("/list")
    public JsonResult<List<SysUser>> list()  {
        return  JsonResult.buildSuccess(sysUserService.list());
    }

}
