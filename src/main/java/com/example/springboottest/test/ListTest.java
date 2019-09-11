package com.example.springboottest.test;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * unmodifiableCollection使用
 */
public class ListTest {
    public static void main(String[] args) {
        final List<String> list = new ArrayList<>();
        final String[] str = {"1","2","3","4"};
        Collections.addAll(list,str);
        Collection<String> lists = Collections.unmodifiableCollection(list);
        lists.add("5");
        System.out.println(list.toString());
    }
}
