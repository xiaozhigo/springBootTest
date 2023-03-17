package com.example.springboottest.test;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class CompleTableFutureTest {
    public static void main(String[] args) {
        CompletableFuture<Void> exceptionally = CompletableFuture.supplyAsync(CompleTableFutureTest::sout)
                .thenAccept(result -> {
                    System.out.println("异步打印:" + result);
                })
                .exceptionally(e -> {
                    e.printStackTrace();
                    return null;
                });
            exceptionally.join();
    }

    static String sout(){
        try {
            Thread.sleep(1000);
            throw new RuntimeException("抛出异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1";
    }

   @Test
   public void test(){
       CompletableFuture<Void> task1 =
               CompletableFuture.supplyAsync(()->{
                   //自定义业务操作
                   System.out.println("task1");
                   return null;
               });

       CompletableFuture<Void> task6 =
               CompletableFuture.supplyAsync(()->{
                   //自定义业务操作
                   System.out.println("task6");
                   return null;
               });

       CompletableFuture<Void> headerFuture = CompletableFuture.allOf(task1,task6);

       try {
           headerFuture.join();
       } catch (Exception ex) {
           //......
       }
       System.out.println("all done. ");
   }
}
