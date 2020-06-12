package com.spring.suport;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

/**
 * @author Mori on 2017/3/31.
 */
public class ResponseResult {

    private String code;

    private Object message;

    private Object id;

    private Object info;


    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public ResponseResult() {
    }

    public ResponseResult(String code, Object message, Object id, Object info) {
        this(code,message,id);
        this.info = info;
    }

    public static ResponseResult build(String code, Object message) {
        return new ResponseResult(code, message);
    }

    public ResponseResult(String code, Object message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(String code, Object message, Object id) {
        this(code, message);
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        System.out.println("=================" + gson.toJson(this));
        return gson.toJson(this);
    }
}
