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
    private long pid;//父ID
    private long targetId;//评论对象id
    private int targetType;//评论对象类型：1、moment；2、article；。。。
    private String content;//评论内容
    private int operateType;//操作类型：1、评论；2、回复；3、转发
    
    private long operateUid;//操作人uid
    private long targetUid;//目标用户uid
    
    private int isDel;//状态：0、未删除；1、已删除
    private long createTime;//发布时间
    private long operateTime;//操作时间
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
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
	public int getOperateType() {
		return operateType;
	}
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	public long getOperateUid() {
		return operateUid;
	}
	public void setOperateUid(long operateUid) {
		this.operateUid = operateUid;
	}
	public long getTargetUid() {
		return targetUid;
	}
	public void setTargetUid(long targetUid) {
		this.targetUid = targetUid;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(long operateTime) {
		this.operateTime = operateTime;
	}
}
