package com.wind.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 请求参数封装
 * 
 * @author qianchun
 * @date 2016年2月25日 下午2:52:23
 */
public class Param implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;//名称
    private String email;//邮箱
    private String token;//token
    private String mobile;//手机
    private String opwd;//旧密码
    private String pwd;//密码
    private String acode;//地区 code
    private String ccode;//市 code
    private String pcode;//省 code
    private long momentid;//momenId;
    private long articleid;//articleId
    private long uid;//用户uid
    private List<Long> uids;//用户uid集合
    private int pstart;
    private int plimit;
    
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOpwd() {
		return opwd;
	}
	public void setOpwd(String opwd) {
		this.opwd = opwd;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAcode() {
		return acode;
	}
	public void setAcode(String acode) {
		this.acode = acode;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public long getMomentid() {
		return momentid;
	}
	public void setMomentid(long momentid) {
		this.momentid = momentid;
	}
	public long getArticleid() {
		return articleid;
	}
	public void setArticleid(long articleid) {
		this.articleid = articleid;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public List<Long> getUids() {
		return uids;
	}
	public void setUids(List<Long> uids) {
		this.uids = uids;
	}
	public int getPstart() {
		return pstart;
	}
	public void setPstart(int pstart) {
		this.pstart = pstart;
	}
	public int getPlimit() {
		return plimit;
	}
	public void setPlimit(int plimit) {
		this.plimit = plimit;
	}
}
