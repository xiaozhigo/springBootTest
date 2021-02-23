package com.example.springboottest.test;

import com.example.springboottest.dto.KeyValueModel;
import com.example.springboottest.dto.UserDetailDto;
import com.example.springboottest.dto.UserDto;
import com.example.springboottest.service.TestService;
import org.junit.Test;


import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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

    @Test
    public void test6(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> collect = list.stream().filter(str -> str==1).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test7(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = list.parallelStream().map(i -> i * 2).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test8(){
        Optional<Object> optional = Optional.ofNullable(null);
        Object o = optional.orElse(0);
        Object elseGet = optional.orElseGet(() -> 1);
        boolean present = optional.isPresent();
        System.out.println(present);
        System.out.println(o);
        System.out.println(elseGet);
    }

    @Test
    public void test9(){
        Optional<Object> optional = Optional.ofNullable(1);
        Object aThrow = optional.orElseThrow(() -> new IllegalArgumentException());
        System.out.println(aThrow);
    }

    @Test
    public void test10(){
        UserDetailDto userDto = new UserDetailDto("tomcat","cat",null);
        Integer integer = Optional.ofNullable(userDto).map(u -> u.getUserId()).orElse(2);
        System.out.println(integer);
    }

    @Test
    public void test11(){
        UserDetailDto userDetailDto = new UserDetailDto("tomcat","cat",null);
        Optional<UserDetailDto> result = Optional.ofNullable(userDetailDto).filter(u -> u.getUserId() != null && u.getDetailName().contains("c"));
        System.out.println(result.isPresent());
        System.out.println(result);
    }
}
