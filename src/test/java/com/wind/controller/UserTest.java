package com.wind.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wind.commons.Constant;
import com.wind.commons.Constant.Sex;
import com.wind.commons.Constant.Single;
import com.wind.commons.Constant.UserStatus;
import com.wind.entity.User;
import com.wind.service.IUserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class UserTest {
    @Resource
    IUserService userService;
    
    /**
     * 添加用户
     * 
     * @author qianchun  @date 2016年2月1日 下午3:42:16
     */
    public User add(User user) {
        return userService.add(user);
    }
    
    /**
     * 禁用用户
     * 
     * @author qianchun  @date 2016年2月1日 下午3:42:07
     * @param uid
     */
    public boolean disable(long uid) {
        return userService.updateStatus(uid, Constant.UserStatus.DISABLE);
    }
    
    /**
     * 启用用户
     * 
     * @author qianchun  @date 2016年2月1日 下午3:41:59
     * @param uid
     */
    public boolean enable(long uid) {
        return userService.updateStatus(uid, Constant.UserStatus.ENABLE);
    }
    
    /**
     * 主键id查询
     * 
     * @author qianchun  @date 2016年2月1日 下午3:41:49
     */
    public User findById(long id) {
        return userService.findById(id);
    }
    
    /**
     * 条件查询
     * 
     * @author qianchun  @date 2016年2月1日 下午3:41:33
     */
    public List<User> findList() {
        Map<String, Object> params = new HashMap<String, Object>();
        return userService.findList(params);
    }
    @Test
    public void main() {
//        User user = createUser();
//        boolean flag = false;
//        List<User> userList = null;
//        
//        user = add(user);
        User user = userService.findByEmail("qianchun27@hotmail.com");
        String result1 = JSONObject.fromObject(user).toString();
        System.out.println(result1);
//        user = findById(1l);
//        flag = disable(1l);
//        flag = enable(1l);
//        flag = disable(1l);
//        userList = findList();
    }
    
    public User createUser() {
        User user = new User();
        user.setName("张跃雨");
        user.setNick("Hobart");
        user.setEmail("zhangyueyu@hotmail.com");
        user.setMobile("13512341234");
        user.setTel("13512341234");
        user.setHeadImage("");
        user.setProvince("河南");
        user.setCity("濮阳市");
        user.setCounty("xx区");
        user.setAddress("xxxx");
        user.setSex(Sex.MALE);
        user.setSingle(Single.FALSE);
        user.setStatus(UserStatus.ENABLE);
        user.setBirth(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        user.setLastLoginTime(System.currentTimeMillis());
        user.setRegisterTime(System.currentTimeMillis());
        return user;
    }
}    