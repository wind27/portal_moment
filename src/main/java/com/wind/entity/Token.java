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

    private long id;
    private long uid;//登录用户uid
    private String loginName;//登录用户名
    private String appToken;//app 登录 token
    private String pcToken;//pc 登录 token
    private long time;//登录时间
    private int loginType;//登录方式
    
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
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public int getLoginType() {
        return loginType;
    }
    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
