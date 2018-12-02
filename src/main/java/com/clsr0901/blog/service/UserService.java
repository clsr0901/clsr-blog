package com.clsr0901.blog.service;

import com.clsr0901.blog.appEnum.ExceptionEnum;
import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.entity.User;
import com.clsr0901.blog.exception.BException;
import com.clsr0901.blog.mapper.UserMapper;
import com.clsr0901.blog.util.ResultUtil;
import com.clsr0901.blog.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    private static final Integer TOKEN_EXPIRATION = 30 * 60 * 1000;
    @Autowired
    private UserMapper userMapper;

    public Result<User> save(User user) {
        User result = userMapper.findById(user.getId());
        //判断用户是否存在，不存在新增，存在修改资料
        if (result == null) {
            userMapper.insert(user);
        } else {
            userMapper.update(user);
        }
        return ResultUtil.success(user);
    }

    public Result<User> login(User user, HttpServletResponse response) {
        User result = null;
        if (user.getUsername() != null) {
            user.setPhone(null);
            result = userMapper.findByUsername(user.getUsername());
        }
        if (!StringUtils.isEmpty(user.getPhone()))
            result = userMapper.findByPhone(user.getPhone());
        if (result == null || !result.getPassword().equals(user.getPassword()))
            throw new BException(ExceptionEnum.USERNAME_AND_PASSWORD_ERROR);
        return ResultUtil.success(result, "Bearer " + TokenUtil.getToken(result.getUsername(), TOKEN_EXPIRATION));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(s);
        if (user == null)
            throw new BException(ExceptionEnum.USERNAME_AND_PASSWORD_ERROR);
        return user;
    }

    public String getUserName() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return username;
    }

    public User getCurrentUser(UserMapper mapper) {
        User currentUser = mapper.findByUsername(getUserName());
        log.info("当前用户 user={}", currentUser);
        return currentUser;
    }
}
