package com.clsr0901.blog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private String msg;
    private T data;
    private String token;

    public Result() {
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public Result(String msg, T data, String token) {
        this.msg = msg;
        this.data = data;
        this.token = token;
    }

}
