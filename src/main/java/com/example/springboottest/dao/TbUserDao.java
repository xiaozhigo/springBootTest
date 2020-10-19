package com.example.springboottest.dao;

import com.example.springboottest.dto.TbUserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserDao {
    public void insertTbUser(TbUserDto tbUserDto);
}
