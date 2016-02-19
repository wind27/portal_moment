package com.wind.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 启用禁用表
 * 
 * @author qianchun
 * @date 2016年1月25日 下午5:35:28
 */
public class DisabledUser implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private long id;
    private long uid;//被操作人id
    private long operatorId;//操作人id
    private int type;//操作类型：0、禁用；1、启用
    private String reason;//操作原因
    private Date createTime;//操作时间
    
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
    public long getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
