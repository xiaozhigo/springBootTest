package com.example.springboottest.test;

import com.example.springboottest.dto.KeyValueModel;
import com.example.springboottest.service.TestService;
import org.junit.Test;


import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Test3 {

    @Test
    public void test1(){
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(yyyyMMdd.format(now));
    }

    @Test
    public void test2(){
        try {
            //Class<?> aClass = Class.forName("com.example.springboottest.service.TestService");
            Class<TestService> aClass = TestService.class;
            Date date = new Date();
            Method getTableAndTime = aClass.getMethod("getTableAndTime",date.getClass());
            Object invoke = getTableAndTime.invoke(aClass.newInstance(),date);
            System.out.println(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        Test1 test1 = SingleDemo.getTest1();
        test1.test3();
    }

    @Test
    public void test4(){
        Test1 test1 = SingleDemo2.getTest1();
        test1.test1();
    }


    @Test
    public void test5(){
        List<KeyValueModel<Object>> list = new ArrayList<>();
        KeyValueModel<Object> valueModel = new KeyValueModel<>();
        valueModel.setKey("id");
        valueModel.setVaule(2);
        list.add(valueModel);
        String id = "";
        for (KeyValueModel<Object> objectKeyValueModel : list) {
            String s = "id".equals(objectKeyValueModel.getKey()) ? objectKeyValueModel.getVaule().toString() : "";
            id = s;
        }
        System.out.println(id);
    }
}
