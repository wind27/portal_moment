package com.wind.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wind.dao.ITokenDao;
import com.wind.entity.Token;

/**
 * ITokenDao 实现
 * 
 * @author qianchun
 * @date 2016年2月19日 下午2:57:55
 */
@Service
public class TokenDao extends BaseDao<Token, Long> implements ITokenDao {
    public Token insert(Token token) {
        token = super.insert(token);
        return token;
    }

    @Override
    public Token findByUid(long uid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", uid);
        List<Token> tokenList = super.findList("findByUid", params);
        if(tokenList!=null && tokenList.size()==1) {
            return tokenList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean updateByParams(Map<String, Object> params) {
        return super.update("updateByParams", params);
    }
}
