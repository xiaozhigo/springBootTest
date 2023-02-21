package com.example.springboottest.test;

import com.example.springboottest.design.cglib.CglibFactory;
import com.example.springboottest.design.cglib.CglibService;
import com.example.springboottest.design.cglib.CglibServiceImpl;

public class CglibTest {
    public static void main(String[] args) {
        CglibService cglibService = (CglibService) CglibFactory.getCglibProxy(CglibServiceImpl.class);
        cglibService.sendMessage("发送");
    }
}
