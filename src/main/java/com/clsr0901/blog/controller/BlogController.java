package com.clsr0901.blog.controller;

import com.clsr0901.blog.entity.Blog;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.service.BlogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
