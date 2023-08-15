package com.example.springboottest.config;

import com.example.springboottest.xss.XssJacksonDeserializer;
import com.example.springboottest.xss.XssJacksonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Component
public class WebConfig implements WebMvcConfigurer{

    /**
     * 全局跨域
     * @param registry
     */
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }*/

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        ObjectMapper mapper = builder.build();
        /*注入自定义的序列化工具，将RequestBody的参数进行转译后传输*/
        SimpleModule simpleModule = new SimpleModule();
        // XSS序列化
        simpleModule.addSerializer(String.class, new XssJacksonSerializer());
        simpleModule.addDeserializer(String.class, new XssJacksonDeserializer());
        mapper.registerModule(simpleModule);
        converters.add(new MappingJackson2HttpMessageConverter(mapper));
    }
}
