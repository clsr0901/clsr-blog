package com.clsr0901.blog.filter;


import com.clsr0901.blog.appEnum.ExceptionEnum;
import com.clsr0901.blog.entity.User;
import com.clsr0901.blog.mapper.UserMapper;
import com.clsr0901.blog.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private UserMapper userMapper;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserMapper userMapper) {
        super(authenticationManager);
        this.userMapper = userMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
        if (authentication == null) {
            response.setContentType("application/json;charset=UTF-8");
            response.sendError(ExceptionEnum.TOKEN_EXPIRED.getCode(), ExceptionEnum.TOKEN_EXPIRED.getMsg());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
        }
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        String username = TokenUtil.parseToken(token);
        if (username.equals("")) {
            return null;
        }
        User sysUser = userMapper.findByUsername(username);
        if (sysUser != null) {
            return new UsernamePasswordAuthenticationToken(username, sysUser.getPassword(), new ArrayList<>());
        }
        return null;
    }

}
