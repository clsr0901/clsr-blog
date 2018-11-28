package com.ktcatv.qtms.exception;


import com.ktcatv.qtms.applicationEnum.ExceptionEnum;
import lombok.Data;

@Data
public class QTMSException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;  //错误码

    public QTMSException() {
    }

    public QTMSException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

}

