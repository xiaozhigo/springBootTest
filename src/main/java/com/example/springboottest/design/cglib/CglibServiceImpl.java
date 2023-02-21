package com.example.springboottest.design.cglib;

public class CglibServiceImpl implements CglibService{
    @Override
    public String sendMessage(String message) {
        System.out.println("打印信息:"+message);
        return message;
    }
}
