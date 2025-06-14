package org.feather.bz.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.feather.bz.domain.base.JsonResult;
import org.feather.bz.domain.entity.SysUser;
import org.feather.bz.domain.request.AddUserRequest;
import org.feather.bz.domain.request.LoginRequest;
import org.feather.bz.domain.vo.LoginVO;
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

    @Operation(summary = "登录",   description = "登录")
    @PostMapping("/login")
    public JsonResult<LoginVO> login(@RequestBody @Validated LoginRequest request)  {
        return JsonResult.buildSuccess(sysUserService.login(request));
    }

    @Operation(summary = "登出",   description = "登出")
    @PostMapping("/logout")
    public JsonResult<Boolean> logout()  {
        StpUtil.logout();
        return JsonResult.buildSuccess(true);
    }

    @Operation(summary = "用户信息",   description = "用户信息")
    @GetMapping("/getUserInfo")
    public JsonResult<UserVo> getUserInfo() {
        String userId = StpUtil.getLoginIdAsString();
        UserVo userVo = (UserVo) StpUtil.getTokenSession().get(String.valueOf(userId));
        return JsonResult.buildSuccess(userVo);
    }

    @Operation(summary = "查询用户列表", description = "获取系统中所有用户的信息",method = "GET")
    @GetMapping("/list")
    public JsonResult<List<SysUser>> list()  {
        return  JsonResult.buildSuccess(sysUserService.list());
    }

    @Operation(summary = "点赞", description = "点赞",method = "GET")
    @GetMapping("/likePost/{postId}")
    public JsonResult<Boolean> likePost(@PathVariable Long postId)  {
        return  JsonResult.buildSuccess(sysUserService.likePost(postId));
    }

    @Operation(summary = "取消点赞", description = "取消点赞",method = "GET")
    @GetMapping("/unLikePost/{postId}")
    public JsonResult<Boolean> unLikePost(@PathVariable Long postId)  {
        return  JsonResult.buildSuccess(sysUserService.unLikePost(postId));
    }

    @Operation(summary = "点赞列表", description = "点赞列表",method = "GET")
    @GetMapping("/getLikes/{postId}")
    public JsonResult<List<String>> getLikes(@PathVariable Long postId)  {
        return  JsonResult.buildSuccess(sysUserService.getLikes(postId));
    }


}
