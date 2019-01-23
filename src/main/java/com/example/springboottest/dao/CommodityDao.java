package com.example.springboottest.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2019-01-22 13:16
 */
@Mapper
public interface CommodityDao {

    List<Map<String,Object>> productAnalysis(Map<Object, Object> param);
}
