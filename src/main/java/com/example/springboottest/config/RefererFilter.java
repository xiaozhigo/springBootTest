package com.example.springboottest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 */
/*@Configuration*/
public class RefererFilter extends HttpServlet implements Filter {
    Logger log = LoggerFactory.getLogger(RefererFilter.class);
    private static final long serialVersionUID = 1L;
    private FilterConfig filterConfig;
    //白名单
    static List<String> whiteList = new ArrayList<>();

    static {
        whiteList.add("baidu.com");
        whiteList.add("google.com");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String referer = request.getHeader("referer");
        String serverName = request.getServerName();
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        log.info("RefererFilter-referer:{}", referer);
        log.info("RefererFilter-serverName:{}", serverName);
        log.info("RefererFilter-contextPath:{}", contextPath);
        log.info("RefererFilter-requestURI:{}", requestURI);
        if (referer == null) {
            log.info("RefererFilter-referer is null");
            filterChain.doFilter(request, response);
        } else if (referer.contains(serverName) || checkUrl(referer.split("/")[2])
                || requestURI.contains(referer.split("/")[2])) {
            String ip = referer.split("/")[2];
            log.info("RefererFilter-ip:{}",ip);
            filterChain.doFilter(request, response);
        } else if (referer.endsWith("/api/doc.html")) {
            //处理swagger
            filterChain.doFilter(request, response);
        }
    }

    public void destroy() {
        this.filterConfig = null;
    }

    private boolean checkUrl(String url) {
        for (String s : whiteList) {
            if (url.endsWith(s)) {
                return true;
            }
        }
        return false;
    }
}
