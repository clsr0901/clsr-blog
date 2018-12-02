package com.clsr0901.blog.exception;


import com.clsr0901.blog.appEnum.ExceptionEnum;
import lombok.Data;

@Data
public class BException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;  //错误码

    public BException() {
    }

    public BException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

}

