package com.example.springboottest.controller;

import com.example.springboottest.service.impl.HttpAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Test4 {

    @Autowired
    private HttpAPIService httpAPIService;

    @RequestMapping("/test1")
    public void test1(){
        try {
            //query 检索关键字 tag检索偏好 region 检索行政区划区域 output=json ak百度开发者访问密钥
            String s = httpAPIService.doGet("http://api.map.baidu.com/place/v2/search?query=ATM机&tag=银行&region=北京&output=json&ak=stlbPbu14MAXvcIMoR39D8vfQycPFGil");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
