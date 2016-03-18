package com.wind.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/article")
public class ArticlePageController {
	/**
	 * 跳转添加动态页面
	 * 
	 * @author qianchun  @date 2016年3月14日 下午2:36:52
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView addPage(HttpServletRequest request) {
        return new ModelAndView("/article/add");
    }
    /**
     * 跳转所有动态页面
     * 
     * @author qianchun  @date 2016年3月14日 下午2:37:06
     * @param request
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ModelAndView allPage(HttpServletRequest request) {
        return new ModelAndView("/article/all");
    }
    /**
     * 跳转我的动态页面
     * 
     * @author qianchun  @date 2016年3月14日 下午2:37:29
     * @param request
     * @return
     */
    @RequestMapping(value = "/my",method = RequestMethod.GET)
    public ModelAndView indexPage(HttpServletRequest request) {
        return new ModelAndView("/article/my");
    }
    
    /**
     * 跳转动态详情页面
     * 
     * @author qianchun  @date 2016年3月14日 下午2:37:44
     * @param request
     * @return
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ModelAndView detailPage(HttpServletRequest request) {
    	return new ModelAndView("/article/detail");
    }
}
