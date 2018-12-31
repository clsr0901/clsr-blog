package com.clsr0901.blog.controller;

import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.entity.Source;
import com.clsr0901.blog.service.SourceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/source")
@Slf4j
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @GetMapping("/get/{userId}")
    @ApiOperation(value = "根据用户ID查询资源", notes = "根据用户ID查询资源接口/source/get/{id}", tags = "SourceController", httpMethod = "GET")
    public Result<List<Source>> findByUserId(@PathVariable("userId") @ApiParam(name = "User ID", value = "传入用户id", required = true) int userId) {
        log.info("根据用户ID查询资源 userId={}", userId);
        return sourceService.get(userId);
    }

    @DeleteMapping("/delete/{uid}")
    @ApiOperation(value = "根据资源uid删除资源", notes = "根据资源uid删除资源接口/source/delete/{uid}", tags = "SourceController", httpMethod = "DELETE")
    public Result deleteByUid(@PathVariable("uid") @ApiParam(name = "Source uid", value = "传入资源uid", required = true) String uid) {
        log.info("根据资源uid删除资源 uid={}", uid);
        return sourceService.deleteByUid(uid);
    }
}
