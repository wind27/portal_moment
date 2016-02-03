
package com.wind.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wind.commons.DataSourceSwitch;
import com.wind.dao.IUserDao;
import com.wind.entity.User;
import com.wind.service.IUserService;

/**
 * UserService 实现
 * 
 * @author qianchun
 * @date 2016年1月29日 下午4:02:23
 */
@Service
public class UserService implements IUserService {
    public UserService() {
        DataSourceSwitch.setDataSourceType("userDataSource");
    }
    
    @Resource
    IUserDao userDao;

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User add(User user) {
        return userDao.insert(user);
    }

    public boolean updateStatus(long id, int status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("status", status);
        return userDao.update(params);
    }

    public List<User> findList(Map<String, Object> params) {
        return userDao.findList(params);
    }
    
    public Map<Long, User> findMap(Map<String, Object> params) {
        Map<Long, User> userMap = new HashMap<>();
        List<User> userList = userDao.findList(params);
        if(userList != null) {
            for(int i=0; i<userList.size(); i++) {
                User user = userList.get(i);
                if(user != null) {
                    userMap.put(user.getId(), user);
                }
            }
        }
        return userMap;
    }
}
