package com.example.springboottest.design.observer;

import com.google.common.eventbus.Subscribe;

/**
 * 发送手机短信——观察者
 */
public class SMSObserver {

    @Subscribe
    public void sendSMS(String userName) {
        System.out.println("发送短信：" + userName + "欢迎你");
    }
}
