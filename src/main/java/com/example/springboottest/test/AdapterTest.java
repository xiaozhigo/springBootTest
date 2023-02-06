package com.example.springboottest.test;

import com.example.springboottest.design.adaptee.*;

public class AdapterTest {

    public static void main(String[] args) {
        // 原有的业务逻辑
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.t1();

        // 基于继承 增加适配器业务逻辑
        Target adapter = new Adapter();
        adapter.t1();

        // 基于组合 增加适配器业务逻辑
        Target adapterCompose = new AdapterCompose(new Adaptee());
        adapterCompose.t1();
    }
}
