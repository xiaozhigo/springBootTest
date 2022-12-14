package com.example.springboottest.test;

import com.alibaba.fastjson.JSONObject;
import com.github.rholder.retry.*;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class RetryTest {

    @Test
    public void test(){
        Retryer<JSONObject> retryer = RetryerBuilder.<JSONObject>newBuilder()
                .retryIfException()
                .retryIfResult(res -> {
                    if (null == res) {
                        return true;
                    }
                    System.out.println(res);
                    return !"0000".equals(res.getString("errorNo"));
                })
                .withWaitStrategy(WaitStrategies.fixedWait(5, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .build();
        try {
            retryer.call(this::returnTest);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
    }

    private JSONObject returnTest(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorNo","9999");
        return jsonObject;
    }
}
