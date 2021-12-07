package com.example.springboottest.service.impl;

import com.example.springboottest.service.SuperLoggerConfiguration;

public class XMLConfiguration implements SuperLoggerConfiguration {
    @Override
    public void configure() {
        System.out.println("XML打印");
    }
}
