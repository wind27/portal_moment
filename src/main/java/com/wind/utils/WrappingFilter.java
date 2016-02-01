package com.wind.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class WrappingFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;

        MyHttpResponseWrapper wrapperResponse = new MyHttpResponseWrapper(resp);
        chain.doFilter(request, wrapperResponse);

        String content = wrapperResponse.getContent();
        content = new String(content.getBytes(), "utf-8");
        response.setContentLength(-1);
        response.getWriter().write(content);
    }
}
