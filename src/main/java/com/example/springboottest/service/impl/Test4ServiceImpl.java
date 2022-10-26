package com.example.springboottest.service.impl;

import com.example.springboottest.dao.TbUserDao;
import com.example.springboottest.dto.DateDto;
import com.example.springboottest.dto.TbUserDto;
import com.example.springboottest.dto.UserDto;
import com.example.springboottest.mysql.TestDao;
import com.example.springboottest.service.Test1Service;
import com.example.springboottest.service.Test4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class Test4ServiceImpl implements Test4Service {

    @Autowired
    private TestDao dao;
    @Autowired
    private Test1Service test1Service;
    @Autowired(required=false)
    private TbUserDao tbUserDao;

    private static final Map<String, Function<String, DateDto>> METHOD_MAP = new HashMap<>();

    @PostConstruct
    public void initMap(){
        METHOD_MAP.put("1", this::timeTest1);
        METHOD_MAP.put("2", this::timeTest2);
    }


    @Transactional(transactionManager = "xaTransaction",propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void transactionTest(UserDto userDto) {
        //新增user
        dao.insertUser(userDto);
        //新增userDetail
        test1Service.insertUserDetail(userDto);
        //新增user进oracle数据库
        TbUserDto tbUserDto = new TbUserDto();
        tbUserDto.setUserName(userDto.getUserName());
        tbUserDto.setAge("10");
        //int i = 10/0;
        tbUserDao.insertTbUser(tbUserDto);
    }

    @Override
    public TbUserDto queryUserById(String userId) {
        TbUserDto userDto = new TbUserDto();
        Map map = dao.queryUserById(Integer.parseInt(userId));
        userDto.setUserId(Integer.parseInt(map.get("userId").toString()));
        userDto.setUserName(map.get("userName").toString());
        return userDto;
    }

    public void insertUserDetail(UserDto userDto) {
        dao.insertUserDetail(userDto.getUserDetailDto());
        List<Map<String,Object>> list = dao.queryAllUser();
        dao.updateUserId(Integer.parseInt(list.get(0).get("user_id").toString()));
        //int i = 10/0;
        //System.out.println(i);
    }


    @Override
    public DateDto testMethod(String id) {
        Function<String, DateDto> dateDtoFunction = METHOD_MAP.get(id);
        return dateDtoFunction.apply(id);
    }


    private DateDto timeTest1(String s) {
        DateDto order = new DateDto();
        order.setLocalDateTime(LocalDateTime.now());
        order.setDate(new Date());
        return order;
    }

    private DateDto timeTest2(String s) {
        DateDto order = new DateDto();
        order.setLocalDateTime(LocalDateTime.now().plusDays(1));
        order.setDate(new Date());
        return order;
    }

}
