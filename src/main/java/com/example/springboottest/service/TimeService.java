package com.example.springboottest.service;

import com.example.springboottest.dao.TimeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wulei
 * @date 2019-01-25 10:41
 */
@Service
public class TimeService {

    @Autowired(required = false)
    private TimeDao dao;

    public Boolean queryInterfaceCall(Date date) {
        Map<String,Object> map = getTableAndTime(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String,Object>> list = dao.queryInterfaceCall(map);
        if(CollectionUtils.isEmpty(list)){
            for(int i = 0;i < list.size(); i++){
                Map<String, Object> interfaceCall = new HashMap<>();
                interfaceCall.put("stateTimestamp",format.format(new Date()));
                interfaceCall.put("successFrequency",list.get(i).get("successFrequency"));
                interfaceCall.put("failFrequency",list.get(i).get("failFrequency"));
                interfaceCall.put("successRate",list.get(i).get("successRate"));
                interfaceCall.put("remarks","");
                interfaceCall.put("monitorId","");
                interfaceCall.put("responseTime",list.get(i).get("serviceTimes"));
            }
        }
        System.out.println(map);
        return true;
    }

    private Map<String,Object> getTableAndTime(Date date) {
        Map<String, Object> map = new HashMap<>();
        StringBuilder builder = new StringBuilder("invoke_log_statistic_");
        SimpleDateFormat YMformat = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat YMDHMSformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(date);
        startCalendar.set(Calendar.DATE,startCalendar.get(Calendar.DATE)-1);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(date);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        Date startDate = startCalendar.getTime();
        Date endDate = endCalendar.getTime();
        String YMtime = YMformat.format(startDate);
        String startTime = YMDHMSformat.format(startDate);
        String endTime = YMDHMSformat.format(endDate);
        String tableName = builder.append(YMtime).toString();
        map.put("tableName",tableName);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return map;
    }
}
