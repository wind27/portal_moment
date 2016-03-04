package com.wind.commons;

import java.io.Serializable;

/**
 * result
 * 
 * @author qianchun
 * @date 2016年3月4日 下午12:40:06
 */
public class ContrResult implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean success;
    private String msg;
    private Object data;
    
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}

