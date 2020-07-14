package com.example.springboottest.runbale;

import com.example.springboottest.listener.StudyListener;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class StudentClass implements Runnable {

    public List<StudyListener> list = new ArrayList<>();

    public StudentClass(List<StudyListener> list) {
        this.list = list;
    }

    @Override
    public void run() {
        study();
    }

    public void study() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String flag = "true";
        for (StudyListener listener : list) {
             listener.update(flag);
        }
    }
}
