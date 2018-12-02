package com.clsr0901.blog.service;

import com.clsr0901.blog.appEnum.ExceptionEnum;
import com.clsr0901.blog.entity.Blog;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.entity.User;
import com.clsr0901.blog.exception.BException;
import com.clsr0901.blog.mapper.BlogMapper;
import com.clsr0901.blog.mapper.UserMapper;
import com.clsr0901.blog.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;

    public Result<Blog> save(Blog blog) {
        User user = userMapper.findById(blog.getUserId());
        if (user == null)
            throw new BException(ExceptionEnum.USER_NOT_EXITS);
        Blog result = blogMapper.findById(blog.getId());
        if (result == null) {
            blogMapper.insert(blog);
        } else {
            BeanUtils.copyProperties(blog, result);
            blogMapper.update(result);
        }
        return ResultUtil.success(blogMapper.findById(blog.getId()));
    }
}
