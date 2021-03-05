package com.example.springboottest.util;

import com.google.gson.Gson;

public enum GsonSingle {
    INSTANCE;

    private Gson gson;

    GsonSingle() {
        gson = new Gson();
    }

    public Gson getGson(){
        return gson;
    }

    //对外暴露一个获取User对象的静态方法
    public Gson getInstance(){
        return GsonSingle.INSTANCE.getGson();
    }
}
