package com.wind.entity;

import java.io.Serializable;

/**
 * 主键Id生成
 * 
 * @author qianchun
 * @date 2016年3月4日 上午11:20:42
 */
public class Ids implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;//主键名称
    private long nextIndex;//下一个主键id
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNextIndex() {
		return nextIndex;
	}
	public void setNextIndex(long nextIndex) {
		this.nextIndex = nextIndex;
	}
}
