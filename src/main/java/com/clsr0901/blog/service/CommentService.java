package com.clsr0901.blog.service;

import com.clsr0901.blog.appEnum.ExceptionEnum;
import com.clsr0901.blog.entity.Comment;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.exception.BException;
import com.clsr0901.blog.mapper.BlogMapper;
import com.clsr0901.blog.mapper.CommentMapper;
import com.clsr0901.blog.mapper.UserMapper;
import com.clsr0901.blog.util.ResultUtil;
import com.clsr0901.blog.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;

    public Result<Comment> put(Comment comment) {
        if (blogMapper.findById(comment.getBlogId()) == null)
            throw new BException(ExceptionEnum.BLOG_NOT_EXITS);
        if (userMapper.findById(comment.getSourceUserId()) == null)
            throw new BException(ExceptionEnum.USER_NOT_EXITS);
        commentMapper.insert(comment);
        return ResultUtil.success(comment);
    }

    public Result delete(int id) {
        commentMapper.deleteById(id);
        return ResultUtil.success();
    }

    public Result<List<CommentVO>> get(int blogId) {
        if (blogMapper.findById(blogId) == null)
            throw new BException(ExceptionEnum.BLOG_NOT_EXITS);
        return ResultUtil.success(commentMapper.findAllByBlogId(blogId));
    }
}
