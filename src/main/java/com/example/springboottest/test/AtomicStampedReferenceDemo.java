package com.example.springboottest.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {

    private static AtomicInteger integer = new AtomicInteger(0);

    private static AtomicStampedReference<Integer> stamp = new AtomicStampedReference(0,1);

    static int add(){
        return integer.incrementAndGet();
    }

    static int delete(){
        return integer.decrementAndGet();
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            int stampVersion = AtomicStampedReferenceDemo.stamp.getStamp();
            System.out.println(Thread.currentThread().getName() + "------版本号:" + stampVersion + "-----内容:" + stamp.getReference());
            stamp.compareAndSet(integer.get(), add(), stamp.getStamp(), stamp.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "------版本号:" + stamp.getStamp() + "-----内容:" + stamp.getReference());
            stamp.compareAndSet(integer.get(), delete(), stamp.getStamp(), stamp.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "------版本号:" + stamp.getStamp() + "-----内容:" + stamp.getReference());
        }, "线程一");
        Thread thread2 = new Thread(() -> {
            stamp.compareAndSet(0, 10, stamp.getStamp(), stamp.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "------版本号:" + stamp.getStamp() + "-----内容:" + stamp.getReference());
        }, "线程二");
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

}
