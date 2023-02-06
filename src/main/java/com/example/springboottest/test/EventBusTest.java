package com.example.springboottest.test;

import com.example.springboottest.design.observer.MailObserver;
import com.example.springboottest.design.observer.SMSObserver;
import com.example.springboottest.design.observer.UserController;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 */
public class EventBusTest {
    public static void main(String[] args) {
        UserController userController = new UserController();
        List<Object> observerList = new ArrayList<>();
        observerList.add(new MailObserver());
        observerList.add(new SMSObserver());
        userController.setObserverList(observerList);
        userController.register("张三","123");
    }
}
