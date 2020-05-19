package com.example.springboottest.service.impl;

import com.example.springboottest.service.TestProxyService;

public class TestProxyServiceImpl2 implements TestProxyService {
    TestProxyService testProxyService;

    public TestProxyServiceImpl2(TestProxyService testProxyService) {
        this.testProxyService = testProxyService;
    }
    @Override
    public void query() {
        System.out.println("呵呵呵呵呵呵呵呵呵");
        testProxyService.query();
    }
}
