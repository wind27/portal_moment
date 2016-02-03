package com.wind.service;


import java.util.List;
import java.util.Map;

import com.wind.entity.Comment;
import com.wind.entity.Moment;


/**
 * UserService 接口
 * 
 * @author qianchun
 * @date 2016年1月29日 下午4:02:47
 */
public interface ICommentService {
    public Comment findById(long id);
    public Comment create(Comment comment);
    public boolean delete(long id);
    public boolean update(Map<String, Object> params);
    
    public List<Comment> findList(Map<String, Object> params);
}
