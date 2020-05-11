package com.example.springboottest.test;

import java.util.concurrent.ConcurrentLinkedDeque;

public class CurrentCollection {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDeque<Object> deque = new ConcurrentLinkedDeque<>();
        Thread[] thread = new Thread[100];
        for(int i =0;i < 100;i++){
            thread[i] = new Thread(()->{
                for(int j = 0;j < 10000;j++){
                    deque.add(Thread.currentThread().getName());
                }
            });
            thread[i].start();
            thread[i].join();
        }

        System.out.println(deque.size());

        for(int i =0;i < 100;i++){
            thread[i] = new Thread(()->{
                for(int j = 0;j < 10000;j++){
                    deque.poll();
                }
            });
            thread[i].start();
            thread[i].join();
        }

        System.out.println(deque.size());
    }
}
