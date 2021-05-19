package com.example.springboottest.config;

import org.junit.Assert;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;

public class MyApplicationContext implements ApplicationContextAware{

    private static ApplicationContext myapplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        myapplicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        //assertContextInjected();
        return myapplicationContext;
    }

    public static <T> T getBean(String beanName){
        assertContextInjected();
        return (T) myapplicationContext.getBean(beanName);
    }


    public static  <T> T getBean(String beanName,Class<T> tClass){
        assertContextInjected();
        T bean = myapplicationContext.getBean(beanName, tClass);
        return bean;
    }

    //判断application是否为空
    public static void assertContextInjected(){
        boolean flag = Optional.ofNullable(myapplicationContext).isPresent();
        if(!flag){
            Assert.fail("application未注入 ，请在springContext.xml中注入SpringHolder!");
        }
    }
}
