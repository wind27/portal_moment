package com.wind.commons;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * service 返回的 result
 * 
 * @author qianchun
 * @date 2016年3月4日 下午12:40:06
 */
public class ServiceResult<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean success;
    private String msg;
    private T object;
    private List<T> list;
    private Map<Long, List<T>> map;
    
    
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
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Map<Long, List<T>> getMap() {
		return map;
	}
	public void setMap(Map<Long, List<T>> map) {
		this.map = map;
	}
}

