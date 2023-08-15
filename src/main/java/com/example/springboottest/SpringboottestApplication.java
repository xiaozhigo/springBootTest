package com.example.springboottest;

import com.example.springboottest.xss.XssFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.DispatcherType;

@SpringBootApplication
@EnableScheduling
@EnableRetry
public class SpringboottestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringboottestApplication.class, args);
	}

	/**
	 * XSS 的Filter注入
	 * 用来处理getParameter的参数
	 * @return
	 */
	@Bean
	public FilterRegistrationBean xssFilterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
}

