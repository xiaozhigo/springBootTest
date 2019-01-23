package com.example.springboottest.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboottest.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wulei
 * @date 2019-01-22 13:13
 */
@RestController
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    /**
     *产品解析
     * @param requestBody
     * @return
     */
    @RequestMapping("/productAnalysis")
    public String productAnalysis(@RequestBody String requestBody){
           Map<String, Object> response = new HashMap<>();
           try{
               if(requestBody.indexOf("C0004") > 0){
                   JSONObject head = JSONObject.parseObject(requestBody);
                   JSONArray productItems = head.getJSONObject("HEAD").getJSONObject("svcContent").getJSONObject("request").getJSONObject("productList").getJSONArray("productItem");
                   response = commodityService.productAnalysis(productItems);
               }
           }catch (Exception e){
             e.printStackTrace();
             response.put("respDesc","商品解析失败:"+e.toString());
             response.put("respCode","9999");
           }
           return JSONObject.toJSONString(response);
    }



}
