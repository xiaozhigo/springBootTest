package com.example.springboottest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private String userName;

    private UserDetailDto userDetailDto;
}
