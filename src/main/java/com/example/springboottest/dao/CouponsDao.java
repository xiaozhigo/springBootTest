package com.example.springboottest.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2019-01-17 16:06
 */
@Mapper
public interface CouponsDao {

    List<Map<String,Object>> qryGoords(Map<String, Object> map);

    Map<String,Object> qryCouponInfo(String couponId);

    Map<String,Object> isOkByCouponId(String couponId);
}
