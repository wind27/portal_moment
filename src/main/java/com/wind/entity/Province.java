package com.wind.entity;

import java.io.Serializable;

/**
 * 省
 * 
 * @author qianchun
 * @date 2016年2月24日 下午6:41:32
 */
public class Province implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;//主键id
    private String code;//省 code
    private String name;//名称
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
