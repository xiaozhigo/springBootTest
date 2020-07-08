package com.example.springboottest.controller;

import com.alibaba.fastjson.JSONObject;
/*import com.dateformat.DateFormatProperties;*/
import com.example.springboottest.annotation.TestAnnotation;
import com.example.springboottest.proxy.InvoiceProxy;
import com.example.springboottest.service.ProxyService;
import com.example.springboottest.service.TestService;
import com.example.springboottest.service.impl.ProxyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
