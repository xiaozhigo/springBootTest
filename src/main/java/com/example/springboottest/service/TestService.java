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
     * 获取当月和前两个月的年月
     * @return
     */
    public String[] createTime() {
        String[] tableNames = new String[3];
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYYMM");
        String nowTime = format.format(date);
        String now = new StringBuilder("invoke_log_statistic_").append(nowTime).toString();
        tableNames[0]=now;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH,-1);
        Date time = calendar.getTime();
        String monthAgoTime = format.format(time);
        String monthAgo = new StringBuilder("invoke_log_statistic_").append(monthAgoTime).toString();
        tableNames[1]=monthAgo;
        calendar.setTime(date);
        calendar.set(Calendar.MONTH,-2);
        Date time1 = calendar.getTime();
        String twoMonthAgoTime = format.format(time1);
        String twoMonthAgo = new StringBuilder("invoke_log_statistic_").append(twoMonthAgoTime).toString();
        tableNames[2]=twoMonthAgo;
        System.out.println(now+","+monthAgo+","+twoMonthAgo);
        return tableNames;
    }

    public Map<String,Object> microServiceCallVolume(String microServiceName) {
           Map<String, Object> response = new HashMap<>();
        try{
               if(StringUtils.isEmpty(microServiceName)){
                   throw new Exception("必要参数microServiceName为空!");
               }
               String[] timeNames = createTime();
               List<Map<String,Object>> list = new ArrayList<>();
               for(int i = 0;i < timeNames.length;i++){
                   String tableName = timeNames[i];
                   Map<String, Object> map = new HashMap<>();
                   map.put("serviceTimes",3500+i*500);
                   map.put("tableName",tableName);
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
