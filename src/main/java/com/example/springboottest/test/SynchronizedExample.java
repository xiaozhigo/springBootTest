package com.example.springboottest.test;

public class SynchronizedExample {
    public void test(){
        //synchronized (SynchronizedExample.class){
            for(int i = 0;i < 10 ;i++){
                System.out.println("---------------"+i+"---------------");
            //}
        }
    }
}
