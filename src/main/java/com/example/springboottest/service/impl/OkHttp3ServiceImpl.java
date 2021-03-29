package com.example.springboottest.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.springboottest.service.OkHttp3Service;
import okhttp3.*;

import java.util.Map;

/**
 * @author ：李国鹏
 * @Date ：Created in 10:43 AM 2020/3/10
 * @Description：
 * @Modified By：
 * @Version: 1.0.0
 */
public class OkHttp3ServiceImpl implements OkHttp3Service {

    private OkHttpClient okHttpClient;

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    @Override
    public String get(String url) {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()//默认就是GET请求，可以不写
                    .build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String postFormData(String url, Map<String, String> param) {
        FormBody.Builder formBody    = new FormBody.Builder();
        for(Map.Entry<String,String> entry:param.entrySet()){
            formBody.add(entry.getKey(),entry.getValue());
        }
        return postData(url,formBody.build());
    }

    @Override
    public String postJsonData(String url, Object param) {
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(param),MediaType.parse("application/json; charset=utf-8"));
        return postData(url,requestBody);
    }

    @Override
    public String postJsonString(String url, String str) {
        RequestBody requestBody = RequestBody.create(str,MediaType.parse("application/json; charset=utf-8"));
        return postData(url,requestBody);
    }

    private String postData(String url,RequestBody requestBody){
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
