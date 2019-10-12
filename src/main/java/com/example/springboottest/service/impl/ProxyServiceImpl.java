package com.example.springboottest.service.impl;

import com.example.springboottest.service.ProxyService;
import java.util.ArrayList;
import java.util.List;

public class ProxyServiceImpl implements ProxyService {

    @Override
    public String queryAllUser() {
        List<String> list = new ArrayList<>();
        list.add("狗蛋");
        list.add("二丫");
        list.add("古二蛋");
        return list.toString();
    }
}
