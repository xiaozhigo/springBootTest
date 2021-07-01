package com.example.springboottest.controller;

import com.alibaba.fastjson.JSONObject;
/*import com.dateformat.DateFormatProperties;*/
import com.example.springboottest.annotation.TestAnnotation;
import com.example.springboottest.config.KafkaProducer;
import com.example.springboottest.dto.UserDto;
import com.example.springboottest.proxy.InvoiceProxy;
import com.example.springboottest.service.OkHttp3Service;
import com.example.springboottest.service.ProxyService;
import com.example.springboottest.service.RetryService;
import com.example.springboottest.service.TestService;
import com.example.springboottest.service.impl.ProxyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2019-01-22 16:38
 */
@RestController
public class TestController {

    @Autowired
    public TestService testService;
    @Autowired
    private OkHttp3Service okHttp3Service;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private RetryService retryService;

    @RequestMapping("/createTime")
    @TestAnnotation("1")
    public List<Map<String, Object>> createTime(){
           return testService.getTableAndTime(new Date());
    }

    @RequestMapping("/microServiceCallVolume")
    public String microServiceCallVolume(@RequestBody String requestBody){
           Map<String, Object> response = new HashMap<>();
           try{
               JSONObject head = JSONObject.parseObject(requestBody);
               String microServiceName = head.getString("microServiceName");
               response = testService.microServiceCallVolume(microServiceName);
           }catch (Exception e){
               e.printStackTrace();
               response.put("resDesc","查询微服务调用量失败:"+e.toString());
               response.put("resCode","9999");
           }
           return JSONObject.toJSONString(response);
    }

    @RequestMapping("/queryAllUser")
    public String queryAllUser(){
       return testService.queryAllUser();
    }

    /**
     * 动态代理测试
     * @return
     */
    @RequestMapping("/proxyTest")
    public String proxyTest(){
        InvoiceProxy invoiceProxy = new InvoiceProxy();
        ProxyService proxyService = (ProxyService) invoiceProxy.newProxyInstance(new ProxyServiceImpl());
        return proxyService.queryAllUser();
    }


    @RequestMapping("/ok3Test")
    public String ok3Test(){
        String s = okHttp3Service.get("http://localhost:9005/everyapi/crontabRest/overTimeCheckHandle");
        return s;
    }

    @RequestMapping("/kafkaTest")
    public String kafkaTest(@RequestParam("message") String message){
        try{
            kafkaProducer.send(message);
            return "kafka发送消息成功";
        }catch (Exception e){
            return "kafka发送消息失败,失败原因:"+e.toString();
        }
    }

    /**
     * 重试功能测试
     */
    @RequestMapping(value = "retryTest",method = RequestMethod.GET)
    public void retryTest(){
        retryService.call();
    }

}
