package com.clsr0901.blog.service;

import com.clsr0901.blog.appEnum.ExceptionEnum;
import com.clsr0901.blog.mapper.CommentMapper;
import com.clsr0901.blog.vo.BlogVO;
import com.clsr0901.blog.entity.Blog;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.entity.User;
import com.clsr0901.blog.exception.BException;
import com.clsr0901.blog.mapper.BlogMapper;
import com.clsr0901.blog.mapper.UserMapper;
import com.clsr0901.blog.util.BlogStringUtil;
import com.clsr0901.blog.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;

    public Result save(Blog blog) {
        User user = userMapper.findById(blog.getUserId());
        if (user == null)
            throw new BException(ExceptionEnum.USER_NOT_EXITS);
        Blog result = blogMapper.findById(blog.getId());
        blog.setSummary(BlogStringUtil.removeTag(blog.getContent(), blog.getContent().length() < 200? blog.getContent().length():200));
        if (result == null) {
            blogMapper.insert(blog);
        } else {
            BeanUtils.copyProperties(blog, result);
            blogMapper.update(result);
        }
        return ResultUtil.success();
    }

    public Result post(Blog blog){
        Blog result = blogMapper.findById(blog.getId());
        if(result == null)
            throw new BException(ExceptionEnum.BLOG_NOT_EXITS);
        blog.setSummary(BlogStringUtil.removeTag(blog.getContent(), blog.getContent().length() < 200? blog.getContent().length():200));
        BeanUtils.copyProperties(blog, result);
        blogMapper.updateContent(result);
        return ResultUtil.success();
    }

    public Result<List<BlogVO>> findAll() {
        List<Blog> blogs = blogMapper.findAll();
        List<BlogVO> blogVOS = new ArrayList<BlogVO>();
        blogs.forEach(blog -> {
            blogVOS.add(blogToBlogDTO(blog));
        });
        return ResultUtil.success(blogVOS);
    }

    public Result delete(int id) {
        Blog blog = blogMapper.findById(id);
        if (blog == null)
            return ResultUtil.success();
        blogMapper.delete(id);
        return ResultUtil.success();
    }

    public Result<BlogVO> findById(int id) {
        Blog blog = blogMapper.findById(id);
        if (blog == null)
            throw new BException(ExceptionEnum.BLOG_NOT_EXITS);
        blog.setView(blog.getView() + 1);
        blogMapper.updateView(blog);
        return ResultUtil.success(blogToBlogDTO(blog));
    }

    public Result<List<BlogVO>> findByUserId(int userId) {
        if (userMapper.findById(userId) == null)
            throw new BException(ExceptionEnum.USER_NOT_EXITS);
        List<Blog> blogs = blogMapper.findByUserId(userId);
        List<BlogVO> blogVOS = new ArrayList<BlogVO>();
        blogs.forEach(blog -> {
            blogVOS.add(blogToBlogDTO(blog));
        });
        return ResultUtil.success(blogVOS);
    }

    private BlogVO blogToBlogDTO(Blog blog) {
        BlogVO blogVO = new BlogVO();
        BeanUtils.copyProperties(blog, blogVO);
        blogVO.setUser(userMapper.findById(blog.getUserId()));
        blogVO.setComments(commentMapper.countByBlogId(blog.getId()));
        return blogVO;
    }
}
