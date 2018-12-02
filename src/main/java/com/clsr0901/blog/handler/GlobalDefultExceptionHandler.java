package com.clsr0901.blog.handler;

import com.clsr0901.blog.entity.Result;
import com.clsr0901.blog.exception.BException;
import com.clsr0901.blog.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@ControllerAdvice
public class GlobalDefultExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Result<?> defultExcepitonHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException{
        e.printStackTrace();
        if (e instanceof BException) {
            log.error("业务异常exception={}", e);
            BException businessException = (BException) e;
            response.sendError(((BException) e).getCode(), e.getMessage());
            return ResultUtil.error(businessException.getMessage());
        }
        if (e instanceof MyBatisSystemException) {
            log.error("Mybatis系统异常 exception={}", e);
            response.sendError(503, e.getMessage());
            return ResultUtil.error();
        }
        if (e instanceof DuplicateKeyException) {
            log.error("DuplicateKey异常 exception={}", e);
            response.sendError(503, e.getMessage());
            return ResultUtil.error();
        }
        if (e instanceof DataIntegrityViolationException) {
            log.error("DataIntegrityViolation异常 exception={}", e);
            response.sendError(503, e.getMessage());
            return ResultUtil.error();
        }
        //未知错误
        log.error("系统异常exception={}", e);
        response.sendError(503, e.getMessage());
        return ResultUtil.error("系统异常：" + e.getMessage());
    }


}