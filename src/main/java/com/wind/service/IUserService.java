package com.wind.service;


import java.util.List;
import java.util.Map;

import com.wind.entity.User;

/**
 * UserService 接口
 * 
 * @author qianchun
 * @date 2016年1月29日 下午4:02:47
 */
public interface IUserService {
    public User findById(long id);
    public User add(User user);
    public boolean updateStatus(long id, int status);
    public boolean update(Map<String, Object> params);
    
    public List<User> findList(Map<String, Object> params);
    public Map<Long, User> findMap(Map<String, Object> params);
}
