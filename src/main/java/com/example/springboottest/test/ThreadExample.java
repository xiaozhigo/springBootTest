package com.example.springboottest.test;

public class ThreadExample {
    private String cnt = "0";

    public synchronized void add(){
        cnt=Integer.parseInt(cnt)+1+"";
    }

    public String get(){
        return cnt;
    }
}
