package com.example.springboottest.util;

/**
 * @Date : Created  in 16:19 2018/5/3
 * @Author : HuZhenwei
 * @Description :
 */
public enum ResponseStatus {

    未登录(1000,"no_login"),
    成功(1001,"success"),
    失败(1002,"false"),
    CODE成功(0,"success"),
    索引不存在(0,"索引不存在"),
    调用ElasticSearch失败(0,"调用ElasticSearch失败"),
    索引已经存在(0,"索引已经存在")
    ;

    private  String msg;
    private  Integer stauts;
    ResponseStatus(Integer stauts, String msg) {
        this.msg = msg;
        this.stauts = stauts;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }
}
