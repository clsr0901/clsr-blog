package com.clsr0901.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MessageVO {
    /**
     * 留言ID
     */
    @ApiModelProperty(value = "留言ID", name = "id")
    private int id;
    /**
     * 目标人姓名
     */
    @ApiModelProperty(value = "目标人姓名", name = "destUserName")
    private String destUserName;

    /**
     * 留言人姓名
     */
    @ApiModelProperty(value = "留言人姓名", name = "sourceUserName")
    private String sourceUserName;
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
