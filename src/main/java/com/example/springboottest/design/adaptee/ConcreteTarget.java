package com.example.springboottest.design.adaptee;

/**
 * 目标角色实现类
 */
public class ConcreteTarget implements Target{
    @Override
    public void t1() {
        System.out.println("目标角色 t1 方法");
    }

    @Override
    public void t2() {
        System.out.println("目标角色 t2 方法");
    }

    @Override
    public void t3() {
        System.out.println("目标角色 t3 方法");
    }
}
