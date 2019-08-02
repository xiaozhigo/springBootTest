package com.example.springboottest.test;

import org.junit.Test;
import org.springframework.scheduling.annotation.Async;

public class TestDemo {

    /*@Test
    @Async("getAsyncExecutor")*/
    public void test1(){
        long l = Long.valueOf("100000000000");
         for(long i = 0; i < l;i++){
             System.out.println(Thread.currentThread().getName()+":"+i);
         }
    }

    public static void main(String[] args) {
        class ThreadA extends Thread{

            public ThreadA(String name) {
                super(name);
            }

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"进入run方法");
               synchronized (this){
                   System.out.println(Thread.currentThread().getName()+"正在执行");

                   System.out.println(Thread.currentThread().getName()+"正在执行notify");
                   notify();
               }

            }
        }

        ThreadA t800 = new ThreadA("t800");
        synchronized (t800){
            System.out.println(Thread.currentThread().getName()+"开始启动");
            t800.start();
            System.out.println(Thread.currentThread().getName()+"开始执行wait");
            try {
                t800.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行结束");
        }
    }
}
