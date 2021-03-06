package com.example.springboottest.test;

import com.example.springboottest.dto.KeyValueModel;
import com.example.springboottest.dto.UserDetailDto;
import com.example.springboottest.dto.UserDto;
import com.example.springboottest.service.TestService;
import com.example.springboottest.util.GsonSingle;
import com.google.gson.Gson;
import com.sun.deploy.util.SyncFileAccess;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Test
    public void nioTest() throws IOException {
        FileInputStream stream = new FileInputStream(new File("C:\\Users\\wulei\\IdeaProjects\\springBootTest\\src\\main\\resources\\新建文本文档.txt"));
        FileChannel channel = stream.getChannel();
        ByteBuffer bf = ByteBuffer.allocate(1024);
        channel.read(bf);
        System.out.println(new String(bf.array()));
        stream.close();
    }

    @Test
    public void aioTest() throws IOException, ExecutionException, InterruptedException {
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Paths.get("C:\\Users\\wulei\\IdeaProjects\\springBootTest\\src\\main\\resources\\新建文本文档.txt"), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> result = fileChannel.read(buffer, 0);
        while (!result.isDone());
        buffer.flip();
        System.out.println("主线程执行完毕");
        Integer bytesRead = result.get();
        System.out.println(buffer.position());
        System.out.println(Charset.forName("UTF-8").decode(buffer).toString());
        System.out.println("Bytes read [" + bytesRead + "]");
        fileChannel.close();
    }

    @Test
    public void stringJoinTest(){
        StringJoiner joiner = new StringJoiner(",", "前缀", "后缀");
        joiner.add("1").add("2").add("3");
        System.out.println(joiner.toString());
    }

    @Test
    public void stopWatchTest() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(1000);
        System.out.println("---------"+stopWatch.getTime());
        Thread.sleep(1000);
        stopWatch.split();
        System.out.println(">>>>>>>>>"+stopWatch.getSplitTime());
        stopWatch.reset();
        stopWatch.start();
        Thread.sleep(2000);
        System.out.println(stopWatch.getTime());
        stopWatch.suspend();
        System.out.println("暂停2秒钟");
        Thread.sleep(2000);
        stopWatch.resume();
        Thread.sleep(1000);
        stopWatch.stop();
        System.out.println("<<<<<<<<"+stopWatch.getTime());
    }

    @Test
    public void lockTest(){
        CountDownLatch latch = new CountDownLatch(10);
        AtomicInteger integer = new AtomicInteger();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        IntStream.range(0,10).forEach(e ->{
            try {
                Thread.sleep(5);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //加锁
                    boolean set = integer.compareAndSet(0, 1);
                    if(set){
                        System.out.println(Thread.currentThread().getName()+"获取锁成功");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        //解锁
                        integer.set(0);
                        System.out.println("初始值:"+integer);
                        latch.countDown();
                    }else{
                        System.out.println(Thread.currentThread().getName()+"未获取到锁");
                        latch.countDown();
                    }
                }
            });
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void setTest(){
        List<UserDetailDto> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
             UserDetailDto dto = new UserDetailDto();
             dto.setUserId(i);
             dto.setDetailName("d"+i);
             dto.setDetailMessage("m"+i);
             list.add(dto);
        }
        Set<Integer> set = list.stream().filter(e ->(e.getUserId()==1)).map(UserDetailDto::getUserId).collect(Collectors.toSet());
        List<UserDetailDto> dtoList = list.stream().filter(e -> set.contains(e.getUserId())).collect(Collectors.toList());
        System.out.println(dtoList);
    }


    @Test
    public void IntegerTest(){
        Integer integer1 = new Integer(100);
        Integer integer2 = 100;
        int i1 = 100;
        Integer integer3 = new Integer(130);
        Integer integer4 = 130;
        int i2 = 130;
        System.out.println(integer1 == integer2);
        System.out.println(integer1.equals(integer2));
        System.out.println(i1 == integer1);
        System.out.println(i1 == integer2);


        System.out.println(integer3 == integer4);
        System.out.println(integer3.equals(integer4));
        System.out.println(i2 == integer3);
        System.out.println(i2 == integer4);
    }

    @Test
    public void builderTest(){
        BuilderTest test = new BuilderTest.UserBuilder().setId(1).setName("二哈").setList(Arrays.asList("1", "2", "3")).bulider();
        System.out.println(test.getId()+","+test.getName()+","+test.getList());
    }
}
