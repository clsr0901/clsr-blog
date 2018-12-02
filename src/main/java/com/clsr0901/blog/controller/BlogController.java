package com.clsr0901.blog.controller;

import com.clsr0901.blog.entity.Blog;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.service.BlogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/save")
    @ApiOperation(value = "新增或修改博客", notes = "新增或修改博客接口/blog/save", tags = "BlogController", httpMethod = "POST")
    public Result<Blog> login(@RequestBody @ApiParam(name = "Blog对象", value = "传入json格式", required = true) Blog blog) {
        log.info("新增或修改博客 blog={}", blog);
        return blogService.save(blog);
    }

    @GetMapping("/query/all")
    @ApiOperation(value = "查询所有博客", notes = "根据ID查询博客接口/query/{id}", tags = "BlogController", httpMethod = "POST")
    public Result<List<Blog>> findAll() {
        log.info("查询所有博客");
        return blogService.findAll();
    }

    @GetMapping("/query/{id}")
    @ApiOperation(value = "根据ID查询博客", notes = "根据ID查询博客接口/query/{id}", tags = "BlogController", httpMethod = "POST")
    public Result<Blog> findById(@PathVariable("id") @ApiParam(name = "Blog ID", value = "传入博客id", required = true) int id) {
        log.info("根据ID查询博客 id={}", id);
        return blogService.findById(id);
    }

    @GetMapping("/query/user/{userId}")
    @ApiOperation(value = "根据用户ID查询博客", notes = "根据用户ID查询博客接口/query/user/{id}", tags = "BlogController", httpMethod = "POST")
    public Result<List<Blog>> findByUserId(@PathVariable("userId") @ApiParam(name = "Blog ID", value = "传入博客id", required = true) int userId) {
        log.info("根据用户ID查询博客 userId={}", userId);
        return blogService.findByUserId(userId);
    }
}
