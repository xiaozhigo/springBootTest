package com.example.springboottest.mysql;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2019-02-13 11:19
 */
@Mapper
public interface TestDao {

    List<Map<String,Object>> queryAllUser();
}
