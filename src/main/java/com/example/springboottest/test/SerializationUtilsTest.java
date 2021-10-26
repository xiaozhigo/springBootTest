package com.example.springboottest.test;

import com.example.springboottest.dto.UserDetailDto;
import com.example.springboottest.dto.UserDto;
import org.apache.commons.lang3.SerializationUtils;

public class SerializationUtilsTest {
    public static void main(String[] args) {
        UserDto userDto = new UserDto();
        UserDetailDto detailDto = new UserDetailDto();
        detailDto.setUserId(1);
        detailDto.setDetailName("大哈");
        detailDto.setDetailMessage("大哈message");
        userDto.setUserDetailDto(detailDto);
        userDto.setUserName("大哈");

        UserDto dto = SerializationUtils.clone(userDto);

        UserDetailDto detailDto2 = new UserDetailDto();
        detailDto2.setUserId(2);
        detailDto2.setDetailName("二哈");
        detailDto2.setDetailMessage("二哈message");
        dto.setUserDetailDto(detailDto2);
        dto.setUserName("二哈");
        System.out.println(userDto);
        System.out.println(dto);
    }
}
