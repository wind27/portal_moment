package com.wind.mongo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wind.commons.Constant;
import com.wind.commons.Constant.DeleteStatus;
import com.wind.commons.Constant.IsDelete;
import com.wind.commons.ServiceResult;
import com.wind.entity.Comment;
import com.wind.mongo.service.CommentService;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class CommentTest {
    @Resource
    CommentService commentService;
    
    @Test
    public void main() {
//    	Moment moment1 = createMoment1();
//    	boolean flag = momentMongoService.create(moment1);
//    	if(flag==true) {
//    		System.out.println("插入成功");
//    	}
    	
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("skip", 0);
//    	params.put("limit", 1);
//    	List<Moment> momentList = momentMongoService.find(params);
//        JSONArray array = JSONArray.fromObject(momentList);
//        System.out.println(array.toString());
    	
//    	Moment moment2 = createMoment2();
//    	Bson filter = new BsonDocument()
//    			.append("id", new BsonInt64(1));
//    	Document document = DocumentMongoUtil.moment2Document(moment2);
//    	momentMongoService.replace(filter, document);
    	
//    	List<Comment> commentList = createCommentList(10);
//    	ServiceResult serviceResult= commentService.batchCreate(commentList);
//    	if(serviceResult.isSuccess()==false) {
//    		System.out.println("批量插入失败");
//    	} else {
//    		System.out.println("批量插入成功");
//    	}
    	
    	List<Long> targetIdsList = new ArrayList<>();
    	targetIdsList.add(1l);
    	ServiceResult result = commentService.findByTargetIds(targetIdsList);
    	if(result.isSuccess()) {
    		System.out.println(JSONArray.fromObject(result.getData()).toString());
    	}
        
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("skip", 0);
//    	params.put("limit", 10);
//    	params.put("bson", filter);
//    	List<Moment> momentList = momentMongoService.find(params);
//        JSONArray array = JSONArray.fromObject(momentList);
//        System.out.println(array.toString());
    }
    
    public Comment createComment1() {
    	Comment comment = new Comment();
        comment.setContent("评论1");
        comment.setCreateTime(System.currentTimeMillis());
        comment.setPid(Constant.TREE_ROOT_ID);
        comment.setIsDel(IsDelete.NO);
        
        comment.setTargetId(1l);
        comment.setTargetUid(1001);
        comment.setTargetType(Constant.CommentTargetType.MOMENT);
        
        comment.setOperateUid(1001);
        comment.setOperateTime(System.currentTimeMillis());
        comment.setOperateType(Constant.CommentOperateType.COMMENT);
        return comment;
    }
    
    public List<Comment> createCommentList(int size) {
    	List<Comment> commentList = new ArrayList<Comment>();
    	for(int i=0; i<size; i++) {
    		Comment comment = new Comment();
            comment.setContent("评论"+i);
            comment.setCreateTime(System.currentTimeMillis());
            comment.setPid(Constant.TREE_ROOT_ID);
            comment.setIsDel(IsDelete.NO);
            
            comment.setTargetId(1l);
            comment.setTargetUid(1001);
            comment.setTargetType(Constant.CommentTargetType.MOMENT);
            
            comment.setOperateUid(1001);
            comment.setOperateTime(System.currentTimeMillis());
            comment.setOperateType(Constant.CommentOperateType.COMMENT);
            commentList.add(comment);
    	}
        return commentList;
    }
}    