package com.example.springboottest.test;

import com.example.springboottest.listener.StudyListener;
import com.example.springboottest.runbale.Master;
import com.example.springboottest.runbale.Student;
import com.example.springboottest.runbale.StudentClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StudyThread {

    @Test
    public void test(){
        List<StudyListener> arrayList = new ArrayList<>();
        Master master = new Master();
        Student student = new Student();
        arrayList.add(master);
        arrayList.add(student);
        StudentClass studentClass = new StudentClass(arrayList);
        Thread thread = new Thread(studentClass);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
