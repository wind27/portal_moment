package com.wind.entity;

import java.io.Serializable;

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
}
