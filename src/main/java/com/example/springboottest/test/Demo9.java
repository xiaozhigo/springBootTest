package com.example.springboottest.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo9 {
    public static void main(String[]args) {
        int num = 5;
        test(num);
        System.out.println(num);
    }

    public static void test(int num) {
        num = 10;
        System.out.println("num:"+num);
    }

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> stringList = Collections.synchronizedList(list1);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");

        list.stream().parallel().forEach(e ->{
            stringList.add(e);
        });

        System.out.println(list1);
        System.out.println(stringList);

    }
}
