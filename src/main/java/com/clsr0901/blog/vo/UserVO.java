package com.clsr0901.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVO {
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
     * 头像
     */
    @ApiModelProperty(value = "头像", name = "avatar")
    private String avatar;
    /**
     * 用户邮箱
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
     * 博客数
     */
    @ApiModelProperty(value = "博客数", name = "blogs")
    private int blogs;

    /**
     * 评论数
     */
    @ApiModelProperty(value = "评论数", name = "comments")
    private int comments;
}
