package com.example.springboottest.util;


/**
 * @Author: HU_zhenwei
 * @Date: 2018/10/242:22 PM
 * @Descripton:
 */
public class ResponseResult<T> {
    public ResponseResult() {
    }

    private int code = ResponseStatus.CODE成功.getStauts();

    private Object body;

    private String msg = ResponseStatus.成功.getMsg();

    private int status = ResponseStatus.成功.getStauts();

    public Integer errorCode;

    public ResponseResult(int code, String body) {
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public static <T> ResponseResult<T> error() {
        ResponseResult<T> response = new ResponseResult<>();
        response.setMsg(ResponseStatus.失败.getMsg());
        response.setCode(ResponseStatus.失败.getStauts());
        return response;
    }

    public static <T> ResponseResult<T> errorMessage(String message) {
        ResponseResult<T> response = new ResponseResult<>();
        response.setMsg(message);
        response.setCode(ResponseStatus.失败.getStauts());
        return response;
    }

    public static <T> ResponseResult<T> success() {
        ResponseResult<T> response = new ResponseResult<>();
        response.setMsg(ResponseStatus.成功.getMsg());
        response.setCode(ResponseStatus.成功.getStauts());
        return response;
    }


}
