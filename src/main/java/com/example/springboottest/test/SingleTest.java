package com.example.springboottest.test;

public class SingleTest {

    public static SingleTest singleTest = new SingleTest();

    public SingleTest() {
    }

    public static SingleTest getSingleTest(){
        return singleTest;
    }
}
