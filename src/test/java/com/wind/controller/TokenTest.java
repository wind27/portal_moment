package com.wind.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wind.commons.Constant.LoginType;
import com.wind.entity.Token;
import com.wind.entity.User;
import com.wind.service.ITokenService;
import com.wind.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class TokenTest {
    @Resource
    ITokenService tokenService;
    
    @Resource
    IUserService userService;
    
    
    /**
     * 添加用户
     * 
     * @author qianchun  @date 2016年2月1日 下午3:42:16
     */
    public Token create(Token token) {
        return tokenService.create(token);
    }
    
    public boolean updateByParams(Map<String, Object> params) {
        return tokenService.updateByParams(params);
    }
    public Token findByUid(long id) {
        return tokenService.findByUid(id);
    }
    
    @Test
    public void main() {
//        Token token = create();
//        token = tokenService.create(token);
//        String result1 = JSONObject.fromObject(token).toString();
//        System.out.println(result1);
        
//        Token token = tokenService.findByUid(1001);
//        String result2 = JSONObject.fromObject(token).toString();
//        System.out.println(result2);
//        
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("uid", token.getUid());
//        params.put("appToken", "2222");
//        boolean flag = tokenService.updateByParams(params);
//        String result3 = JSONObject.fromObject(token).toString();
//        System.out.println(result3);
    }
    
    public Token create() {
        User user = userService.findById(1001);
        Token token = new Token();
        token.setUid(user.getId());
        token.setPcToken("1111");
        token.setTime(System.currentTimeMillis());
        token.setLoginName(user.getEmail());
        token.setLoginType(LoginType.EMAIL);
        return token;
    }
}    