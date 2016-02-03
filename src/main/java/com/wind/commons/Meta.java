package com.wind.commons;


/**
 * meta
 * 
 * @author qianchun
 * @date 2016年2月2日 下午3:56:40
 */
public class Meta {
    private int code;
    private String msg;

    public Meta(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

