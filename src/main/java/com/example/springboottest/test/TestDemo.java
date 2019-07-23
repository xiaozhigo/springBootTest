package com.example.springboottest.test;

import org.junit.Test;
import org.springframework.scheduling.annotation.Async;

public class TestDemo {

    @Test
    @Async("getAsyncExecutor")
    public void test1(){
        long l = Long.valueOf("100000000000");
         for(long i = 0; i < l;i++){
             System.out.println(Thread.currentThread().getName()+":"+i);
         }
    }
}
