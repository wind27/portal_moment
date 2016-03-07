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

import com.wind.commons.Constant.MetaCode;
import com.wind.commons.Constant.MetaMsg;
import com.wind.commons.Meta;
import com.wind.commons.ServiceResult;
import com.wind.entity.Moment;
import com.wind.entity.Param;
import com.wind.entity.User;
import com.wind.mongo.service.CommentService;
import com.wind.mongo.service.MomentService;
import com.wind.service.IUserService;

@Controller
@RequestMapping("/moment")
public class MomentController {
    @Resource
    IUserService userService;
    @Resource
    MomentService momentService;
    @Resource
    CommentService commentService;
    
    //---------------------------- 页面跳转 -----------------------------------
    @RequestMapping(value = "/p/all",method = RequestMethod.GET)
    public ModelAndView allPage(HttpServletRequest request) {
        return new ModelAndView("/moment/all");
    }
    @RequestMapping(value = "/p/my",method = RequestMethod.GET)
    public ModelAndView indexPage(HttpServletRequest request) {
        return new ModelAndView("/moment/my");
    }
    
    @RequestMapping(value = "/p/detail",method = RequestMethod.GET)
    public ModelAndView detailPage(HttpServletRequest request) {
    	return new ModelAndView("/moment/detail");
    }
    
