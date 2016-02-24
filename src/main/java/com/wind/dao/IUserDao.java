package com.wind.dao;

import java.util.List;
import java.util.Map;

import com.wind.entity.User;

public interface IUserDao extends IBaseDao<User, Long> {

    public User findById(long id);
    public User findByEmail(String email);
    public List<User> findList(Map<String, Object> params);

    public User insert(User user);
    
    public boolean update(User user);
    public boolean update(Map<String, Object> params);
}
