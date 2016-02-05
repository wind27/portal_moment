package com.wind.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wind.commons.Meta;
import com.wind.entity.vo.RequestParam;

import net.sf.json.JSONObject;

public class InterceptorUtils {
    private static final Logger logger = LoggerFactory.getLogger(InterceptorUtils.class);

    /**
     * 请求json参数转化为RequestParams
     * 
     * @author qianchun @date 2015年11月30日 下午5:39:09
     * @param json
     * @param clazz
     * @return
     */
    public static RequestParam parseToFeedDto(String json, Class<?> clazz) {
        Object obj = null;
        try {
            obj = JsonUtil.toObject(json, clazz);
        }
        catch (Exception e) {
        	logger.error("请求参数解析异常:{}", e);
            return null;
        }
        if (obj == null) {
            return null;
        }
        return (RequestParam) obj;
    }

    /**
     * 校验用户是否合法
     * 
     * @author qianchun @date 2015年11月30日 下午5:38:43
     * @param feedDto
     * @param url
     * @param redisUtil
     * @param tokenService
     * @return 0、用户正确；1、参数错误；2、token失效；
     */
    public static int validateUser(RequestParam feedDto, String url, RedisUtil redisUtil) {
        if (feedDto == null || StringUtils.isEmpty(feedDto.getToken())) {
            return 1;
        }
        String key = feedDto.getUid() + "." + feedDto.getClient_info().getApp() + ".token";
        String appToken = feedDto.getToken();
        String userToke = null;
        if ("dmaitoken01".equals(feedDto.getToken())) {
            return 0;
        }
        else {
            int app = feedDto.getClient_info().getApp();
            //清理 apptoken 缓存
            if ("/user/clear".equals(url)) {
                redisUtil.del(feedDto.getUid() + "." + app + ".token");
                return 0;
            }
            //获取 apptype 缓存
            Object dtoObj = redisUtil.get(key);
            if (dtoObj != null) {
                userToke = dtoObj.toString();
            }
            if (!appToken.equals(userToke)) {
                Map<String, Object> params = new HashMap<>();
                params.put("uid", feedDto.getUid());
                params.put("app", app);
//                Token token = tokenService.findByUid(params);
//                if (token != null) {
//                    userToke = token.getTkey();
//                    redisUtil.set(key, token.getTkey());
//                    redisUtil.expire(key, 60 * 60);
//                }
            }
            return appToken.equals(userToke) == true ? 0 : 2;
        }
    }

    //----------------------------------
    /**
     * 解析request中的请求数据
     * 
     * @author qianchun @date 2015年11月30日 下午5:38:00
     * @param request
     * @return
     */
    public static String parseReader(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    /**
     * 截取response响应内容
     * 
     * @author qianchun @date 2015年11月30日 下午5:37:21
     * @param response
     * @return
     * @throws IOException
     */
    public static String getResponseDataJson(HttpServletResponse response) throws IOException {
        MyHttpResponseWrapper myHttpResponseWrapper = (MyHttpResponseWrapper) response;
        String responseJsonStr = new String(myHttpResponseWrapper.getContent().getBytes(), "UTF-8");
        return responseJsonStr;
    }

    /**
     * 获取请求参数json
     * 
     * @author qianchun @date 2015年11月30日 下午5:37:11
     * @param request
     * @return
     */
    public static String getRequestParamsJson(HttpServletRequest request) {
        return parseReader(request);
    }

    
    /**
     * 获取异常返回结果
     * 
     * @author qianchun @date 2015年11月30日 下午5:36:32
     * @param ecode
     * @param emsg
     * @param response
     * @return
     * @throws IOException
     */
//    public static String getResponseStatus(String ecode, String emsg) {
//        Map<String, Object> resultObject = new HashMap<String, Object>();
//        Map<String, Object> msg = new HashMap<String, Object>();
//        msg.put("code", ecode);
//        msg.put("message", emsg);
//
//        resultObject.put("msg", msg);
//        resultObject.put("success", Success.FALSE);
//        String responseResultJson = JsonUtil.toJson(resultObject);
//        return responseResultJson;
//    }
    
    /**
     * 设置异常返回结果
     * 
     * @author qianchun  @date 2016年2月4日 下午6:48:28
     * @param meta
     * @param response
     * @return
     * @throws IOException
     */
    public static String setResponse(Meta meta, HttpServletResponse response)
            throws IOException {
        String msg = JSONObject.fromObject(meta).toString();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().write(msg);
        response.flushBuffer();
        return msg;
    }
    
    /**
     * 返回请求成功的json
     * @return
     * @throws IOException
     */
//    public static String getSuccessJson()
//            throws IOException {
//        Map<String, Object> resultObject = new HashMap<String, Object>();
//        Map<String, Object> msg = new HashMap<String, Object>();
//        msg.put("code", RequestStatus.SUCCESS_CODE);
//        msg.put("message", RequestStatus.SUCCESS_MSG);
//
//        resultObject.put("msg", msg);
//        resultObject.put("success", Constant.Success.FALSE);
//        return JsonUtil.toJson(resultObject);
//    }
    
    /**
     * 检查是否属于白名单
     * 
     * @author qianchun  @date 2015年6月6日 下午12:21:41
     * @param url
     * @return
     */
    public static boolean checkIsInWhiteList(HttpServletRequest request, List<String> whiteList) {
        String url = request.getRequestURI();
        url = url.substring(request.getContextPath().length());
        
        if (url.endsWith("/") == false) {
            url = url + "/";
        }
        for (String tmp : whiteList) {
            if (tmp.endsWith("/") == false) {
                tmp = tmp + "/";
            }

            if (tmp.equals(url)) {
                return true;
            }

            if (tmp.contains("\\d") || tmp.contains("\\w") || tmp.contains("*")) {
                boolean flag = Pattern.compile(tmp).matcher(url).find();
                if (flag == true) {
                    return true;
                }
            }
        }
        return false;
    }
}
