package com.example.springboottest.service.impl;

import com.example.springboottest.service.SuperLoggerConfiguration;

public class YMLConfiguration implements SuperLoggerConfiguration {
    @Override
    public void configure() {
        System.out.println("YML打印");
    }
}
