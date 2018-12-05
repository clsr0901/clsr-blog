package com.clsr0901.blog.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Role {
    /**
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID", name = "id")
    private int id;
    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称", name = "name")
    private String name;
}
