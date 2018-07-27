package com.travel.intercepter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-07-27
 */
public class GeneralIntercepter implements HandlerInterceptor {

    @Value("${contextpath}")
    private String contextpath;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String js = "";
        String css = "";
        if(httpServletRequest.getRequestURI().equals("/")){
            js = contextpath+"js"+httpServletRequest.getRequestURI()+"index.js";
            css = contextpath+"css"+httpServletRequest.getRequestURI()+"index.css";
        }else{
            js = contextpath+"js"+httpServletRequest.getRequestURI()+".js";
            css = contextpath+"css"+httpServletRequest.getRequestURI()+".css";
        }
        httpServletRequest.setAttribute("js",js);
        httpServletRequest.setAttribute("css",css);
        httpServletRequest.setAttribute("contextpath",contextpath);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
