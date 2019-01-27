package com.clsr0901.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class User implements UserDetails {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", name = "id")
    private int id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", name = "avatar")
    private String avatar;
    /**
     *
     *
     */
    @ApiModelProperty(value = "用户邮箱", name = "email")
    private String email;
    /**
     * 用户手机号码
     */
    @ApiModelProperty(value = "用户手机号码", name = "phone")
    private String phone;
    /**
     * 用户自我介绍
     */
    @ApiModelProperty(value = "用户自我介绍", name = "instruction")
    private String instruction;
    /**
     *创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createtime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;

    /**
     * 用户权限组
     */
    @JsonIgnore
    @ApiModelProperty(value = "用户权限组", name = "roles")
    private List<Role> roles;


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<Role> roles = getRoles();
        for(Role role : roles)
        {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
