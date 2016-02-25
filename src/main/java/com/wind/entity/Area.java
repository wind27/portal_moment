package com.wind.entity;

import java.io.Serializable;

/**
 * 区县
 * 
 * @author qianchun
 * @date 2016年2月24日 下午6:41:32
 */
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;//主键id
    private String code;//市 code
    private String name;//名称
    private String cityCode;//市 code
    private String provinceCode;//省 code
    
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
    public String getCityCode() {
        return cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    public String getProvinceCode() {
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
