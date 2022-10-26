package com.example.springboottest.service;

import com.example.springboottest.dto.DateDto;
import com.example.springboottest.dto.TbUserDto;
import com.example.springboottest.dto.UserDto;

public interface Test4Service {
    void transactionTest(UserDto userDto);

    TbUserDto queryUserById(String userId);

    DateDto testMethod(String id);
}
