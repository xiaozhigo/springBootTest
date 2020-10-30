package com.example.springboottest.controller;

import com.example.springboottest.config.BloomFilterConfig;
import com.example.springboottest.dto.UserDto;
import com.example.springboottest.service.Test4Service;
import com.example.springboottest.service.TestService;
import com.example.springboottest.service.impl.HttpAPIService;
import com.google.common.hash.BloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Test4 {

    @Autowired
    private HttpAPIService httpAPIService;
    @Autowired
    private Test4Service test4Service;
    @Autowired
    private BloomFilterConfig bloomFilterConfig;

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

    /**
     * 事务处理设置
     * @return
     */
    @RequestMapping("/transactionTest")
    public void transactionTest(@RequestBody UserDto userDto){
        test4Service.transactionTest(userDto);
    }

    /**
     * 通过布隆过滤器处理缓存穿透
     */
    @RequestMapping("/bloomFilterTest")
    public void bloomFilterTest(@RequestBody String userId){
        BloomFilter bloomFilter = bloomFilterConfig.getBloomFilter();
        boolean flag = bloomFilter.mightContain(userId);
        //布隆过滤器中存在
        if(flag){

        }
    }
}
