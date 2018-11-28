package com.ktcatv.qtms.handler;

import com.ktcatv.qtms.entity.Result;
import com.ktcatv.qtms.exception.QTMSException;
import com.ktcatv.qtms.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalDefultExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Result<?> defultExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof QTMSException) {
            log.error("业务异常exception={}", e);
            QTMSException businessException = (QTMSException) e;
            return ResultUtil.error(businessException.getMessage());
        }
        if (e instanceof MyBatisSystemException) {
            log.error("Mybatis系统异常 exception={}", e);
            return ResultUtil.error();
        }
        if (e instanceof DuplicateKeyException) {
            log.error("DuplicateKey异常 exception={}", e);
            return ResultUtil.error();
        }
        if (e instanceof DataIntegrityViolationException) {
            log.error("DataIntegrityViolation异常 exception={}", e);
            return ResultUtil.error();
        }
        //未知错误
        log.error("系统异常exception={}", e);
        return ResultUtil.error("系统异常：" + e.getMessage());
    }


}