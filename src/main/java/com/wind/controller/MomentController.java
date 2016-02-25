package com.wind.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.wind.commons.Constant.MetaCode;
import com.wind.commons.Constant.MetaMsg;
import com.wind.commons.Meta;
import com.wind.entity.Moment;
import com.wind.entity.User;
import com.wind.service.IMomentService;
import com.wind.service.IUserService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/moment")
public class MomentController {
    @Resource
    IMomentService momentService;
    @Resource
    IUserService userService;
    
    //---------------------------- 页面跳转 -----------------------------------
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        return new ModelAndView("/moment/index");
    }
    
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ModelAndView detail(HttpServletRequest request) {
    	return new ModelAndView("/moment/detail");
    }
    
    //---------------------------- 获取数据 -----------------------------------
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    @ResponseBody
    public Object list(HttpServletRequest request) {
        long uid = Long.parseLong(request.getParameter("uid"));
        Map<String, Object> resultObject = new HashMap<>();
        List<Map<String, Object>> momentMapList = new ArrayList<>();
        
        //根据用户id获取moment
        List<Long> uids = new ArrayList<Long>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", uid);
        List<Moment> momentList = momentService.findList(params);
        
        //获取moment中的用户ids
        if(momentList!=null) {
            for(int i=0; i<momentList.size(); i++) {
                Moment moment = momentList.get(i);
                if(moment!=null) {
                    uids.add(moment.getUid());
                }
            }
        }
        
        //根据用户ids获取用户信息
        Map<Long, User> userMap = null;
        if(uids!=null && uids.size()>0) {
            params.clear();
            params.put("ids", uids);
            userMap = userService.findMap(params);
        }

        //封装map
        if(momentList!=null) {
            momentMapList = moment2MapList(momentList, userMap);
        }
        
        //封装返回数据
        resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        resultObject.put("data", momentMapList);
        return resultObject;
    }
    
    /**
     * 分頁查詢所有的moment
     * 
     * @author qianchun  @date 2016年2月3日 下午7:07:05
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object findPageList(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        
        params.put("uid", 1);
        List<Moment> momentList = momentService.findList(params);
        JSONArray array = JSONArray.fromObject(momentList);
        System.out.println(array);

        
        PageBounds pager = new PageBounds(1, 3);
        PageList<Moment> momentPageList= (PageList<Moment>) momentService.findPageList(null, pager);
        return momentPageList;
    }
  //---------------------------- 结果封装map -----------------------------------
    public List<Map<String, Object>> moment2MapAll(List<Moment> momentList, Map<Long, User> userMap) {
        List<Map<String, Object>> momentMapList = new ArrayList<>();
        if(momentList!=null) {
            for(int i=0; i<momentList.size(); i++) {
                Moment moment = momentList.get(i);
                if(moment!=null) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", moment.getId());
                    map.put("status", moment.getStatus());
                    map.put("title", moment.getTitle());
                    map.put("content", moment.getContent());
                    
                    map.put("view_num", moment.getViewNum());
                    map.put("praise_num", moment.getViewNum());
                    map.put("collection_num", moment.getViewNum());
                    
                    map.put("praise_uid", moment.getPraiseUid());
                    map.put("collection_uid", moment.getCollectionUid());

                    map.put("uid", moment.getUid());
                    map.put("uname", moment.getUid());
                    map.put("head_image", moment.getUid());
                    
                    map.put("create_time", moment.getCreateTime());
                    map.put("update_time", moment.getUpdateTime());
                    map.put("publish_time", moment.getPublishTime());
                }
            }
        }
        return momentMapList;
    }
    
    public List<Map<String, Object>> moment2MapList(List<Moment> momentList, Map<Long, User> userMap) {
        List<Map<String, Object>> momentMapList = new ArrayList<>();
        if(momentList!=null) {
            for(int i=0; i<momentList.size(); i++) {
                Moment moment = momentList.get(i);
                if(moment!=null) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", moment.getId());
                    map.put("content", moment.getContent());
                    
                    map.put("view_num", moment.getViewNum());
                    map.put("praise_num", moment.getViewNum());
                    map.put("collection_num", moment.getViewNum());

                    if(userMap!=null && userMap.containsKey(moment.getUid())) {
                        User user = userMap.get(moment.getUid());
                        map.put("uid", user.getId());
                        map.put("uname", user.getName());
                        map.put("head_image", user.getHeadImage());
                    } else {
                        map.put("uid", moment.getUid());
                        map.put("uname", "");
                        map.put("head_image", "");
                    }
                    
                    map.put("create_time", moment.getCreateTime());
                    map.put("update_time", moment.getUpdateTime());
                    map.put("publish_time", moment.getPublishTime());
                }
            }
        }
        
        return momentMapList;
    }
}
