package com.clsr0901.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于处理路由
 * @author Lichun
 *
 */
@Controller
@RequestMapping("/web")
@Slf4j
public class WebController  {
    /**
     * 默认处理/web下说有的请求，全部转发到index.html
     * @param request
     * @param response
     */
    @RequestMapping("**")
    public void routes(HttpServletRequest request , HttpServletResponse response) {
        request.setAttribute("routes","路由跳转");
        try {
            // 此处路径要打两点，如果直接写 index.html 会循环反问/web/index.html 造成死循环
            request.getRequestDispatcher("../index.html").forward(request,response);
        } catch (Exception es) {
            log.error("路由失败",es);
        }
    }
}