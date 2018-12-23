package com.clsr0901.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    /**
     * 评论ID
     */
    @ApiModelProperty(value = "评论ID", name = "id")
    private int id;

    /**
     * 博客id
     */
    @ApiModelProperty(value = "博客id", name = "blogId")
    private int blogId;

    /**
     * 目标人id
     */
    @ApiModelProperty(value = "目标人id", name = "destUserId")
    private int destUserId;

    /**
     * 评论人id
     */
    @ApiModelProperty(value = "评论人id", name = "sourceUserId")
    private int sourceUserId;
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容", name = "content")
    private String content;

    /**
     * 0 评论， 1 回复
     */
    @ApiModelProperty(value = "0 评论， 1 回复", name = "action")
    private int action;

    /**
     *创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createtime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;
}
