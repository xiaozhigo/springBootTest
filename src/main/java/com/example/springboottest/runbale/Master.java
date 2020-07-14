package com.example.springboottest.runbale;

import com.example.springboottest.listener.StudyListener;

public class Master implements StudyListener {
    @Override
    public void update(String flag) {
        if("true".equals(flag)){
            System.out.println("GrandMaster!");
        }
    }
}
