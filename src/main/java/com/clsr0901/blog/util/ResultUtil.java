package com.ktcatv.qtms.utils;


import com.ktcatv.qtms.entity.Result;

public class ResultUtil<T> {

    private static final Integer TOKEN_EXPIRATION = 30 * 60 * 1000;

    public static <T> Result success(T data){
        return new Result(null, data, TokenUtil.getToken(UserUtil.getUserName(), TOKEN_EXPIRATION));
    }
    public static <T> Result success(T data, String username){
        return new Result(null, data, TokenUtil.getToken(username, TOKEN_EXPIRATION));
    }
    public static <T> Result success(){
        return new Result(null, "success", TokenUtil.getToken(UserUtil.getUserName(), TOKEN_EXPIRATION));
    }
    public static <T> Result success(T data, int total){
        return new Result(null, data, total, TokenUtil.getToken(UserUtil.getUserName(), TOKEN_EXPIRATION));
    }
    public static Result error(String msg){
        return new Result(msg);
    }
    public static Result error(){
        return new Result("服务器异常");
    }

}
