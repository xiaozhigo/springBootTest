package com.example.springboottest.service;

import com.alibaba.fastjson.JSONArray;
import com.example.springboottest.dao.CommodityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2019-01-22 13:15
 */
@Service
public class CommodityService {

    @Autowired
    private CommodityDao dao;

    public Map<String,Object> productAnalysis(JSONArray productItems) {
           Map<String, Object> response = new HashMap<>();
           Map<String, Object> commditys = new HashMap<>();
           try{
               for(int i = 0;i < productItems.size(); i++){
                 Map<String,Object> map = (Map<String, Object>) productItems.get(i);
                   String dataSource = (String) map.get("dataSource");
                   String productCode = (String) map.get("productCode");
                   String productType = (String) map.get("productType");
                   /*if(StringUtils.isEmpty(dataSource)){
                       throw new Exception("未输入必要参数dataSource");
                   }*/
                   if(StringUtils.isEmpty(productCode)){
                       throw new Exception("未输入必要参数productCode");
                   }
                   if(StringUtils.isEmpty(productType)){
                       throw new Exception("未输入必要参数productType");
                   }
                   if("1".equals(productType) || "5".equals(productType)){
                       Map<Object, Object> param = new HashMap<>();
                       param.put("dataSource",dataSource);
                       param.put("productCode",productCode);
                       param.put("productType",productType);
                       List<Map<String,Object>> commList = dao.productAnalysis(param);
                       List<Map<String,Object>> commodityList = new ArrayList<>();
                       List<Map<String,Object>> productList = new ArrayList<>();
                       Map<String, Object> productItem = new HashMap<>();
                       Map<String, Object> commoditItem = new HashMap<>();
                       if(CollectionUtils.isEmpty(commList)){
                           throw new Exception("商品中心系统异常提示：根据产品:"+productCode+"查询不到对应的产品！");
                       }
                       if(commList.size() > 1){
                           throw new Exception("商品中心系统异常提示：根据产品:"+productCode+"查询到的商品存在"+commList.size()+"个！");
                       }
                       if(commList.size() == 1){
                           Map<String, Object> commodity = commList.get(0);
                           Map<String, Object> comm = new HashMap<>();
                           Map<String, Object> product = new HashMap<>();
                           comm.put("commodityCode",commodity.get("COMM_CODE"));
                           comm.put("commodityDesc",commodity.get("DESCRIPTION"));
                           comm.put("commodityType",commodity.get("COMM_TYPE"));
                           comm.put("commodityName",commodity.get("COMM_NAME"));
                           product.put("productCode",productCode);
                           product.put("productType",productType);
                           productList.add(product);
                           productItem.put("productItem",productList);
                           comm.put("productList",productItem);
                           commodityList.add(comm);
                           commoditItem.put("commoditItem",commodityList);
                       }
                       commditys.put("commodityList",commoditItem);
                   }
                   response.put("respDesc","产品解析成功");
                   response.put("respCode","0000");
                   response.put("respData",commditys);
               }
           }catch (Exception e){
               e.printStackTrace();
               response.put("respDesc","产品解析失败:"+e.toString());
               response.put("respCode","8888");
           }
           return response;
    }
}
