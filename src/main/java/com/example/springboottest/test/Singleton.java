package com.example.springboottest.test;

public class Singleton {

    private Singleton(){
    }

    private static volatile Singleton singleton;

    public Singleton getSingleton(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
