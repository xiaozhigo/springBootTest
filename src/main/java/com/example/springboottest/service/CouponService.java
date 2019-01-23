package com.example.springboottest.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboottest.dao.CouponsDao;
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
 * @date 2019-01-17 15:49
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class CouponService {

    @Autowired
    private CouponsDao dao;

    public Map<String, Object> qryGoords(JSONObject reqBody) {
        Map<String, Object> response = new HashMap<>();
        try{
            String couponId = reqBody.getString("couponId");
            String currPage = reqBody.getString("currPage");
            String pageSize = reqBody.getString("pageSize");
            Map<String, Object> map = new HashMap<>();
            if(!StringUtils.isEmpty(couponId)){
                map.put("couponId",couponId);
            }else{
                throw new Exception("必要参数couponId为空!");
            }
            if(!StringUtils.isEmpty(currPage)){
                map.put("currPage",(Integer.parseInt(currPage)-1)*Integer.parseInt(pageSize)+"");
            }else{
                throw new Exception("必要参数currPage为空!");
            }
            if(!StringUtils.isEmpty(pageSize)){
                map.put("pageSize",Integer.parseInt(currPage)*Integer.parseInt(pageSize)+"");
            }else{
                throw new Exception("必要参数pageSize为空!");
            }
            List<Map<String,Object>> commList = dao.qryGoords(map);
            List<Map<String,Object>> commInfoList = new ArrayList<>();
            Map<String, Object> commInfos = new HashMap<>();
            if(!CollectionUtils.isEmpty(commList)){
                for(int i = 0;i < commList.size();i++){
                    Map<String, Object> commodity = commList.get(i);
                    Map<String, Object> comm = new HashMap<>();
                    Map<String, Object> commInfo = new HashMap<>();
                    comm.put("commId",commodity.get("COMM_ID"));
                    comm.put("commName",commodity.get("COMM_NAME"));
                    comm.put("commDesc",commodity.get("DESCRIPTION"));
                    comm.put("commPrice",commodity.get("MARKET_PRICE"));
                    commInfo.put("commInfo",comm);
                    commInfoList.add(commInfo);
                }
            }
            commInfos.put("commInfos",commInfoList);
            response.put("rspData",commInfos);
            response.put("respDesc","调用成功");
            response.put("respCode","0000");
        }catch (Exception e){
            e.printStackTrace();
            response.put("respDesc","查询商品失败:"+e.toString());
            response.put("respCode","8888");
        }
        return response;
    }

    public Map<String,Object> qryCouponInfo(String couponId) {
           Map<String, Object> response = new HashMap<>();
           Map<String, Object> rootMap = new HashMap<>();
           try{
               if(StringUtils.isEmpty(couponId)){
                   throw new Exception("必要参数couponId为空!");
               }
               Map<String,Object> couponMap = dao.qryCouponInfo(couponId);
               response.put("respDesc","查询商品成功");
               response.put("respCode","0000");
               Map<String, Object> couponInfo = new HashMap<>();
               Map<String, Object> coupon = new HashMap<>();
               if(couponMap != null && couponMap.size() > 0){
                   coupon.put("amount",couponMap.get("AMOUNT") == null ? "": couponMap.get("AMOUNT").toString());
                   coupon.put("cost",couponMap.get("COST") == null ? "" : couponMap.get("COST").toString());
                   coupon.put("couponDesc",couponMap.get("COUPON_DESC") == null ? "" : couponMap.get("COUPON_DESC").toString());
                   coupon.put("couponId",couponMap.get("COUPON_ID") == null ? "" : couponMap.get("COUPON_ID").toString());
                   coupon.put("couponName",couponMap.get("COUPON_NAME") == null ? "" : couponMap.get("COUPON_NAME").toString());
                   coupon.put("couponStatus",couponMap.get("COUPON_STATUS") == null ? "" : couponMap.get("COUPON_STATUS").toString());
                   coupon.put("couponSubType",couponMap.get("COUPON_SUBTYPE") == null ? "" : couponMap.get("COUPON_SUBTYPE").toString());
                   coupon.put("couponType",couponMap.get("COUPON_TYPE") == null ? "": couponMap.get("COUPON_TYPE").toString());
                   coupon.put("endTime",couponMap.get("END_TIME") == null ? "": couponMap.get("END_TIME").toString());
                   coupon.put("startTime",couponMap.get("START_TIME") == null ? "": couponMap.get("START_TIME").toString());
               }
               couponInfo.put("couponInfo",coupon);
               rootMap.put("rspCode","0");
               rootMap.put("rspDesc","成功");
               rootMap.put("rspData",couponInfo);
               response.put("root",rootMap);
           }catch (Exception e){
               e.printStackTrace();
               response.put("respDesc","查询商品失败:"+e.toString());
               response.put("respCode","8888");
           }
           return response;
    }

}
