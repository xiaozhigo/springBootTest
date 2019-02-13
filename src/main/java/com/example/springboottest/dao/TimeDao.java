package com.example.springboottest.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2019-01-25 11:22
 */
@Mapper
public interface TimeDao {

    List<Map<String,Object>> queryInterfaceCall(Map<String, Object> map);
}
