package com.example.springboottest.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Extensions {
    public static String format(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }
}
