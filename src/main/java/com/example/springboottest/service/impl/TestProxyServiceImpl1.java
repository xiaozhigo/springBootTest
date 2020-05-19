package com.example.springboottest.service.impl;

import com.example.springboottest.service.TestProxyService;

public class TestProxyServiceImpl1 implements TestProxyService {

    TestProxyService testProxyService;

    public TestProxyServiceImpl1(TestProxyService testProxyService) {
        this.testProxyService = testProxyService;
    }

    @Override
    public void query() {
        System.out.println("哈哈哈哈哈哈哈哈哈哈或或或或或");
        testProxyService.query();
    }
}
