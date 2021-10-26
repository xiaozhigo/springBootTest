package com.example.springboottest.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolConfig {
    @Bean
    public Executor getAsyncExecutor(){
        //线程工厂设置打印异常信息
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setUncaughtExceptionHandler((thread, throwable)-> log.error("ThreadPool {} got exception", thread,throwable))
                .build();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        /*<!--设置线程工厂 -->*/
        threadPoolTaskExecutor.setThreadFactory(namedThreadFactory);
        /*<!--核心线程数 -->*/
        threadPoolTaskExecutor.setCorePoolSize(2500);
        /*<!--最大线程数 -->*/
        threadPoolTaskExecutor.setMaxPoolSize(5000);
        /* <!-- 队列大小 -->*/
        threadPoolTaskExecutor.setQueueCapacity(9999);
        /*线程前缀*/
        threadPoolTaskExecutor.setThreadNamePrefix("async");
        /* <!-- AbortPolicy:直接抛出java.util.concurrent.RejectedExecutionException异常 -->
        <!-- CallerRunsPolicy:主线程直接执行该任务，执行完之后尝试添加下一个任务到线程池中，可以有效降低向线程池内添加任务的速度 -->
        <!-- DiscardOldestPolicy:抛弃旧的任务、暂不支持；会导致被丢弃的任务无法再次被执行 -->
        <!-- DiscardPolicy:抛弃当前任务、暂不支持；会导致被丢弃的任务无法再次被执行 -->*/
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
