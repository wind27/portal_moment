package com.wind.dao;

import java.util.Map;

import com.wind.entity.Token;

/**
 * ITokenDao 接口
 * 
 * @author qianchun
 * @date 2016年2月19日 下午2:58:20
 */
public interface ITokenDao extends IBaseDao<Token, Long> {
    public Token insert(Token token);
    public Token findByUid(long uid);
    public boolean updateByParams(Map<String, Object> params);
}
