package com.clsr0901.blog.vo;

import com.clsr0901.blog.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class BlogVO {
    /**
     * 博客ID
     */
    @ApiModelProperty(value = "博客ID", name = "id")
    private int id;
    /**
     * 用户
     */
    @ApiModelProperty(value = "用户", name = "user")
    private User user;
    /**
     * 博客标题
     */
    @ApiModelProperty(value = "博客标题", name = "title")
    private String title;
    /**
     * 博客内容
     */
    @ApiModelProperty(value = "博客内容", name = "content")
    private String content;
    /**
     * 博客摘要
     */
    @ApiModelProperty(value = "博客摘要", name = "summary")
    private String summary;
    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数", name = "hit")
    private int hit;
    /**
     * 观看数
     */
    @ApiModelProperty(value = "观看数", name = "view")
    private int view;

    /**
     * 评论数
     */
    @ApiModelProperty(value = "评论数", name = "commets")
    private int comments;
    /**
     * 是否置顶
     */
    @ApiModelProperty(value = "是否置顶", name = "sticky")
    private boolean sticky;
    /**
     * 是否加精
     */
    @ApiModelProperty(value = "是否加精", name = "highlight")
    private boolean highlight;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createtime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "updatetime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatetime;
}

