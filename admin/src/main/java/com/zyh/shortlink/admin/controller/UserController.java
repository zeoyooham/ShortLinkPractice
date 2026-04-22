package com.zyh.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zyh.shortlink.admin.convention.result.Result;
import com.zyh.shortlink.admin.convention.result.Results;
import com.zyh.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.zyh.shortlink.admin.dto.resp.UserActualRespDTO;
import com.zyh.shortlink.admin.dto.resp.UserRespDTO;
import com.zyh.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * author 邹宇航  @vision 1.0
 */
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUserName(@PathVariable("username") String username){
        return Results.success(userService.getUserByUserName(username));
    }

    /**
     * 获取无脱敏的用户信息
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUserName(@PathVariable("username")String username){
        return Results.success(BeanUtil.toBean(userService.getUserByUserName(username), UserActualRespDTO.class));
    }

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/user/has-username")
     public Result<Boolean> hasUserName(@RequestParam("username") String username){
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 用户注册
     * @param requestParam
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam){
        userService.register(requestParam);
        return Results.success();
    }
}
