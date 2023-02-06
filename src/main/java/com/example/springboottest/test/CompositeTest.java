package com.example.springboottest.test;

import com.example.springboottest.design.composite.Component;
import com.example.springboottest.design.composite.Composite;
import com.example.springboottest.design.composite.Leaf;

public class CompositeTest {
    public static void main(String[] args) {
        // 创建一个根节点
        Composite root = new Composite();
        root.doSomething();

        // 创建一个树枝构件
        Composite branch = new Composite();

        // 创建一个叶子节点
        Leaf leaf = new Leaf();

        // 串联起来
        root.add(branch);
        branch.add(leaf);

        display(root);
    }

    // 通过递归遍历数
    public static void display(Composite root){
        for(Component c : root.getChildren()){
            if(c instanceof Leaf){ // 叶子节点
                c.doSomething();
            }else{
                display((Composite) c);
            }
        }
    }
}
