package com.example.springboottest.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable("td_user")
@Component
@Slf4j
public class UserHandler implements EntryHandler<User> {

    @Override
    public void insert(User user) {
        log.info("新增数据:{}",user);
    }

    @Override
    public void update(User before, User after) {
        log.info("更新数据：前{},后{}",before,after);
    }

    @Override
    public void delete(User user) {
        log.info("删除数据：{}",user);
    }
}
