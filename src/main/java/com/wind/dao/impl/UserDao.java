package com.wind.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wind.dao.IUserDao;
import com.wind.entity.User;

@Service
public class UserDao extends BaseDao<User, Long> implements IUserDao {
    private final String namespace = User.class.getName();

    public User insert(User user) {
        user = super.insert(user);
        return user;
    }

    public User findById(long id) {
        User user = super.findById(id);
        return user;
    }

    @Override
    public boolean update(Map<String, Object> params) {
        return super.update("updateByParams", params);
    }
    
    public List<User> findList(Map<String, Object> params) {
        return (List<User>) super.findList("findByParam", params);
    }
}
