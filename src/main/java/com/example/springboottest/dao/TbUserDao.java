package com.example.springboottest.dao;

import com.example.springboottest.dto.TbUserDto;
import com.example.springboottest.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
@Mapper
public interface TbUserDao {
    public void insertTbUser(TbUserDto tbUserDto);
}
