package com.clsr0901.blog.util;


import com.clsr0901.blog.entity.Result;

public class ResultUtil<T> {

    private static final Integer TOKEN_EXPIRATION = 30 * 60 * 1000;

    public static <T> Result success(){
        return new Result("success");
    }
    public static <T> Result success(T data){
        return new Result("success", data);
    }
    public static <T> Result success(T data, String token){
        return new Result("success", data, token);
    }
    public static Result error(String msg){
        return new Result(msg);
    }
    public static Result error(){
        return new Result("服务器异常");
    }

}
