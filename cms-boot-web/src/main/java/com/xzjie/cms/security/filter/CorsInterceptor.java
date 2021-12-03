//package com.xzjie.cms.security.filter;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Configuration
//public class CorsInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        if ("OPTIONS".equals(request.getMethod())) {
//            return true;
//        }
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }
//
//}
