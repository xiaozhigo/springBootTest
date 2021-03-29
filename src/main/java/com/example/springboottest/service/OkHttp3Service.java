package com.example.springboottest.service;

import java.util.Map;

/**
 * @author ：李国鹏
 * @Date ：Created in 10:40 AM 2020/3/10
 * @Description：
 * @Modified By：
 * @Version: 1.0.0
 */
public interface OkHttp3Service {

    String get(String url) ;

    String postFormData(String url, Map<String, String> param);

    String postJsonData(String url, Object param);

    String postJsonString(String url, String str);

}
