package com.wind.service;


import java.util.Map;

import com.wind.entity.Token;

/**
 * ITokenService 接口
 * 
 * @author qianchun
 * @date 2016年2月19日 下午2:59:10
 */
public interface ITokenService {
    public Token create(Token token);
    public Token findByUid(long uid);
    public boolean updateByParams(Map<String, Object> params);
}
