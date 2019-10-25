package com.example.springboottest.test;

public class ThreadExample {
    private int cnt = 0;

    public synchronized void add(){
        cnt+=1;
    }

    public int get(){
        return cnt;
    }
}
