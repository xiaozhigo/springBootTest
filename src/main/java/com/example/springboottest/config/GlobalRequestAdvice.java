package com.example.springboottest.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalRequestAdvice {

    @ModelAttribute
    public void authenticationUser(HttpServletRequest request) {
        System.out.println("查询的参数：" + request.getQueryString());
        System.out.println("用户名参数：" + request.getParameter("userName"));
    }
}
