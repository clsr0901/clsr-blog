package com.clsr0901.blog.controller;

import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.entity.User;
import com.clsr0901.blog.service.UserService;
import com.clsr0901.blog.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{userId}")
    @ApiOperation(value = "根据用户Id获取用户信息", notes = "根据用户Id获取用户信息接口 /user/get/{userId}", tags = "UserController", httpMethod = "GET")
    public Result<UserVO> findUserById(@PathVariable("userId")@ApiParam(name = "userId", value = "传入int格式", required = true) int userId){
        log.info("根据用户Id获取用户信息 userId={}", userId);
        return userService.findUserById(userId);
    }
}
