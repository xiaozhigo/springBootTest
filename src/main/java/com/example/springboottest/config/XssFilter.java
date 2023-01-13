package com.example.springboottest.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Configuration
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            long startTime = System.currentTimeMillis();
            request.setAttribute("startTime", startTime);
            Span span = new Span();
            span.setTraceId(UUID.randomUUID().toString().replaceAll("-", ""));
            span.setId(1l);
            MDC.put("req.id", span.getTraceId());
            //过滤request
            HttpServletRequest xssHttpRequest = new XssHttpRequestWrapper(request);
            filterChain.doFilter(xssHttpRequest, servletResponse);
            log.debug("[" + request.getRequestURI() + "] executeTime : " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void destroy() {
        log.info("springboot => XssFilter destroy ...");
    }
}
