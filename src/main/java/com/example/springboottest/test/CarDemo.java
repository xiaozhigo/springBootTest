package com.example.springboottest.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CarDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        Thread[] thread = new Thread[10];
        for(int i = 0;i < 10;i++){
            thread[i] = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"获取信号量!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    TimeUnit.SECONDS.sleep(2);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"信号量释放");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"car"+i);
            thread[i].start();

        }
    }
}
