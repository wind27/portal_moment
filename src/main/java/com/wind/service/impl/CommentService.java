package com.wind.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wind.commons.DataSourceSwitch;
import com.wind.dao.ICommentDao;
import com.wind.entity.Comment;
import com.wind.service.ICommentService;

/**
 * UserService 实现
 * 
 * @author qianchun
 * @date 2016年1月29日 下午4:02:23
 */
@Service
public class CommentService implements ICommentService {
    
    public CommentService() {
        DataSourceSwitch.setDataSourceType("userDataSource");
    }
    
    @Resource
    ICommentDao commentDao;

    @Override
    public Comment findById(long id) {
        return commentDao.findById(id);
    }

    @Override
    public Comment add(Comment comment) {
        return commentDao.insert(comment);
    }

    public boolean updateStatus(long id, int status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("status", status);
        return commentDao.update(params);
    }
    
    public boolean update(Map<String, Object> params) {
        return commentDao.update(params);
    }

    public List<Comment> findList(Map<String, Object> params) {
        return commentDao.findList(params);
    }
}
