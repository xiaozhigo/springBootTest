package com.example.springboottest.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 使用okhttp作为 默认的 restTemplate
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);
        return factory;
    }

    @Bean
    public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory(OkHttpClient okhttp3Client) {
        return new OkHttp3ClientHttpRequestFactory(okhttp3Client);
    }

    @Bean(name = "restTemplate")
    public RestTemplate restOkTemplate(ClientHttpRequestFactory okHttp3ClientHttpRequestFactory){
        return new RestTemplate(okHttp3ClientHttpRequestFactory);
    }






}