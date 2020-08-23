package com.example.springboottest.test;

import com.example.springboottest.service.TestService;
import org.junit.Test;


import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
}
