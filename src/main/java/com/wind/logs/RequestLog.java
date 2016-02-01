package com.wind.logs;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wind.utils.DateUtil;

/**
 * 记录请求日志
 * 
 * @author qianchun
 * @date 2015年11月23日 下午4:13:04
 */
public class RequestLog {
    /**
     * 获取上行日志
     * 
     * @author qianchun @date 2015年11月30日 下午5:36:55
     * @param url
     * @param requstParamsJson
     * @param uid
     * @param request
     * @return
     * @throws IOException
     */
    public static String getBeforLog(String url, String requstParamsJson, long uid,
            HttpServletRequest request) throws IOException {
        StringBuilder beforLog = new StringBuilder();
        
        beforLog.append("[");
        beforLog.append(DateUtil.format(new Date(), null));
        beforLog.append("] ");

        beforLog.append("[");
        beforLog.append("REQUEST");
        beforLog.append("] ");
        
        beforLog.append("[");
        beforLog.append(url);
        beforLog.append("] ");
        
        beforLog.append("[");
        beforLog.append(uid);
        beforLog.append("] ");

        beforLog.append("[");
        beforLog.append(requstParamsJson);
        beforLog.append("]");
        return beforLog.toString();
    }
    /**
     * 获取下行日志
     * 
     * @author qianchun @date 2015年9月21日 下午3:06:40
     * @param requestid
     * @param url
     * @param headerJson
     * @param requstParamsJson
     * @param upTime
     * @param responseResultJson
     * @param systemId
     * @param response
     * @return
     */
    public static String getAfterLog(String url, String requstParamsJson, long costTime,
            String responseResultJson, long uid, HttpServletResponse response) {
        StringBuilder afterLog = new StringBuilder();
        
        afterLog.append("[");
        afterLog.append(DateUtil.format(new Date(), null));
        afterLog.append("] ");

        afterLog.append("[");
        afterLog.append("RESPONSE");
        afterLog.append("] ");
        
        afterLog.append("[");
        afterLog.append(url);
        afterLog.append("] ");
        
        afterLog.append("[");
        afterLog.append(uid);
        afterLog.append("] ");
        
        afterLog.append("[");
        afterLog.append(requstParamsJson);
        afterLog.append("] ");
        
        afterLog.append("[");
        afterLog.append(responseResultJson);
        afterLog.append("] ");
        
        afterLog.append("[");
        afterLog.append(costTime);
        afterLog.append("毫秒");
        afterLog.append("]");
        
        return afterLog.toString();
    }
    
    public static String getRequestLog(long uid, String url, String requstParamsJson, String responseResultJson, long costTime) {
        StringBuilder afterLog = new StringBuilder();
        responseResultJson = "操作成功";
        afterLog.append("[");
        afterLog.append("REQUEST");
        afterLog.append("] ");
        
        afterLog.append("[");
        afterLog.append("uid:");
        afterLog.append(uid);
        afterLog.append("] ");
        
        afterLog.append("[");
        afterLog.append("url:");
        afterLog.append(url);
        afterLog.append("] ");
        
        afterLog.append("[");
        afterLog.append("params:");
        afterLog.append(requstParamsJson);
        afterLog.append("] ");
        
        afterLog.append("[");
        afterLog.append("result:");
        afterLog.append(responseResultJson);
        afterLog.append("] ");
        
        afterLog.append("[");
        afterLog.append("costTime:");
        afterLog.append(costTime);
        afterLog.append("毫秒");
        afterLog.append("] ");
        
        return afterLog.toString();
    }
}
