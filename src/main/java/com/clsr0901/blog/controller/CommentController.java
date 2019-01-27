package com.clsr0901.blog.controller;

import com.clsr0901.blog.entity.Comment;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.service.CommentService;
import com.clsr0901.blog.vo.CommentVO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PutMapping("/put")
    @ApiOperation(value = "新增评论", notes = "新增评论接口/comment/put", tags = "CommentController", httpMethod = "PUT")
    public Result<CommentVO> put(@RequestBody @ApiParam(name = "Comment对象", value = "传入json格式", required = true) Comment comment){
        log.info("新增评论 comment={}", comment);
        return  commentService.put(comment);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除评论", notes = "根据博客id删除评论接口/comment/delete/{id}", tags = "CommentController", httpMethod="DELETE")
    public Result delete(@PathVariable("id") @ApiParam(name = "blog id", value = "传入int格式", required = true) int id){
        log.info("根据博客id删除评论 id={}", id);
        return  commentService.delete(id);
    }

    @GetMapping("/get/{blogId}")
    @ApiOperation(value = "根据博客id获取博客评论", notes = "根据博客id获取博客评论接口/comment/get/{blogId}", tags = "CommentController", httpMethod="GET")
    public Result<List<CommentVO>> get(@PathVariable("blogId") @ApiParam(name = "blog id", value = "传入int格式", required = true) int blogId){
        log.info("根据博客id获取博客评论 blogId={}", blogId);
        return  commentService.get(blogId);
    }

}
