package com.wind.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wind.commons.Meta;
import com.wind.commons.Constant.MetaCode;
import com.wind.commons.Constant.MetaMsg;
import com.wind.commons.Constant.RelationFocus;
import com.wind.commons.Constant.RelationType;
import com.wind.entity.Relation;
import com.wind.service.IRelationService;

@Controller
@RequestMapping("/relation")
public class RelationController {
    private final static Logger logger = LoggerFactory.getLogger(RelationController.class);
    
    @Resource
    IRelationService relationService;
    
    //---------------------------- 页面跳转 -----------------------------------
    //---------------------------- 数据处理 -----------------------------------
    /**
     * 关注
     * 
     * @author qianchun  @date 2016年2月24日 下午3:21:22
     * @param uid
     * @param targetUid
     * @return
     */
    @RequestMapping(value = "/attention/pay", method = RequestMethod.POST)
    public Object payAttention(long uid, long targetUid) {
        Map<String, Object> resultObject = new HashMap<>();
        boolean flag = false;
        
        try {
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
                flag = relationService.batchAdd(relationList);
            } else if(relation!=null && target!=null 
                    && relation.getType()==RelationType.FRIEND_BY 
                    && target.getType()==RelationType.FRIEND_TO) {
                target.setType(RelationType.FRIEND_EACH);
                relation.setType(RelationType.FRIEND_EACH);
                relationList.add(target);
                relationList.add(relation);
                flag = relationService.batchUpdate(relationList);
            } else if(relation!=null && target!=null 
                    && relation.getType()==RelationType.NO_RELATION 
                    && target.getType()==RelationType.NO_RELATION) {
                target.setType(RelationType.FRIEND_BY);
                relation.setType(RelationType.FRIEND_TO);
                relationList.add(target);
                relationList.add(relation);
                flag = relationService.batchUpdate(relationList);
            }
        }
        catch (Exception e) {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
            logger.error(RelationController.class.getName() + " : " + "添加关注异常！！！");
            e.printStackTrace();
        }
        
        if(flag==true) {
            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        } else {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
        }
        return resultObject;
    }
    
    /**
     * 取消关注
     * 
     * @author qianchun  @date 2016年2月24日 下午4:59:05
     * @param uid
     * @param targetUid
     * @return
     */
    @RequestMapping(value = "/attention/cancel", method = RequestMethod.POST)
    public Object cancelAttention(long uid, long targetUid) {
        Map<String, Object> resultObject = new HashMap<>();
        boolean flag = false;
        try {
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
                flag = relationService.batchUpdate(relationList);
            } else if(relation!=null && target!=null 
                    && relation.getType()==RelationType.FRIEND_EACH 
                    && target.getType()==RelationType.FRIEND_EACH) {
                relation.setType(RelationType.FRIEND_BY);
                target.setType(RelationType.FRIEND_TO);
                relationList.add(target);
                relationList.add(relation);
                flag = relationService.batchUpdate(relationList);
            }
        }
        catch (Exception e) {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
            logger.error(RelationController.class.getName() + " : " + "取消关注异常！！！");
            e.printStackTrace();
        }
        if(flag==true) {
            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        } else {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
        }
        return resultObject;
    }
    
    /**
     * 我的关注
     * 
     * @author qianchun  @date 2016年2月24日 下午5:39:19
     * @param uid
     * @param targetUid
     * @return
     */
    @RequestMapping(value = "/attention/my", method = RequestMethod.GET)
    public Object myAttention(long uid, long targetUid) {
        Map<String, Object> resultObject = new HashMap<>();
        return resultObject;
    }
    
    /**
     * 关注我的
     * 
     * @author qianchun  @date 2016年2月24日 下午5:39:27
     * @param uid
     * @param targetUid
     * @return
     */
    @RequestMapping(value = "/attention/other", method = RequestMethod.GET)
    public Object otherUserAttention(long uid, long targetUid) {
        Map<String, Object> resultObject = new HashMap<>();
        return resultObject;
    }
    
    /**
     * 互相关注
     * 
     * @author qianchun  @date 2016年2月24日 下午5:39:27
     * @param uid
     * @param targetUid
     * @return
     */
    @RequestMapping(value = "/attention/each", method = RequestMethod.GET)
    public Object eachAttention(long uid, long targetUid) {
        Map<String, Object> resultObject = new HashMap<>();
        return resultObject;
    }
}
