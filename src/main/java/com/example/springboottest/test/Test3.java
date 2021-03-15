package com.example.springboottest.test;

import com.example.springboottest.dto.KeyValueModel;
import com.example.springboottest.dto.UserDetailDto;
import com.example.springboottest.service.TestService;
import com.example.springboottest.util.GsonSingle;
import com.google.gson.Gson;
import org.junit.Test;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class Test3 {

    @Test
    public void test1() {
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(yyyyMMdd.format(now));
    }

    @Test
    public void test2() {
        try {
            //Class<?> aClass = Class.forName("com.example.springboottest.service.TestService");
            Class<TestService> aClass = TestService.class;
            Date date = new Date();
            Method getTableAndTime = aClass.getMethod("getTableAndTime", date.getClass());
            Object invoke = getTableAndTime.invoke(aClass.newInstance(), date);
            System.out.println(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        Test1 test1 = SingleDemo.getTest1();
        test1.test3();
    }

    @Test
    public void test4() {
        Test1 test1 = SingleDemo2.getTest1();
        test1.test1();
    }


    @Test
    public void test5() {
        List<KeyValueModel<Object>> list = new ArrayList<>();
        KeyValueModel<Object> valueModel = new KeyValueModel<>();
        valueModel.setKey("id");
        valueModel.setVaule(2);
        list.add(valueModel);
        String id = "";
        for (KeyValueModel<Object> objectKeyValueModel : list) {
            String s = "id".equals(objectKeyValueModel.getKey()) ? objectKeyValueModel.getVaule().toString() : "";
            id = s;
        }
        System.out.println(id);
    }

    @Test
    public void test6() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> collect = list.stream().filter(str -> str == 1).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test7() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = list.parallelStream().map(i -> i * 2).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test8() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName() + "开始");
                }
            };
            service.execute(runnable);
        }
        try {
            System.out.println(Thread.currentThread().getName() + "开始等待");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }

    @Test
    public void test9() {
        Optional<Object> optional = Optional.ofNullable(null);
        Object o = optional.orElse(0);
        Object elseGet = optional.orElseGet(() -> 1);
        boolean present = optional.isPresent();
        System.out.println(present);
        System.out.println(o);
        System.out.println(elseGet);
    }

    @Test
    public void test10() {
        Optional<Object> optional = Optional.ofNullable(1);
        Object aThrow = optional.orElseThrow(() -> new IllegalArgumentException());
        System.out.println(aThrow);
    }

    @Test
    public void test11() {
        UserDetailDto userDto = new UserDetailDto("tomcat", "cat", null);
        Integer integer = Optional.ofNullable(userDto).map(u -> u.getUserId()).orElse(2);
        System.out.println(integer);
    }

    @Test
    public void test12() {
        UserDetailDto userDetailDto = new UserDetailDto("tomcat", "cat", null);
        Optional<UserDetailDto> result = Optional.ofNullable(userDetailDto).filter(u -> u.getUserId() != null && u.getDetailName().contains("c"));
        System.out.println(result.isPresent());
        System.out.println(result);
    }

    @Test
    public void test13() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue<String>(1);
        blockingQueue.add("1");
        System.out.println(blockingQueue);
        blockingQueue.remove();
        System.out.println(blockingQueue);
        blockingQueue.remove();
    }

    @Test
    public void test14() {
        BlockingQueue queue = new ArrayBlockingQueue<String>(1);
        queue.offer("1");
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
        queue.poll();
    }

    @Test
    public void test15() {
        BlockingQueue queue = new ArrayBlockingQueue<String>(1);
        try {
            queue.put("1");
            System.out.println(queue);
            queue.take();
            System.out.println(queue);
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test16() {
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(processors*2, processors * 4, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 5; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("????");
                }
            });
        }
        executor.shutdown();
    }

    @Test
    public void test17(){
        Gson instance = GsonSingle.INSTANCE.getInstance();
        Gson instance1 = GsonSingle.INSTANCE.getInstance();
        System.out.println(instance == instance1);
    }


    @Test
    public void test18() throws Exception {
        Class<GsonSingle> gsonSingleClass = GsonSingle.class;
        Constructor<GsonSingle> declaredConstructor = gsonSingleClass.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        GsonSingle gsonSingle = declaredConstructor.newInstance(String.class,int.class);
        Gson instance = gsonSingle.getInstance();
        instance.toString();
    }

    @Test
    public void test19(){
        LocalDateTime now = LocalDateTime.now();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String format = dateTimeFormatter.format(now);
        System.out.println(format);
    }

    @Test
    public void test20(){
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime of = LocalDateTime.ofInstant(instant, zoneId);
        System.out.println(of);
    }

    @Test
    public void test21(){
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = now.atZone(zoneId).toInstant();
        Date from = Date.from(instant);
        System.out.println(from);
    }

    @Test
    public void test22() throws NoSuchAlgorithmException {
        SecureRandom instanceStrong = SecureRandom.getInstanceStrong();
        instanceStrong.setSeed(1L);
        int i = instanceStrong.nextInt();
        System.out.println(i);
    }

    @Test
    public void test23(){
        LongAdder longAdder = new LongAdder();
        longAdder.add(100L);
        System.out.println(longAdder);
        longAdder.add(1L);
        System.out.println(longAdder);
    }

}
