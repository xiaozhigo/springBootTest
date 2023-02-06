package com.example.springboottest.design.observer;

import com.google.common.eventbus.Subscribe;

/**
 * 发送邮件——观察者
 */
public class MailObserver {

    @Subscribe
    public void sendMail(String userName) {
        System.out.println("发送邮件:" + userName + "欢迎你");
    }
}
