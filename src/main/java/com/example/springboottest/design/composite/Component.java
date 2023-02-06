package com.example.springboottest.design.composite;
/**
 * 个体和整体的抽象
 */
public abstract class Component {
    // 个体和整体都有的共享
    public void doSomething(){
        // 通用业务逻辑
        System.out.println("通用业务逻辑");
    }
}
