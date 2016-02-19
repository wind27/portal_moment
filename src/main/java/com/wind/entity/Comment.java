package com.wind.entity;

import java.io.Serializable;

/**
 * 评论
 * 
 * @author qianchun
 * @date 2016年1月25日 下午5:37:00
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;//主键ID
    private long targetId;//评论对象
    private int targetType;//评论类型
    private String content;//评论内容
    private long pid;//父ID
    private int operateType;//操作类型：1、评论；2、回复；3、转发
    private long targetUid;//目标用户uid
    private int status;//状态：0、删除；1、正常
    private long createTime;//发布评论时间
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTargetId() {
        return targetId;
    }
    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }
    public int getTargetType() {
        return targetType;
    }
    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getPid() {
        return pid;
    }
    public void setPid(long pid) {
        this.pid = pid;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public int getOperateType() {
        return operateType;
    }
    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }
    public long getTargetUid() {
        return targetUid;
    }
    public void setTargetUid(long targetUid) {
        this.targetUid = targetUid;
    }
}
