package com.wind.utils;

import javax.servlet.http.HttpServletRequest;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wind.commons.Constant;
import com.wind.commons.Constant.MetaCode;
import com.wind.commons.StringUtils;

public class ParamsUtil {
    
    /**
     * 获取分页信息
     * 
     * @author qianchun  @date 2016年2月4日 下午6:26:16
     * @param request
     * @return
     */
    public static PageBounds getPageBounds(HttpServletRequest request) {
        int start = 0;
        int limit = Constant.LIMIT_50;
        if(!StringUtils.isBlank(request.getParameter("start"))) {
            try {
                start = Integer.parseInt(request.getParameter("start"));
            } catch (Exception e) {
            }
        }
        if(!StringUtils.isBlank(request.getParameter("limit"))) {
            try {
                limit = Integer.parseInt(request.getParameter("limit"));
            } catch(Exception e) {
            }
        }
        PageBounds pageBounds = new PageBounds(start, limit);
        return pageBounds;
    }
    
    /**
     * 获取用户uid
     * 
     * @author qianchun  @date 2016年2月4日 下午6:26:26
     * @param request
     * @return
     */
    public static long getUid(HttpServletRequest request) {
        long uid = 0;
        
        if(!StringUtils.isBlank(request.getParameter("uid"))) {
            try {
                uid = Integer.parseInt(request.getParameter("uid"));
            } catch (Exception e) {
            }
        }
        return uid;
    }
    
    public static String getUname(HttpServletRequest request) {
        return request.getParameter("uname");
    }
    
    public static String getToken(HttpServletRequest request) {
        return request.getParameter("token");
    }
    
    
    /**
     * 请求基础参数校验
     * 
     * @author qianchun  @date 2016年2月4日 下午6:39:20
     * @param request
     * @param redisUtil
     * @return
     */
    public static int validate(HttpServletRequest request) {
        long uid = getUid(request);
        String uname = getUname(request);
        String token = getToken(request);
        
        if(uid == 0) {
            return MetaCode.PARAMS_ERROR;
        }
        if(StringUtils.isBlank(uname)) {
            return MetaCode.PARAMS_ERROR;
        }
        if(StringUtils.isBlank(token)) {
            return MetaCode.PARAMS_ERROR;
        }
        
        
        //token 校验：redis校验 + 数据库校验
//        return RequestStatus.SYSTEM_NOT_LOGIN_CODE;
        return MetaCode.SUCCESS;
    }
    
}
