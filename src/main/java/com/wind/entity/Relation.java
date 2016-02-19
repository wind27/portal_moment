package com.wind.entity;

import java.io.Serializable;

/**
 * 用户关系
 * 
 * @author qianchun
 * @date 2016年2月19日 下午12:47:50
 */
public class Relation implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private long uid;
    private long targetUid;
    private int type;
    private int focus;
    private long createTime;
    private long updateTime;
    
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
    public long getTargetUid() {
        return targetUid;
    }
    public void setTargetUid(long targetUid) {
        this.targetUid = targetUid;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getFocus() {
        return focus;
    }
    public void setFocus(int focus) {
        this.focus = focus;
    }
    public long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public long getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
