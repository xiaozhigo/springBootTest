package com.example.springboottest.service;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wulei
 * @date 2019-01-22 16:39
 */
@Service
public class TestService {
    /**
     * 获取当月和前两个月的年月的表名
     * @return
     */
    public List<Map<String,Object>> createTime() {
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i = 0;i < 3;i++){
            Map<String, Object> map = new HashMap<>();
            StringBuilder builder = new StringBuilder("invoke_log_statistic_");
            SimpleDateFormat YMformat = new SimpleDateFormat("yyyyMM");
            SimpleDateFormat DDformat = new SimpleDateFormat("dd");
            SimpleDateFormat YMDformat = new SimpleDateFormat("yyyyMMdd HHmmss");
            Calendar endCalendar = Calendar.getInstance();
            Date date = new Date();
            endCalendar.setTime(date);
            endCalendar.set(Calendar.MINUTE, 0);
            endCalendar.set(Calendar.SECOND, 0);
            endCalendar.set(Calendar.MILLISECOND, 0);
            endCalendar.set(Calendar.HOUR_OF_DAY, 0);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(date);
            startCalendar.set(Calendar.MINUTE, 0);
            startCalendar.set(Calendar.SECOND, 0);
            startCalendar.set(Calendar.MILLISECOND, 0);
            startCalendar.set(Calendar.HOUR_OF_DAY, 0);
            //如果是每月1号
            if("01".equals(DDformat.format(date))){
                endCalendar.set(Calendar.MONTH,-i);
                //endCalendar.set(Calendar.DAY_OF_MONTH,-i);
                startCalendar.add(Calendar.MONTH, -(i+1));
                startCalendar.set(Calendar.DAY_OF_MONTH, 1);
            }else{
                endCalendar.set(Calendar.MONTH,-i);
                startCalendar.add(Calendar.MONTH, -i);
                startCalendar.set(Calendar.DAY_OF_MONTH, 1);
            }
            Date startDate = startCalendar.getTime();
            Date endDate = endCalendar.getTime();
            String YMtime = YMformat.format(startDate);
            String startTime = YMDformat.format(startDate);
            String endTime = YMDformat.format(endDate);
            String tableName = builder.append(YMtime).toString();
            map.put("tableName",tableName);
            map.put("startTime",startTime);
            map.put("endTime",endTime);
            list.add(map);
        }
        return list;
    }

    public Map<String,Object> microServiceCallVolume(String microServiceName) {
           Map<String, Object> response = new HashMap<>();
        try{
               if(StringUtils.isEmpty(microServiceName)){
                   throw new Exception("必要参数microServiceName为空!");
               }
               List<Map<String, Object>> timeList = createTime();
               List<Map<String,Object>> list = new ArrayList<>();
               for(int i = 0;i < timeList.size();i++){
                   String tableName = (String) timeList.get(i).get("tableName");
                   String startTime = (String) timeList.get(i).get("startTime");
                   String endTime = (String) timeList.get(i).get("endTime");
                   Map<String, Object> map = new HashMap<>();
                   map.put("serviceTimes",3500+i*500);
                   map.put("tableName",tableName);
                   map.put("startTime",startTime);
                   map.put("endTime",endTime);
                   map.put("microServiceName",microServiceName);
                   list.add(map);
               }
               response.put("resData",list);
               response.put("resDesc","查询微服务调用量成功!");
               response.put("resCode","0000");
           }catch (Exception e){
               e.printStackTrace();
               response.put("resDesc","查询微服务调用量失败:"+e.toString());
               response.put("resCode","8888");
           }
           return response;
    }
}
