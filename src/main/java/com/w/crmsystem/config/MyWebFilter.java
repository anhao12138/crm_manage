package com.w.crmsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/7 0:08
 * @Version 1.0
 */
@Configuration
public class MyWebFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        response.setCharacterEncoding("utf-8");

        return true;
    }
}
