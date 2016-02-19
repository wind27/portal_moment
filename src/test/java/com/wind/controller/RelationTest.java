package com.wind.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
    
    
    /**
     * 添加用户
     * 
     * @author qianchun  @date 2016年2月1日 下午3:42:16
     */
    public Relation create(Relation relation) {
        return relationService.add(relation);
    }
    
    public boolean updateByParams(Map<String, Object> params) {
        return relationService.updateByParams(params);
    }
    public List<Relation> findByUid(long id) {
        return relationService.findByUid(id);
    }
    
    @Test
    public void main() {
        boolean flag = create(1001, 1002);
        
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
    
//        
//        Relation relationTmp = relationService.findByUidAndTargetUid(uid, targetUid);
//        Relation targetRelationTmp = relationService.findByUidAndTargetUid(targetUid, uid);

    public boolean create(long uid, long targetUid) {
        List<Relation> relationList = new ArrayList<>();
        
        Relation relation = new Relation();
        relation.setUid(uid);
        relation.setType(RelationType.FRIEND_TO);
        relation.setTargetUid(targetUid);
        relation.setFocus(RelationFocus.NO);
        relation.setCreateTime(System.currentTimeMillis());
        relation.setUpdateTime(System.currentTimeMillis());
        relationService.add(relation);
        relationList.add(relation);
    
        Relation relationTarget = new Relation();
        relationTarget.setUid(targetUid);
        relationTarget.setType(RelationType.FRIEND_BY);
        relationTarget.setTargetUid(uid);
        relationTarget.setFocus(RelationFocus.NO);
        relationTarget.setCreateTime(System.currentTimeMillis());
        relationTarget.setUpdateTime(System.currentTimeMillis());
        relationService.add(relationTarget);
        relationList.add(relationTarget);
        
        return relationService.batchAdd(relationList);
    }
}    