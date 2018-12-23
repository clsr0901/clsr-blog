package com.clsr0901.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Blog {
    /**
     * 博客ID
     */
    @ApiModelProperty(value = "博客ID", name = "id")
    private int id;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", name = "userId")
    private int userId;
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
     *创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createtime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;

    /**
     *更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updatetime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String updatetime;
}
