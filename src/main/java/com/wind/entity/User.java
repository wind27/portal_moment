package com.wind.entity;

import java.io.Serializable;

/**
 * 用户表
 * 
 * @author qianchun
 * @date 2016年1月25日 下午5:35:28
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public long id;
    public String name;
    public String nick;
    public String headImage;
    public String email;
    public String mobile;
    public String tel;
    public int sex;
    public int age;
    public int single;
    public long birth;
    public String address ;
    public String province ;
    public String city ;
    public String county;
    public int status;
    public long registerTime;
    public long updateTime;
    public long lastLoginTime;
    
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
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
    public String getHeadImage() {
        return headImage;
    }
    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSingle() {
        return single;
    }
    public void setSingle(int single) {
        this.single = single;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public long getBirth() {
        return birth;
    }
    public void setBirth(long birth) {
        this.birth = birth;
    }
    public long getRegisterTime() {
        return registerTime;
    }
    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }
    public long getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
    public long getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

}
