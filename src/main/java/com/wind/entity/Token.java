package com.wind.entity;

import java.io.Serializable;

/**
 * 用户登录token
 * 
 * @author qianchun
 * @date 2016年1月25日 下午5:37:00
 */
public class Token implements Serializable {
    private static final long serialVersionUID = 1L;

    public long id;
    public long uid;//用户id
    public String appToken;//app token
    public String pcToken;//pc token
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public String getAppToken() {
        return appToken;
    }
    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }
    public String getPcToken() {
        return pcToken;
    }
    public void setPcToken(String pcToken) {
        this.pcToken = pcToken;
    }
}
