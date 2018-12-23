package com.clsr0901.blog.controller;

import com.clsr0901.blog.vo.BlogVO;
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

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/put")
    @ApiOperation(value = "新增或修改博客", notes = "新增或修改博客接口/blog/put", tags = "BlogController", httpMethod = "PUT")
    public Result put(@RequestBody @ApiParam(name = "Blog对象", value = "传入json格式", required = true) Blog blog) {
        log.info("新增或修改博客 blog={}", blog);
        return blogService.save(blog);
    }



    @PostMapping("/post")
    @ApiOperation(value = "更新博客", notes = "根据用户ID查询博客接口/blog/post", tags = "BlogController", httpMethod = "POST")
    public Result post(@RequestBody @ApiParam(name = "Blog 对象", value = "传入博客对象", required = true) Blog blog) {
        log.info("更新博客 blog={}", blog);
        return blogService.post(blog);
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据ID删除博客", notes = "根据ID删除博客接口/delete/{id}", tags = "BlogController", httpMethod = "DELETE")
    public Result delete(@PathVariable("id") @ApiParam(name = "Blog ID", value = "传入博客id", required = true) int id) {
        log.info("根据ID删除博客 id={}", id);
        return blogService.delete(id);
    }

    @GetMapping("/get")
    @ApiOperation(value = "查询所有博客", notes = "根据ID查询博客接口/query/{id}", tags = "BlogController", httpMethod = "GET")
    public Result<List<BlogVO>> findAll() {
        log.info("查询所有博客");
        return blogService.findAll();
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "根据ID查询博客", notes = "根据ID查询博客接口/query/{id}", tags = "BlogController", httpMethod = "GET")
    public Result<BlogVO> findById(@PathVariable("id") @ApiParam(name = "Blog ID", value = "传入博客id", required = true) int id) {
        log.info("根据ID查询博客 id={}", id);
        return blogService.findById(id);
    }

    @GetMapping("/get/user/{userId}")
    @ApiOperation(value = "根据用户ID查询博客", notes = "根据用户ID查询博客接口/query/user/{id}", tags = "BlogController", httpMethod = "GET")
    public Result<List<BlogVO>> findByUserId(@PathVariable("userId") @ApiParam(name = "Blog ID", value = "传入博客id", required = true) int userId) {
        log.info("根据用户ID查询博客 userId={}", userId);
        return blogService.findByUserId(userId);
    }


}
