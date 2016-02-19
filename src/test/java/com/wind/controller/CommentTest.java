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
import com.wind.commons.Constant.CommentOperateType;
import com.wind.commons.Constant.CommentTargetType;
import com.wind.commons.Constant.DeleteStatus;
import com.wind.entity.Comment;
import com.wind.service.ICommentService;
import com.wind.service.IMomentService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class CommentTest {
    @Resource
    ICommentService commentService;
    
    @Resource
    IMomentService momentService;
    
    /**
     * 添加用户
     * 
     * @author qianchun  @date 2016年2月1日 下午3:42:16
     */
    public Comment create(Comment comment) {
        return commentService.create(comment);
    }
    
    /**
     * 删除
     * 
     * @author qianchun  @date 2016年2月1日 下午5:28:19
     * @param uid
     * @return
     */
    public boolean delete(long uid) {
        return commentService.delete(uid);
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
//        Comment comment = createComment();
//        comment = create(comment);
        
//        Comment comment = commentService.findById(1l);
//        JSONObject obj = JSONObject.fromObject(comment);
//        System.out.println(obj.toString());

//        List<Comment> commentList = null;
//        commentList = findList();
//        JSONArray array = JSONArray.fromObject(commentList);
//        System.out.println(array.toString());
    }
    
    public Comment createComment() {
        Comment comment = new Comment();
        comment.setContent("这是一个很不错的平台");
        comment.setCreateTime(System.currentTimeMillis());
        comment.setPid(Constant.COMMENT_ROOT_PID);
        comment.setStatus(DeleteStatus.NO);
        comment.setTargetId(1l);
        comment.setTargetType(CommentTargetType.MOMENT);
        comment.setOperateType(CommentOperateType.COMMENT);
        return comment;
    }
}    