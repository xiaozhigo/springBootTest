package com.example.springboottest.design.facade;
/**
 * 门面角色
 */
public class Facade {
    private ClassA a = new ClassA();
    private ClassB b = new ClassB();
    private ClassC c= new ClassC();

    // 提供给外部访问的方法
    public void doSomething(){
        this.a.doA();
        this.b.doB();
        this.c.doC();
    }
}
