package com.wind.utils;

import javax.servlet.http.HttpServletRequest;

import com.wind.entity.vo.RequestParam;

public class ParamsUtil {
    public static RequestParam getFeedDto(HttpServletRequest request) {
        Object feedDtoObject = request.getAttribute("feedDto");
        if (feedDtoObject != null) {
            return (RequestParam) feedDtoObject;
        }
        return null;
    }
}
