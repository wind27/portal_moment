package com.wind.dao;

import java.util.List;
import java.util.Map;

import com.wind.entity.Comment;


public interface ICommentDao extends IBaseDao<Comment, Long> {
    public Comment insert(Comment comment);
    public Comment findById(long id);
    
    public boolean update(Comment comment);
    public boolean update(Map<String, Object> params);
    public List<Comment> findList(Map<String, Object> params);
}
