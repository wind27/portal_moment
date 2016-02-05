package com.wind.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wind.entity.vo.RequestParam;

import net.sf.json.JSONObject;

/**
 * json 解析工具
 * 
 * @author qianchun
 * @date 2015年8月18日 上午9:57:35
 */
public final class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static String toJson(Object obj) {
        try {
            return JSONObject.fromObject(obj).toString();
        }
        catch (Exception e) {
            logger.info("请求参数解析异常", e);
            return null;
        }
    }

    @SuppressWarnings("static-access")
    public static Object toObject(String json, Class<?> clazz) {
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            return (RequestParam) jsonObject.toBean(jsonObject, clazz);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("解析参数异常：" + json);
            return null;
        }
    }
}