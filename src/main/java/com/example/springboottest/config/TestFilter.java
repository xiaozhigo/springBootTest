package com.example.springboottest.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

@Configuration
public class TestFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("-----------------------"+System.currentTimeMillis()+"------------------------------");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("-----------------------"+System.currentTimeMillis()+"------------------------------");
    }

    @Override
    public void destroy() {

    }
}
