package com.example.springboottest.test;

import com.example.springboottest.service.TestService;
import org.junit.Test;
import org.springframework.util.StringUtils;


import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Test3 {

    @Test
    public void test1(){
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(yyyyMMdd.format(now));
    }

    @Test
    public void test2(){
        try {
            //Class<?> aClass = Class.forName("com.example.springboottest.service.TestService");
            Class<TestService> aClass = TestService.class;
            Date date = new Date();
            Method getTableAndTime = aClass.getMethod("getTableAndTime",date.getClass());
            Object invoke = getTableAndTime.invoke(aClass.newInstance(),date);
            System.out.println(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        Test1 test1 = SingleDemo.getTest1();
        test1.test3();
    }

    @Test
    public void test4(){
        Test1 test1 = SingleDemo2.getTest1();
        test1.test1();
    }

    @Test
    public void test5(){
        int i = 1024 << 10;
        int j = 1024 >> 10;
        double k = Math.pow(2,10);
        System.out.println(i+":"+j+":"+k);
    }

    @Test
    public void test6(){
        StringBuilder builder = new StringBuilder();
        String str;
        if(StringUtils.isEmpty(builder)){
            str = builder.append(1).toString();
        }else{
            str = builder.toString();
        }
        System.out.println(str);
    }

    @Test
    public void test7(){
        Map<String, Object> map = new HashMap<>();
        map.put("1","1");
        map.put("1","2");
        map.remove("1");
        System.out.println(map);
    }

    @Test
    public void test8() throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        FutureTask<String> task = new FutureTask<>(myCallable);
        Thread thread = new Thread(task);
        thread.start();
        System.out.println(task.get());
    }

    @Test
    public void test9(){
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for(int i = 0;i < 10;i++){
            Future<String> submit = pool.submit(new MyCallable());
            try {
                System.out.println(submit.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }

    @Test
    public void test10(){
        ExecutorService service = Executors.newFixedThreadPool(10);
        SynchronizedExample synchronizedExample1 = new SynchronizedExample();
        SynchronizedExample synchronizedExample2 = new SynchronizedExample();
        service.execute(() -> synchronizedExample1.test());
        service.execute(() -> synchronizedExample2.test());
        service.shutdown();
    }

    @Test
    public void test11() throws InterruptedException {
        int count = 10;
        ExecutorService service = Executors.newFixedThreadPool(count);
        CountDownLatch countDownLatch = new CountDownLatch(count);
        service.execute(() -> {
            for(int i = 0;i < count;i++){
                System.out.println("=================="+countDownLatch.getCount()+"========================");
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        System.out.println("==================shutDown===================");
        service.shutdown();
    }

    @Test
    public void test12() throws InterruptedException {
        int count = 1000;
        ThreadExample threadExample = new ThreadExample();
        ExecutorService service = Executors.newFixedThreadPool(count);
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for(int i = 0;i < count;i++){
            service.execute(() -> {
                threadExample.add();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        service.shutdown();
        System.out.println("e:"+threadExample.get()+"======="+"latch:"+countDownLatch.getCount());
    }

    @Test
    public void test13(){
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(() -> {
            threadLocal.set(1);
            System.out.println(threadLocal.get());
            threadLocal.remove();
        });
        service.execute(() -> {
            threadLocal.set(2);
            System.out.println(threadLocal.get());
            threadLocal.remove();
            System.out.println(threadLocal.get());
        });
        service.shutdown();
    }
}
