package com.wind.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
    
    /**
     * 打印请求日志
     * 
     * @author qianchun  @date 2016年2月4日 下午6:58:50
     * @param uid
     * @param uname
     * @param token
     * @param url
     * @param costTime
     * @param ip
     */
    public static void printRequestLog(HttpServletRequest request, String url, int costTime, String responseJson) {
        long uid =ParamsUtil.getUid(request);
        String uname = ParamsUtil.getUname(request);
        String token = ParamsUtil.getToken(request);
        
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("uname", uname);
        map.put("token", token);
        map.put("url", url);
        map.put("costTime", costTime);
        map.put("response_json", responseJson);
        
        String requestLog = JsonUtil.toJson(map);
        logger.info(requestLog);
    }
}
