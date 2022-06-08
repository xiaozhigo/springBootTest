package com.example.springboottest.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    public static void main(String[] args) {
        StampedLock lock = new StampedLock();
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.forEach(e ->{
            Thread thread = new Thread();
            long l = lock.writeLock();
            try {
                Thread.sleep(1000);
                System.out.println(e);
            }catch (Exception ex){
            }finally {
                lock.unlockWrite(l);
            }

        });

    }
}
