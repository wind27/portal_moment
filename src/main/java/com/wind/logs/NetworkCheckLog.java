package com.wind.logs;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wind.utils.DateUtil;

import net.sf.json.JSONObject;

/**
 * 记录网络测试日志
 * 
 * @author qianchun
 * @date 2015年12月11日 下午6:15:45
 */
public class NetworkCheckLog {
    private final static Logger logger = LoggerFactory.getLogger(NetworkCheckLog.class);
    
//    public static void printNetworkLog(FeedDto feedDto) {
//        JSONObject resultJson = new JSONObject();
//        JSONObject userInfoJson = new JSONObject();
//        JSONObject networkLogJson = new JSONObject();
//        if(feedDto!=null) {
//            userInfoJson.put("uid", feedDto.getUid());
//            userInfoJson.put("apptoken", feedDto.getApptoken());
//            networkLogJson.put("log", feedDto.getNetworkLog());
//        }
//        
//        
//        resultJson.put("user", userInfoJson);
//        resultJson.put("log", networkLogJson);
//        logger.info("[" +DateUtil.format(new Date(), null)+"]"
//                  + " [" + userInfoJson.toString() +"]"
//                  + " [" + networkLogJson.toString() + "]");
        
        
//        if(feedDto!=null && feedDto.getClient_info()!=null) {
//            userInfoJson.put("version", feedDto.getClient_info().getVersion());
//            userInfoJson.put("os_version", feedDto.getClient_info().getOsVersion());
//            userInfoJson.put("device_id", feedDto.getClient_info().getDevice_id());
//            userInfoJson.put("network", feedDto.getClient_info().getNetwork());
//            userInfoJson.put("isp", feedDto.getClient_info().getIsp());
//            userInfoJson.put("app", feedDto.getClient_info().getApp());
//            userInfoJson.put("ssid", feedDto.getClient_info().getSsid());
//            userInfoJson.put("ip", feedDto.getClient_info().getIp());
//        }
//    }
}
