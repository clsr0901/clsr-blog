package com.clsr0901.blog.controller;

import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.entity.User;
import com.clsr0901.blog.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/in")
    @ApiOperation(value = "用户登录", notes = "验证用户登录接口/login/in", tags = "LoginController", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "useraccount", value = "用户账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String"),
    })
    public Result<User> login(@RequestBody User user, HttpServletResponse response) {
        log.info("登录 user={}", user);
        return userService.login(user, response);
    }

    @PostMapping("/save")
    @ApiOperation(value = "用户注册或修改用户资料", notes = "用户注册或修改用户资料接口/login/save", tags = "LoginController", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "useraccount", value = "用户账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String"),
    })
    public Result<User> save(@RequestBody(required = true) @ApiParam(name = "user对象", value = "传入json格式", required = true) User user) {
        log.info("用户注册或修改用户资料 user={}", user);
        return userService.save(user);
    }
}
