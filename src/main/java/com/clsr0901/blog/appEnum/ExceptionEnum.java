package com.clsr0901.blog.appEnum;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    USERNAME_AND_PASSWORD_ERROR(510, "用户名密码错误"),
    USER_NOT_EXITS(511, "用户不存在"),
    BLOG_NOT_EXITS(512, "博客不存在"),
    UPLOAD_ERROR(513, "上传文件失败"),
    UPLOAD_CREATE_DIRECTORY_ERROR(513, "上传文件创建文件夹失败"),
    UPLOAD_SAVE_FILE_ERROR(513, "上传文件创建文件夹失败"),

    TOKEN_EXPIRED(417, "token失效"),

    ;
    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
