package com.example.springboottest.test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {
        Map<String, Object> map = new TreeMap<>();
        Map<String, Object> hashMap = new LinkedHashMap<>();
        map.put("user","user");
        map.put("account","account");
        map.put("customer","account");
        hashMap.put("user66","user66");
        hashMap.put("user1","user1");
        hashMap.put("user3","user3");
        System.out.println(map);
        System.out.println(hashMap);
    }
}
