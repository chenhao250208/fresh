package com.store.fresh.util;

import java.io.Serializable;

public enum ResultEnum implements Serializable {
    SUCCESS(200,"请求成功"),
    LOGIN_SUCCESS(200,"登陆成功"),
    LOGIN_ERROR(400,"登陆失败"),
    UNKNOWN_ERROR(500,"未知错误")

    ;



    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
