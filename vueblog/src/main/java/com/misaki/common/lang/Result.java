package com.misaki.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private int code;
    private String message;
    private Object data;
    public static Result success(String msg,Object data){
        return success(200,msg,data);
    }

    public static Result success(int code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }


    public static Result success(Object data) {
        Result r = new Result();
        r.code = 200;
        r.message = "OK";
        r.data = data;
        return r;
    }

    public static Result faild(int code, String message, Object data) {
        Result r = new Result();
        r.code = code;
        r.message = message;
        r.data = data;
        return r;
    }

    public static Result faild(String message) {
        Result r = new Result();
        r.code = 400;
        r.message = message;
        r.data = null;
        return r;
    }

    public static Result faild(int code, String message) {
        Result r = new Result();
        r.code = code;
        r.message = message;
        r.data = null;
        return r;
    }
}