    //---------------------------- 获取数据 -----------------------------------
    /**
     * 获取我的 moment
     * 
     * @author qianchun  @date 2016年3月7日 下午7:31:16
     * @param param
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/my", method = RequestMethod.GET)
    @ResponseBody
    public Object my(Param param, HttpServletRequest request) {
        long uid = param.getUid();
        Map<String, Object> resultObject = new HashMap<>();
        List<Map<String, Object>> momentMapList = new ArrayList<>();
        
        //根据用户id获取moment
        List<Long> uids = new ArrayList<Long>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", uid);
        ServiceResult serviceResult = momentService.findByUids(uids, param.getPstart(), param.getPlimit());
        
        if(serviceResult.isSuccess()==false) {
        	resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
            resultObject.put("data", momentMapList);
            return resultObject;
        }
        List<Moment> momentList = (List<Moment>) serviceResult.getData();
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
        resultObject.put("data", momentMapList!=null?momentMapList:new ArrayList<>());
        return resultObject;
    }
    
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    @ResponseBody
//    public Object all(Param param, HttpServletRequest request) {
//        long uid = param.getUid();
//        Map<String, Object> resultObject = new HashMap<>();
//        List<Map<String, Object>> momentMapList = new ArrayList<>();
//        
//        //根据用户id获取moment
//        List<Long> uids = new ArrayList<Long>();
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("uid", uid);
//        List<Moment> momentList = momentSqlService.findList(params);
//        
//        //获取moment中的用户ids
//        if(momentList!=null) {
//            for(int i=0; i<momentList.size(); i++) {
//                Moment moment = momentList.get(i);
//                if(moment!=null) {
//                    uids.add(moment.getUid());
//                }
//            }
//        }
//        
//        //根据用户ids获取用户信息
//        Map<Long, User> userMap = null;
//        if(uids!=null && uids.size()>0) {
//            params.clear();
//            params.put("ids", uids);
//            userMap = userService.findMap(params);
//        }
//
//        //封装map
//        if(momentList!=null) {
//            momentMapList = moment2MapList(momentList, userMap);
//        }
//        
//        //封装返回数据
//        resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
//        resultObject.put("data", momentMapList);
//        return resultObject;
//    }
//    
//    /**
//     * 分頁查詢所有的moment
//     * 
//     * @author qianchun  @date 2016年2月3日 下午7:07:05
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @ResponseBody
//    public Object findPageList(HttpServletRequest request) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        
//        params.put("uid", 1);
//        List<Moment> momentList = momentSqlService.findList(params);
//        JSONArray array = JSONArray.fromObject(momentList);
//        System.out.println(array);
//
//        
//        PageBounds pager = new PageBounds(1, 3);
//        PageList<Moment> momentPageList= (PageList<Moment>) momentSqlService.findPageList(null, pager);
//        return momentPageList;
//    }
//    
//    /**
//     * 收藏
//     * 
//     * @author qianchun  @date 2016年2月26日 下午5:29:27
//     * @param param
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/collect", method = RequestMethod.POST)
//    @ResponseBody
//    public Object collect(Param param, HttpServletRequest request) {
//        Map<String, Object> resultObject = new HashMap<>();
//        
//        if(param==null || param.getUid()==0 || param.getMomentid()==0) {
//            resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
//            return resultObject;
//        }
//        long momentId = param.getMomentid();
//        boolean flag = true;
//        try {
//            Moment moment = momentSqlService.findById(momentId);
//            if(moment==null) {
//                resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
//                return resultObject;
//            }
//            JSONArray collectionUidsJson = JSONArray.fromObject(moment.getCollectionUid());
//            if(collectionUidsJson==null) {
//                collectionUidsJson = new JSONArray();
//            }
//            if(!collectionUidsJson.contains(momentId)) {
//                collectionUidsJson.add(param.getUid());
//                Map<String, Object> params = new HashMap<String, Object>();
//                params.put("collectionUid", collectionUidsJson.toString());
//                params.put("id", momentId);
//                flag = momentSqlService.update(params);
//            }
//        }
//        catch (Exception e) {
//            resultObject.put("meta", new Meta(MetaCode.SYSTEM_ERROR, MetaMsg.SYSTEM_ERROR));
//            return resultObject;
//        }
//        if(flag==true) {
//            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
//        } else {
//            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
//        }
//        return resultObject;
//    }
//    
//    /**
//     * 点赞
//     * 
//     * @author qianchun  @date 2016年2月26日 下午5:30:22
//     * @param param
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/praise", method = RequestMethod.POST)
//    @ResponseBody
//    public Object praise(Param param, HttpServletRequest request) {
//        Map<String, Object> resultObject = new HashMap<>();
//        
//        if(param==null || param.getUid()==0 || param.getMomentid()==0) {
//            resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
//            return resultObject;
//        }
//        long momentId = param.getMomentid();
//        long uid = param.getUid();
//        boolean flag = true;
//        try {
//            Moment moment = momentSqlService.findById(momentId);
//            if(moment==null) {
//                resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
//                return resultObject;
//            }
//            JSONArray praiseUidsJson = JSONArray.fromObject(moment.getPraiseUid());
//            if(praiseUidsJson==null) {
//                praiseUidsJson = new JSONArray();
//            }
//            if(!praiseUidsJson.contains(uid)) {
//                praiseUidsJson.add(uid);
//                Map<String, Object> params = new HashMap<String, Object>();
//                params.put("praiseUid", praiseUidsJson.toString());
//                params.put("id", momentId);
//                flag = momentSqlService.update(params);
//            }
//        }
//        catch (Exception e) {
//            resultObject.put("meta", new Meta(MetaCode.SYSTEM_ERROR, MetaMsg.SYSTEM_ERROR));
//            return resultObject;
//        }
//        if(flag==true) {
//            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
//        } else {
//            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
//        }
//        return resultObject;
//    }
//    
//    /**
//     * 取消收藏
//     * 
//     * @author qianchun  @date 2016年2月26日 下午5:29:27
//     * @param param
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/collect/cancel", method = RequestMethod.POST)
//    @ResponseBody
//    public Object collectCancel(Param param, HttpServletRequest request) {
//        Map<String, Object> resultObject = new HashMap<>();
//        
//        if(param==null || param.getUid()==0 || param.getMomentid()==0) {
//            resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
//            return resultObject;
//        }
//        long momentId = param.getMomentid();
//        boolean flag = true;
//        try {
//            Moment moment = momentSqlService.findById(momentId);
//            if(moment==null) {
//                resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
//                return resultObject;
//            }
//            JSONArray collectionUidsJson = JSONArray.fromObject(moment.getCollectionUid());
//            if(collectionUidsJson!=null && collectionUidsJson.contains(momentId)) {
//                collectionUidsJson.remove(param.getUid());
//                Map<String, Object> params = new HashMap<String, Object>();
//                params.put("collectionUid", collectionUidsJson.toString());
//                params.put("id", momentId);
//                flag = momentSqlService.update(params);
//            }
//        }
//        catch (Exception e) {
//            resultObject.put("meta", new Meta(MetaCode.SYSTEM_ERROR, MetaMsg.SYSTEM_ERROR));
//            return resultObject;
//        }
//        if(flag==true) {
//            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
//        } else {
//            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
//        }
//        return resultObject;
//    }
//    
//    /**
//     * 取消点赞
//     * 
//     * @author qianchun  @date 2016年2月26日 下午5:30:22
//     * @param param
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/praise/cancel", method = RequestMethod.POST)
//    @ResponseBody
//    public Object praiseCancel(Param param, HttpServletRequest request) {
//        Map<String, Object> resultObject = new HashMap<>();
//        
//        if(param==null || param.getUid()==0 || param.getMomentid()==0) {
//            resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
//            return resultObject;
//        }
//        long momentId = param.getMomentid();
//        long uid = param.getUid();
//        boolean flag = true;
//        try {
//            Moment moment = momentSqlService.findById(momentId);
//            if(moment==null) {
//                resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
//                return resultObject;
//            }
//            JSONArray praiseUidsJson = JSONArray.fromObject(moment.getPraiseUid());
//            if(praiseUidsJson!=null && praiseUidsJson.contains(uid)) {
//                praiseUidsJson.remove(uid);
//                Map<String, Object> params = new HashMap<String, Object>();
//                params.put("praiseUid", praiseUidsJson.toString());
//                params.put("id", momentId);
//                flag = momentSqlService.update(params);
//            }
//        }
//        catch (Exception e) {
//            resultObject.put("meta", new Meta(MetaCode.SYSTEM_ERROR, MetaMsg.SYSTEM_ERROR));
//            return resultObject;
//        }
//        if(flag==true) {
//            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
//        } else {
//            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
//        }
//        return resultObject;
//    }
//  //---------------------------- 结果封装map -----------------------------------
//    public List<Map<String, Object>> moment2MapAll(List<Moment> momentList, Map<Long, User> userMap) {
//        List<Map<String, Object>> momentMapList = new ArrayList<>();
//        if(momentList!=null) {
//            for(int i=0; i<momentList.size(); i++) {
//                Moment moment = momentList.get(i);
//                if(moment!=null) {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("id", moment.getId());
//                    map.put("status", moment.getStatus());
//                    map.put("title", moment.getTitle());
//                    map.put("content", moment.getContent());
//                    
//                    map.put("view_num", moment.getViewNum());
//                    map.put("praise_num", moment.getViewNum());
//                    map.put("collection_num", moment.getViewNum());
//                    
//                    map.put("praise_uid", moment.getPraiseUid());
//                    map.put("collection_uid", moment.getCollectionUid());
//
//                    map.put("uid", moment.getUid());
//                    map.put("uname", moment.getUid());
//                    map.put("head_image", moment.getUid());
//                    
//                    map.put("create_time", moment.getCreateTime());
//                    map.put("update_time", moment.getUpdateTime());
//                    map.put("publish_time", moment.getPublishTime());
//                }
//            }
//        }
//        return momentMapList;
//    }
//    
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
