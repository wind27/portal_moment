package com.wind.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wind.dao.ICommentDao;
import com.wind.entity.Comment;

//@Service
public class CommentDao extends BaseDao<Comment, Long> implements ICommentDao {
    public Comment insert(Comment comment) {
        comment = super.insert(comment);
        return comment;
    }

    public Comment findById(long id) {
        Comment comment = super.findById(id);
        return comment;
    }

    @Override
    public boolean update(Map<String, Object> params) {
        return super.update("updateByParams", params);
    }
    
    public List<Comment> findList(Map<String, Object> params) {
        return (List<Comment>) super.findList("findByParam", params);
    }
}
