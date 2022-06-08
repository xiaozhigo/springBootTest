package com.example.springboottest.aop;

import com.example.springboottest.annotation.TestAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切面类
 */
@Aspect
@Component
@Slf4j
public class AopTest {

    //定义切入点
    @Pointcut("@annotation(com.example.springboottest.annotation.TestAnnotation)")
    public void test(){}

    @Around("test()")
    public Object around(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取添加注解的方法
        Method method = signature.getMethod();
        //获取到注解
        TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
        //获取注解中的值
        String value = annotation.value();
        Object proceed = null;
        try {
            //调用方法
            log.info("annotation中的值为:"+value);
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("报错了,错误信息为:"+throwable.toString());
        }
        return proceed;
    }
}
