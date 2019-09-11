package com.example.springboottest.test;

public class SingleDemo2 {
    private static Test1 test1 = new Test1();

    public SingleDemo2() {
    }

    public static Test1 getTest1(){
         return test1;
    }
}
