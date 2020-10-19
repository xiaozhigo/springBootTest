package com.example.springboottest.mysql;

import com.example.springboottest.dto.UserDetailDto;
import com.example.springboottest.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2019-02-13 11:19
 */
@Mapper
public interface TestDao {

    List<Map<String,Object>> queryAllUser();

    void insertUser(UserDto userDto);

    void insertUserDetail(UserDetailDto userDto);

    void updateUserId(@Param("id") int id);
}
