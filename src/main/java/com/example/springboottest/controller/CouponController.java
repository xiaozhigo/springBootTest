package com.example.springboottest.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springboottest.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wulei
 * @date 2019-01-17 15:48
 */
@RestController
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 查询可依附的商品
     * @return
     */
    @RequestMapping("/qryGoords")
    public String qryGoords(@RequestBody String reuqestBody){
        Map<String, Object> response = new HashMap<>();
        try {
            JSONObject root = JSONObject.parseObject(reuqestBody);
            JSONObject reqBody = root.getJSONObject("root").getJSONObject("reqBody");
            response = couponService.qryGoords(reqBody);
        }catch (Exception e){
            e.printStackTrace();
            response.put("respDesc","查询商品失败:"+e.toString());
            response.put("respCode","9999");
        }
        return JSONObject.toJSONString(response);
    }

    /**
     * 查询优惠券基本信息
     * @param requestBody
     * @return
     */
    @RequestMapping("/qryCouponInfo")
    public String qryCouponInfo(@RequestBody String requestBody){
        Map<String, Object> response = new HashMap<>();
        try{
            JSONObject root = JSONObject.parseObject(requestBody);
            String couponId = root.getJSONObject("root").getJSONObject("reqBody").getString("couponId");
            response = couponService.qryCouponInfo(couponId);
        }catch (Exception e){
            e.printStackTrace();
            response.put("respDesc","查询商品失败:"+e.toString());
            response.put("respCode","9999");
        }
        return JSONObject.toJSONString(response);
    }

}
