package com.example.springboottest.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    public static final ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void LogAspectMethod() {
    }

    @Before("LogAspectMethod()")
    public void doBefore(JoinPoint joinPoint) {
        threadLocal.set(System.currentTimeMillis());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (log.isDebugEnabled()) {
            log.info("SERVICE METHOD: {} ; VAR:{}", joinPoint.getSignature().getDeclaringType().getSimpleName() + "#" + signature.getMethod().getName(), JSONObject.toJSONString(joinPoint.getArgs()));
        }
    }

    @After("LogAspectMethod()")
    public void doAround(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (log.isDebugEnabled()) {
            log.info("SERVICE METHOD: {} ; 耗时:{}ms", joinPoint.getSignature().getDeclaringType().getSimpleName() + "#" + signature.getMethod().getName(), System.currentTimeMillis() - threadLocal.get());
        }
        threadLocal.remove();
    }

    @AfterThrowing(pointcut = "LogAspectMethod()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Throwable e) {
        threadLocal.remove();
        log.error(e.getMessage(),e);
    }


}
