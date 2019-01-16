package com.clsr0901.blog.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageQuery {
    /**
     * 查询关键字
     */
    @ApiModelProperty(value = "查询关键字", name = "keyword")
    private String keyword;
    /**
     * 查询页数
     */
    @ApiModelProperty(value = "查询页数", name = "pageNum")
    private int pageNum;
    /**
     * 查询大小
     */
    @ApiModelProperty(value = "查询大小", name = "pageSize")
    private int pageSize;

}
