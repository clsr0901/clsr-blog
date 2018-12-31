package com.clsr0901.blog.util;

import com.clsr0901.blog.entity.User;
import com.clsr0901.blog.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Slf4j
public class UserUtil {
    public static String getUserName(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return username;
    }

    public static User getCurrentUser(UserMapper mapper){
        User currentUser = mapper.findByUsername(getUserName());
        log.info("当前用户 user={}", currentUser);
        return currentUser;
    }
}
