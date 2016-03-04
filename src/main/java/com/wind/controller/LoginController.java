package com.wind.controller;

import java.util.HashMap;
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
import com.wind.commons.Constant.UserStatus;
import com.wind.commons.Meta;
import com.wind.entity.User;
import com.wind.service.IUserService;

@Controller
@RequestMapping("/")
public class LoginController {
    @Resource
    IUserService userService;
    
    //---------------------------- 页面跳转 -----------------------------------
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request) {
        return new ModelAndView("/login");
    }
    //---------------------------- 数据处理 -----------------------------------
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(HttpServletRequest request) {
        Map<String, Object> resultObject = new HashMap<>();
        String pwd = request.getParameter("pwd").trim();
        String email = request.getParameter("email");
        
        User user = null;
        try {
            user = userService.findByEmail(email);
            
            if(user==null || !user.getPwd().equals(pwd)) {
                resultObject.put("meta", new Meta(MetaCode.USERNAME_PASSWORD_WRONG, MetaMsg.USERNAME_PASSWORD_WRONG));
                return resultObject;
            } else if(user.getStatus()==UserStatus.DISABLE) {
                resultObject.put("meta", new Meta(MetaCode.USER_DISABLE, MetaMsg.USER_DISABLE));
                return resultObject;
            }
        }
        catch (Exception e) {
            resultObject.put("meta", new Meta(MetaCode.SYSTEM_ERROR, MetaMsg.SYSTEM_ERROR));
        }
        if(user!=null) {
            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        } else {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
        }
        
        return resultObject;
    }
    
    /**
     * 新增用户
     * 
     * @author qianchun  @date 2016年2月19日 下午12:14:47
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object add(HttpServletRequest request) {
        Map<String, Object> resultObject = new HashMap<>();
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String email = request.getParameter("email");
        
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);
        user.setEmail(email);
        try {
            user = userService.add(user);
        }
        catch (Exception e) {
            resultObject.put("meta", new Meta(MetaCode.SYSTEM_ERROR, MetaMsg.SYSTEM_ERROR));
        }
        if(user!=null) {
            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        } else {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
        }
        return resultObject;
    }
}
