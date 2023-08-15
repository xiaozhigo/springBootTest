package com.example.springboottest.xss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssFilter implements Filter{
    private static final Logger logger = LoggerFactory.getLogger(XssFilter.class);

    /**
     * 初始化方法
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("----xss filter start-----");
    }
    /**
     * 过滤方法
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest wrapper = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            wrapper = new XSSHttpServletRequestWrapper(servletRequest);
        }

        if (null == wrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(wrapper, response);
        }
    }
}
