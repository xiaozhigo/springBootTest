package com.example.springboottest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvoiceProxy implements InvocationHandler{

    private Object targetObject;


    public  Object newProxyInstance(Object targetObject){
            this.targetObject=targetObject;
            return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),targetObject.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------------------代理开始-----------------------");
        try{
            Object invoke = method.invoke(targetObject, args);
            System.out.println("-----------------代理成功--------------------");
            return invoke;
        }catch (Exception e){
            System.out.println("------------------代理出错-----------------------");
            e.printStackTrace();
        }
        return null;
    }
}
