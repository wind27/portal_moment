package com.wind.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wind.commons.Meta;
import com.wind.commons.StringUtils;
import com.wind.commons.Constant.MetaCode;
import com.wind.commons.Constant.MetaMsg;
import com.wind.entity.User;
import com.wind.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService userService;
    //---------------------------- 页面跳转 -----------------------------------
    //---------------------------- 数据处理 -----------------------------------
    /**
     * 更新用户状态
     * 
     * @author qianchun  @date 2016年2月19日 下午12:14:58
     * @param request
     * @return
     */
    @RequestMapping(value = "/status/update", method = RequestMethod.POST)
    public Object del(HttpServletRequest request) {
        Map<String, Object> resultObject = new HashMap<>();
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        if(StringUtils.isBlank(id) || StringUtils.isBlank(status)) {
            resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
            return resultObject;
        }
        boolean flag = false;
        try {
            flag = userService.updateStatus(Long.parseLong(id), Integer.parseInt(status));
        }
        catch (Exception e) {
            resultObject.put("meta", new Meta(MetaCode.SYSTEM_ERROR, MetaMsg.SYSTEM_ERROR));
        }
        
        if(flag==true) {
            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        } else {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
        }
        return resultObject;
    }
    
    /**
     * 更新用户信息
     * 
     * @author qianchun  @date 2016年2月19日 下午12:26:34
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(HttpServletRequest request) {
        Map<String, Object> resultObject = new HashMap<>();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String nick = request.getParameter("nick");
        String single = request.getParameter("single");

        if(StringUtils.isBlank(id)) {
            resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
            return resultObject;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        if(!StringUtils.isBlank(name)) {
            params.put("name", name);
        }
        if(!StringUtils.isBlank(nick)) {
            params.put("nick", nick);
        }
        if(!StringUtils.isBlank(single)) {
            params.put("single", single);
        }
        boolean flag = false;
        try {
            flag = userService.update(params);
        }
        catch (Exception e) {
            resultObject.put("meta", new Meta(MetaCode.SYSTEM_ERROR, MetaMsg.SYSTEM_ERROR));
        }
        if(flag==true) {
            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        } else {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
        }
        return resultObject;
    }
    
    /**
     * 用户列表
     * 
     * @author qianchun  @date 2016年2月19日 下午12:26:49
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object list(HttpServletRequest request) {
        return null;
    }
    
}
