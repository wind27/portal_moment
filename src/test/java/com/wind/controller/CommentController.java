package com.wind.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wind.commons.Constant;
import com.wind.entity.Comment;
import com.wind.service.ICommentService;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class CommentController {
    @Resource
    ICommentService commentService;
    
    /**
     * 添加用户
     * 
     * @author qianchun  @date 2016年2月1日 下午3:42:16
     */
    public Comment add(Comment comment) {
        return commentService.add(comment);
    }
    
    /**
     * 删除
     * 
     * @author qianchun  @date 2016年2月1日 下午5:28:19
     * @param uid
     * @return
     */
    public boolean delete(long uid) {
        return commentService.updateStatus(uid, Constant.Status.DELETE_YES);
    }
    
    
    public boolean update(Map<String, Object> params) {
        return commentService.update(params);
    }
    /**
     * 主键id查询
     * 
     * @author qianchun  @date 2016年2月1日 下午3:41:49
     */
    public Comment findById(long id) {
        return commentService.findById(id);
    }
    
    /**
     * 条件查询
     * 
     * @author qianchun  @date 2016年2月1日 下午3:41:33
     */
    public List<Comment> findList() {
        Map<String, Object> params = new HashMap<String, Object>();
        return commentService.findList(params);
    }
    @Test
    public void main() {
        Comment comment = createComment();
        boolean flag = false;
        List<Comment> commentList = null;
        
//        comment = add(comment);
//        comment = findById(1l);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", 1l);
        params.put("updateTime", System.currentTimeMillis());
        params.put("content", "编辑第一个此刻");
        flag = update(params);
        
        commentList = findList();
        JSONArray array = JSONArray.fromObject(commentList);
        System.out.println(array.toString());
    }
    
    public Comment createComment() {
        Comment comment = new Comment();
        JSONArray emptyArrayJson = JSONArray.fromObject(new ArrayList<>());
        
//        comment.setTitle("创建第一个此刻");
//        comment.setStatus(Status.DELETE_NO);
//        comment.setContent("今天创建第一个此刻");
//
//        comment.setUid(1l);
//        comment.setCreateTime(System.currentTimeMillis());
//        comment.setUpdateTime(System.currentTimeMillis());
//        comment.setPublishTime(System.currentTimeMillis());
//
//        comment.setPraiseUid(emptyArrayJson.toString());
//        comment.setCollectionUid(emptyArrayJson.toString());
        return comment;
    }
}    