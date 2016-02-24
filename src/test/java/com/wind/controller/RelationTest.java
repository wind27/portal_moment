package com.wind.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.eval.RelationalOperationEval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wind.commons.Constant.RelationFocus;
import com.wind.commons.Constant.RelationType;
import com.wind.entity.Relation;
import com.wind.service.IRelationService;
import com.wind.service.IUserService;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class RelationTest {
    @Resource
    IRelationService relationService;
    
    @Resource
    IUserService userService;
    
    
    @Test
    public void main() {
        boolean flag = false;
//        flag = payAttention(1001, 1002);
//        flag = payAttention(1002, 1001);
        flag = cancelAttention(1001, 1002);
//        flag = cancelAttention(1002, 1001);
        
        
        Relation relation = relationService.findByUidAndTargetUid(1001, 1002);
        Relation relationTarget = relationService.findByUidAndTargetUid(1002, 1001);
        String result1 = JSONObject.fromObject(relation).toString();
        String result2 = JSONObject.fromObject(relationTarget).toString();
        System.out.println(result1);
        System.out.println(result2);
        
//        Token token = tokenService.findByUid(1001);
//        String result2 = JSONObject.fromObject(token).toString();
//        System.out.println(result2);
//        
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("uid", token.getUid());
//        params.put("appToken", "2222");
//        boolean flag = tokenService.updateByParams(params);
//        String result3 = JSONObject.fromObject(token).toString();
//        System.out.println(result3);
    }
    
    /**
     * 关注
     * 
     * @author qianchun  @date 2016年2月24日 下午3:21:22
     * @param uid
     * @param targetUid
     * @return
     */
    public boolean payAttention(long uid, long targetUid) {
        Relation relation = relationService.findByUidAndTargetUid(uid, targetUid);
        Relation target = relationService.findByUidAndTargetUid(targetUid, uid);
        
        List<Relation> relationList = new ArrayList<>();
        if(relation==null && target==null) {
            relation = new Relation();
            relation.setUid(uid);
            relation.setType(RelationType.FRIEND_TO);
            relation.setTargetUid(targetUid);
            relation.setFocus(RelationFocus.NO);
            relation.setCreateTime(System.currentTimeMillis());
            relation.setUpdateTime(System.currentTimeMillis());
            relationList.add(relation);

            target = new Relation();
            target.setUid(targetUid);
            target.setType(RelationType.FRIEND_BY);
            target.setTargetUid(uid);
            target.setFocus(RelationFocus.NO);
            target.setCreateTime(System.currentTimeMillis());
            target.setUpdateTime(System.currentTimeMillis());
            relationList.add(target);
            return relationService.batchAdd(relationList);
        } else if(relation!=null && target!=null 
                && relation.getType()==RelationType.FRIEND_BY 
                && target.getType()==RelationType.FRIEND_TO) {
            target.setType(RelationType.FRIEND_EACH);
            relation.setType(RelationType.FRIEND_EACH);
            relationList.add(target);
            relationList.add(relation);
            return relationService.batchUpdate(relationList);
        } else if(relation!=null && target!=null 
                && relation.getType()==RelationType.NO_RELATION 
                && target.getType()==RelationType.NO_RELATION) {
            target.setType(RelationType.FRIEND_BY);
            relation.setType(RelationType.FRIEND_TO);
            relationList.add(target);
            relationList.add(relation);
            return relationService.batchUpdate(relationList);
        }
        return false;
    }
    
    /**
     * 取消关注
     * 
     * @author qianchun  @date 2016年2月24日 下午4:59:05
     * @param uid
     * @param targetUid
     * @return
     */
    public boolean cancelAttention(long uid, long targetUid) {
        Relation relation = relationService.findByUidAndTargetUid(uid, targetUid);
        Relation target = relationService.findByUidAndTargetUid(targetUid, uid);
        List<Relation> relationList = new ArrayList<Relation>();
        
        if(relation!=null && target!=null 
                && relation.getType()==RelationType.FRIEND_TO 
                && target.getType()==RelationType.FRIEND_BY) {
            relation.setType(RelationType.NO_RELATION);
            target.setType(RelationType.NO_RELATION);
            relationList.add(target);
            relationList.add(relation);
            return relationService.batchUpdate(relationList);
        } else if(relation!=null && target!=null 
                && relation.getType()==RelationType.FRIEND_EACH 
                && target.getType()==RelationType.FRIEND_EACH) {
            relation.setType(RelationType.FRIEND_BY);
            target.setType(RelationType.FRIEND_TO);
            relationList.add(target);
            relationList.add(relation);
            return relationService.batchUpdate(relationList);
        }
        return true;
    }
}    