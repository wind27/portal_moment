package com.wind.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wind.commons.Constant.MetaCode;
import com.wind.commons.Constant.MetaMsg;
import com.wind.commons.Meta;
import com.wind.utils.InterceptorUtils;
import com.wind.utils.LoggerUtil;
import com.wind.utils.ParamsUtil;

/**
 * 用户登录拦截器
 * 
 * @author qianchun
 */
public class LoggedInterceptor extends HandlerInterceptorAdapter {
    /**
     * 请求上行处理
     * 
     * @author qianchun @date 2015年6月6日 下午12:21:41
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        if (handler != null && handler instanceof HandlerMethod) {
            request.setCharacterEncoding("UTF-8");
            String url = request.getRequestURI().substring(request.getContextPath().length());
            
            if(InterceptorUtils.checkIsInWhiteList(request, whiteList) == true) {
                return true;
            }
            int flag = ParamsUtil.validate(request);
            if(flag==MetaCode.PARAMS_ERROR) {
                Meta meta = new Meta(
                        MetaCode.PARAMS_ERROR, 
                        MetaMsg.PARAMS_ERROR);
                String responseJson = InterceptorUtils.setResponse(meta, response);
                LoggerUtil.printRequestLog(request, url, 0, responseJson);
                return false;
            } else if(flag==MetaCode.NOT_LOGIN) {
                Meta meta = new Meta(
                        MetaCode.NOT_LOGIN, 
                        MetaMsg.NOT_LOGIN);
                String responseJson = InterceptorUtils.setResponse(meta, response);
                System.out.println(responseJson);
                return false;
            }
        }
        return false;
    }
    
    /**
     * 请求下行处理
     * 
     * @author qianchun @date 2015年6月6日 下午12:21:41
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        
        super.afterCompletion(request, response, handler, ex);
    }
    
//            String requstParamsJson = InterceptorUtils.getRequestParamsJson(request);
//            String responseDataJson = "";
//            FeedDto feedDto = InterceptorUtils.parseToFeedDto(requstParamsJson, FeedDto.class);
//            //校验
//            int validate = InterceptorUtils.validateUser(feedDto,
//                    request.getRequestURL().toString(), redisUtil, tokenService);
//            
//            long upTime = System.currentTimeMillis();
//            long uid = feedDto!=null ? feedDto.getUid() : 0;
//            if (validate == 1) {
//                String message = InterceptorUtils.getResponseStatus(
//                        RequestStatus.PARAMS_ERROR_CODE, RequestStatus.PARAMS_ERROR_MSG);
//            	responseDataJson = InterceptorUtils.setResponse(message, response);
//                String afterLog = RequestLog.getRequestLog(uid, url, 
//                		requstParamsJson, responseDataJson, System.currentTimeMillis()-upTime);
//                logger.info(afterLog);
//                return false;
//            }
//            else if (validate == 2 && InterceptorUtils.checkIsInWhiteList(request, whiteList) == false) {
//                String message = InterceptorUtils.getResponseStatus(
//                        RequestStatus.SYSTEM_NOT_LOGIN_CODE, RequestStatus.SYSTEM_NOT_LOGIN_MSG);
//                responseDataJson = InterceptorUtils.setResponse(message, response);
//                String afterLog = RequestLog.getRequestLog(uid, url, 
//                		requstParamsJson, responseDataJson, System.currentTimeMillis()-upTime);
//                logger.info(afterLog);
//                return false;
//            }
//            else {
//            	feedDto = InterceptorUtils.setLatlng(feedDto, httpUtil, gaodeUrl);
//                request.setAttribute("upTime", upTime);
//                request.setAttribute("feedDto", feedDto);
//                request.setAttribute("requstParamsJson", requstParamsJson);
//                return true;
//            }
 

//        Object upTimeObject = request.getAttribute("upTime");
//        Object requestJsonObject = request.getAttribute("requstParamsJson");
//        Object feedObject = request.getAttribute("feedDto");
//        String url = request.getRequestURI().substring(request.getContextPath().length());
//        long upTime = 0;
//        String responseDataJson="";
//        String requstParamsJson = null;
//        FeedDto feedDto = null;
//
//        if(upTimeObject!=null) {
//            upTime = Long.parseLong(request.getAttribute("upTime").toString());
//        }
//        if(requestJsonObject != null) {
//            requstParamsJson = requestJsonObject.toString();
//        }        
//        if(feedObject!=null) {
//            feedDto = (FeedDto) feedObject;
//        }        
//                
//        long costTime = System.currentTimeMillis()-upTime;
//        boolean isWhiteUrl = InterceptorUtils.checkIsInWhiteList(request, whiteList);
//        if(isWhiteUrl==false && feedDto==null) {//没有feedDto，用户未登录
//            String message = InterceptorUtils.getResponseStatus(
//                    RequestStatus.SYSTEM_NOT_LOGIN_CODE, 
//                    RequestStatus.SYSTEM_NOT_LOGIN_MSG);
//        	responseDataJson = InterceptorUtils.setResponse(message, response);
//        } else {
//        	responseDataJson = InterceptorUtils.getSuccessJson();
//        }
//
//        String afterLog = RequestLog.getRequestLog(feedDto.getUid(), url, 
//        		requstParamsJson, responseDataJson, costTime);
//        BigDataLog.printStatisticsLog(feedDto, url, new Date(upTime));
//        if(!(url.equals("/feed/unread") || url.equals("/notice/count"))) {
//            logger.info(afterLog);
//        }
//        
        
    //--------------------------------------------------------------------------------------------------------------------------
    private static final Logger logger = LoggerFactory.getLogger(LoggedInterceptor.class);
    private List<String> whiteList;
    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
    private static String urlFormat(String url) {
        if(url.endsWith("/")) {
            return url.substring(0, url.length()-1); 
        }
        return url;
    }
}
