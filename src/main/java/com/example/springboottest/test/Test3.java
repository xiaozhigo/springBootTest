package com.example.springboottest.test;

import cn.hutool.dfa.WordTree;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboottest.dto.KeyValueModel;
import com.example.springboottest.dto.Plain;
import com.example.springboottest.dto.UserDetailDto;
import com.example.springboottest.service.SuperLoggerConfiguration;
import com.example.springboottest.service.TestService;
import com.example.springboottest.util.GsonSingle;
import com.google.gson.Gson;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
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
                    System.out.println(Thread.currentThread().getName() + "开始");
                    countDownLatch.countDown();
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

    @Test
    public void ThreadExceptionTest() throws InterruptedException {
        System.out.println("运行");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("运行线程");
                    throw new RuntimeException("~~~~~~~~~~抛出异常~~~~~~~~");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
        thread.start();
        thread.join();
    }

    private static int jj;

    public static int test24(){
      int i = 2;
      int j = 6;
      int k = i+j;
      return k;
     }

    public static void main(String[] args) {
        int kk = test24();
        System.out.println(kk);
        System.out.println(jj);
    }

    @Test
    public void test25(){
        List<String> list = Arrays.asList("1", "2", "3");
        list.forEach(e ->{
            if("1".equals(e)){
                return;
            }
            System.out.println("--"+e+"--");
        });

        list.stream().forEach(e ->{
            if ("1".equals(e)){
                return;
            }
            System.out.println("<<"+e+">>");
        });
    }

    @Test
    public void test26(){
        List<UserDetailDto> list = new ArrayList<>();
        HashMap<String, UserDetailDto> map = new HashMap<>();
        List<UserDetailDto> arrayList = new ArrayList<>();
        for (int i = 0;i < 5;i++){
            UserDetailDto userDetailDto = new UserDetailDto();
            userDetailDto.setUserId(i);
            list.add(userDetailDto);
        }
        list.forEach(e ->{
            map.put(e.getUserId()+"",e);
        });
        for (int i = 0;i < 4;i++){
             if(!Objects.isNull(map.get(i+""))){
                 UserDetailDto detailDto = map.get(i + "");
                 detailDto.setDetailName("name"+i);
                 detailDto.setDetailMessage("message"+i);
                 arrayList.add(detailDto);
             }
        }
        Iterator<Map.Entry<String, UserDetailDto>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, UserDetailDto> entry = iterator.next();
            System.out.println(entry.getValue());
        }
        for (UserDetailDto detailDto : arrayList) {
            System.out.println("detailDto:"+detailDto);
        }
    }

    @Test
    public void testSPI(){
        ServiceLoader<SuperLoggerConfiguration> serviceLoader = ServiceLoader.load(SuperLoggerConfiguration.class);
        Iterator<SuperLoggerConfiguration> iterator = serviceLoader.iterator();
        SuperLoggerConfiguration configuration = null;

        while(iterator.hasNext()) {
            //加载并初始化实现类
            configuration = iterator.next();
        }

        //对最后一个configuration类调用configure方法
        assert configuration != null;
        configuration.configure();
    }

    @Test
    public void testWordTree(){
        WordTree tree = new WordTree();
        tree.addWord("大");
        tree.addWord("大憨憨");
        tree.addWord("憨憨");
        String text = "那人真是个大憨憨！";
        boolean match = tree.isMatch(text);
        System.out.println(match);
        List<String> all = tree.matchAll(text, -1, false, false);
        System.out.println(all);
        List<String> strs = tree.matchAll(text, -1, false, true);
        System.out.println(strs);
        List<String> strs1 = tree.matchAll(text, -1, true, false);
        System.out.println(strs1);
        List<String> strList = tree.matchAll(text, -1, true, true);
        System.out.println(strList);
    }

    @Test
    public void test27(){
        int i = 0;
        int a = i++;
        int b = a+1;
        System.out.println(i);
        System.out.println(a);
        System.out.println(b);
    }


    @Test
    public void test29(){
        // 随机数类实例化
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        // 64位数字数组
        int[] number = new int[64];
        // 循环变量
        int i = 0;
        // 生成64位随机数算法
        for (i = 0; i < 64; i++) {
            if (number[i] == 0) {
        // 产生0-10之间的随机小数，强制转换成正数
                number[i] = (int) (Math.random() * 10);
            }
         // 输出数字
            System.out.print(number[i] + "");
            str.append(number[i] + "");
        }

        System.out.println("/n"+str.toString().length());
    }

    @Test
    public void test30(){
        String str = "{\"userName\":\"张三\",\"imei\":\"20\",\"sex\":\"男\"}";
        Plain plain = JSON.parseObject(str, Plain.class);
        System.out.println(plain);
    }
}
