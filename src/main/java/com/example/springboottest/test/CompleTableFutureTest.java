package com.example.springboottest.test;

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
}
