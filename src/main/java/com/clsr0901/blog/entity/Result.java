package com.ktcatv.qtms.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private String msg;
    private T data;
    private Integer total;
    private String token;

    public Result() {
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(String msg, T data, String token) {
        this.msg = msg;
        this.data = data;
        this.token = "Bearer " + token;
    }

    public Result(String msg, T data, int total, String token) {
        this.msg = msg;
        this.data = data;
        this.total = total;
        this.token = "Bearer " + token;
    }
}
