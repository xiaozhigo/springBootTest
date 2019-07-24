package com.example.springboottest.test;


public class ThreadDemo3 {
    public static void main(String[] args) {
        class ThreadA extends Thread{
            public ThreadA(String name) {
                super(name);
            }

            @Override
            public synchronized void run() {
                for(int i = 0;i < 10;i++){
                    System.out.println(this.getName()+":"+i);
                    if(i % 2 == 0){
                       Thread.yield();
                    }
                }
            }
        }
        ThreadA Ti1050 = new ThreadA("Ti1050");
        ThreadA ti1080 = new ThreadA("Ti1080");
        Ti1050.start();
        ti1080.start();
    }


}
