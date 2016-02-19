package com.wind.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wind.commons.DataSourceSwitch;
import com.wind.dao.ITokenDao;
import com.wind.entity.Token;
import com.wind.service.ITokenService;

/**
 * TokenService 实现
 * 
 * @author qianchun
 * @date 2016年2月19日 下午2:59:33
 */
@Service
public class TokenService implements ITokenService {
    
    public TokenService() {
        DataSourceSwitch.setDataSourceType("userDataSource");
    }
    
    @Resource
    ITokenDao tokenDao;

    @Override
    public Token create(Token token) {
        return tokenDao.insert(token);
    }

    @Override
    public Token findByUid(long uid) {
        return tokenDao.findByUid(uid);
    }

    @Override
    public boolean updateByParams(Map<String, Object> params) {
        return tokenDao.updateByParams(params);
    }
}
