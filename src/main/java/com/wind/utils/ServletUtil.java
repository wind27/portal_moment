package com.wind.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class ServletUtil {

  public static String getClientIp(HttpServletRequest request) {
    String clientIp = request.getHeader("x-forwarded-for");
    if ((clientIp == null) || (clientIp.length() == 0) || ("unknown".equalsIgnoreCase(clientIp))) {
      clientIp = request.getHeader("Proxy-Client-IP");
    }
    if ((clientIp == null) || (clientIp.length() == 0) || ("unknown".equalsIgnoreCase(clientIp))) {
      clientIp = request.getHeader("WL-Proxy-Client-IP");
    }
    if ((clientIp == null) || (clientIp.length() == 0) || ("unknown".equalsIgnoreCase(clientIp))) {
      clientIp = request.getRemoteAddr();
    }
    return clientIp;
  }

  /**
   * 获取bigdecimal值
   * 
   * @param request
   * @param params
   * @param defaultValue
   * @return
   */
  public static BigDecimal getDecimal(HttpServletRequest request, String params, BigDecimal defaultValue) {
    String tmpStr = getStr(request, params, "");
    if ("".equals(tmpStr))
      return defaultValue;
    try {
      Double tmp = Double.parseDouble(tmpStr);
      defaultValue = BigDecimal.valueOf(tmp);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return defaultValue;
  }

  /**
   * 获取文件扩展名
   * 
   * @param file
   * @return
   */
  public static String getFileType(String fileName) {
    if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
      return fileName.substring(fileName.lastIndexOf(".") + 1);
    } else {
      return "";
    }
  }

  /**
   * 从request中获取参数，并转换为int
   * 
   * @param request
   * @param params
   * @param defaultValue
   * @return
   */
  public static int getInt(HttpServletRequest request, String params, int defaultValue) {
    String tmpStr = request.getParameter(params);
    if (tmpStr == null)
      return defaultValue;
    int tmpInt = defaultValue;
    try {
      tmpInt = Integer.parseInt(tmpStr);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return tmpInt;
  }

  /**
   * 从http head 获取int值
   * 
   * @param request
   * @param params
   * @param defaultValue
   * @return
   */
  public static int getIntFromHead(HttpServletRequest request, String params, int defaultValue) {
    String tmpStr = request.getHeader(params);
    if (tmpStr == null)
      return defaultValue;
    int tmpInt = defaultValue;
    try {
      tmpInt = Integer.parseInt(tmpStr);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return tmpInt;
  }

  public static long getLong(HttpServletRequest request, String param, long defaultValue) {
    String tmpStr = request.getParameter(param);
    if (tmpStr == null)
      return defaultValue;
    long tmp = defaultValue;
    try {
      tmp = Long.parseLong(tmpStr);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return tmp;
  }

  public static long getLongFromHead(HttpServletRequest request, String params, long defaultValue) {
    String tmpStr = request.getHeader(params);
    if (tmpStr == null)
      return defaultValue;
    long tmp = defaultValue;
    try {
      tmp = Long.parseLong(tmpStr);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return tmp;
  }

  public static short getShort(HttpServletRequest request, String param, short defaultValue) {
    String tmpStr = request.getParameter(param);
    if (tmpStr == null)
      return defaultValue;
    short tmp = defaultValue;
    try {
      tmp = Short.parseShort(tmpStr);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return tmp;
  }

  /**
   * 获取request范围里面的值
   * 
   * @param request
   * @param params
   * @param defaultValue
   * @return
   */
  public static String getStr(HttpServletRequest request, String params, String defaultValue) {
    String tmpStr = request.getParameter(params);
    if (tmpStr == null)
      return defaultValue;
    return tmpStr.trim();

  }

  public static String getStrFromHead(HttpServletRequest request, String params, String defaultValue) {
    String tmpStr = request.getHeader(params);
    if (tmpStr == null)
      return defaultValue;
    return tmpStr;
  }

  public static String getTipMsg() {
    String tipMsg = "";
    Calendar cal = Calendar.getInstance();
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    if (hour >= 6 && hour < 8) {
      tipMsg = "早上好";
    } else if (hour >= 8 && hour < 11) {
      tipMsg = "上午好";
    } else if (hour >= 11 && hour < 13) {
      tipMsg = "中午好";
    } else if (hour >= 13 && hour < 18) {
      tipMsg = "下午好";
    } else {
      tipMsg = "晚上好";
    }
    return tipMsg;
  }

  /**
   * 客户端返回JSON字符串
   * 
   * @param response
   * @param object
   * @return
   */
  public static String renderString(HttpServletResponse response, Object object) {
    return renderString(response, JSON.toJSONString(object), "application/json");
  }

  /**
   * response返回字符串
   * 
   * @param response
   * @param string
   * @return
   */
  public static String renderString(HttpServletResponse response, String string, String type) {
    try {
      response.setHeader("Pragma", "no-cache");
      response.setDateHeader("Expires", 0);
      response.addHeader("Cache-Control", "no-cache");// 浏览器和缓存服务器都不应该缓存页面信息
      response.addHeader("Cache-Control", "no-store");// 请求和响应的信息都不应该被存储在对方的磁盘系统中；
      response.addHeader("Cache-Control", "must-revalidate");// /于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；

      response.reset();
      response.setContentType(type);
      // response.setCharacterEncoding("utf-8");
      response.getWriter().print(string);
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}
