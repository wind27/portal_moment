package com.wind.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wind.entity.vo.RequestParam;

import net.sf.json.JSONArray;
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
            logger.error("JsonUtil : 解析 JSON 异常 : " + obj, e);
            return null;
        }
    }

    @SuppressWarnings("static-access")
    public static Object toObject(String str, Class<?> clazz) {
        try {
            JSONObject jsonObject = JSONObject.fromObject(str);
            return (RequestParam) jsonObject.toBean(jsonObject, clazz);
        }
        catch (Exception e) {
            logger.error("JsonUtil : 解析 "+clazz.getName()+" 异常 : " + str, e);
            return null;
        }
    }
    public static JSONArray toJSONArray(String str) {
        try {
            return JSONArray.fromObject(str);
        }
        catch (Exception e) {
            logger.error("JsonUtil : 解析 JSONArray 异常 : " + str, e);
            return new JSONArray();
        }
    }
}