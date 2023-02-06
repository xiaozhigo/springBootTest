package com.example.springboottest.design.composite;

/**
 * 叶子节点
 */
public class Leaf extends Component{
    // 覆写父类方法
    @Override
    public void doSomething() {
        // 叶子节点逻辑
        System.out.println("叶子节点逻辑");
    }
}
