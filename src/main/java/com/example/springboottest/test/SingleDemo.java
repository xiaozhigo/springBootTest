package com.example.springboottest.test;

/**
 * 懒汉式单例
 */
public class SingleDemo {

    private static Test1 test1 = null;

    public SingleDemo() {
    }

    public static Test1 getTest1(){
        if(test1 == null){
            test1 = new Test1();
        }
        return test1;
    }
}
