package com.example.springboottest.service.impl;

import com.example.springboottest.dto.UserDetailDto;
import com.example.springboottest.dto.UserDto;
import com.example.springboottest.mysql.TestDao;
import com.example.springboottest.service.Test1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class Test1ServiceImpl implements Test1Service {

    @Autowired
    private TestDao dao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertUserDetail(UserDto userDto) {
        dao.insertUserDetail(userDto.getUserDetailDto());
        List<Map<String,Object>> list = dao.queryAllUser();
        dao.updateUserId(Integer.parseInt(list.get(0).get("user_id").toString()));
        //int i = 10/0;
    }
}
