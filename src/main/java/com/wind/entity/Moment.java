package com.wind.entity;

import java.io.Serializable;

/**
 * 此刻
 * 
 * @author qianchun
 * @date 2016年1月25日 下午5:37:00
 */
public class Moment implements Serializable {
    private static final long serialVersionUID = 1L;

    public long id;
    public String title;//标题
    public String content;//内容
    public long uid;//发布人uid
    public long createTime;//创建时间
    public long updateTime;//修改时间
    public long publishTime;//发布时间
    public String praiseUid;//点赞用户
    public String collectionUid;//收藏用户
    public int viewNum;//浏览数
    public int status;//状态：0、删除；1、正常
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
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
    public long getPublishTime() {
        return publishTime;
    }
    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }
    public String getPraiseUid() {
        return praiseUid;
    }
    public void setPraiseUid(String praiseUid) {
        this.praiseUid = praiseUid;
    }
    public String getCollectionUid() {
        return collectionUid;
    }
    public void setCollectionUid(String collectionUid) {
        this.collectionUid = collectionUid;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getViewNum() {
        return viewNum;
    }
    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }
}
