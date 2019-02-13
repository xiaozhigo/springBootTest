package com.example.springboottest.scheduled;

import com.example.springboottest.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wulei
 * @date 2019-01-24 18:12
 */
@Component
public class TimeTest {

    @Autowired
    private TimeService timeService;

    @Scheduled(cron = "0 27 18 * * ?")
    //@Scheduled(cron = "0/1 * * * * ?")
    public void timeTest1(){
        try{
            Date date = new Date(System.currentTimeMillis() - 24 * 24 * 3600 * 1000);
            Boolean flag = timeService.queryInterfaceCall(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("_______________________-");
    }

}
