package com.clsr0901.blog.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRole {
    /**
     * id
     */
    private int id;
    /**
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID", name = "roleId")
    private int roleId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", name = "userId")
    private int userId;
}
