package com.example.springboottest.util;

import com.google.gson.Gson;

/**
 * @Author: HU_zhenwei
 * @Date: 2018/10/181:16 PM
 * @Descripton:
 */
public class Gsons {
    public static Gson gson = null;

    private Gsons(){
    }

    public static Gson gsons(){
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }
}
