package com.example.springboottest.service.impl;

import com.example.springboottest.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RetryServiceImpl implements RetryService {

    @Override
    @Retryable(value = {Exception.class},maxAttempts = 3,backoff = @Backoff(delay = 1000L,multiplier = 1.5))
    public void call() {
        log.info("~~~~~~重试测试开始~~~~~~~");
        try {
            throw new Exception("测试异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("~~~~~~重试测试结束~~~~~~~");
    }

    /**
     * 重试返回
     * @param e
     */
    @Recover
    public void recover(Exception e) {
        System.out.println(e.getMessage());
    }
}
