package com.clsr0901.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Message {
    /**
     * 留言ID
     */
    @ApiModelProperty(value = "留言ID", name = "id")
    private int id;
    /**
     * 目标人id
     */
    @ApiModelProperty(value = "目标人id", name = "destUserId")
    private int destUserId;

    /**
     * 留言人id
     */
    @ApiModelProperty(value = "留言人id", name = "sourceUserId")
    private int sourceUserId;
    /**
     * 留言信息
     */
    @ApiModelProperty(value = "留言信息", name = "message")
    private String message;

    /**
     *创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createtime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createtime;
}
