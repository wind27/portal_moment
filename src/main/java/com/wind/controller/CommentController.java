package com.wind.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CommentController {
    
    @RequestMapping(value="/city", method = RequestMethod.GET)
    public Object getCityByProvinceCode(HttpServletRequest request) {
        String provinceCode = request.getParameter("provinceCode");
        return null;
    }
    
    @RequestMapping(value="/area", method = RequestMethod.GET)
    public Object getAreaByCityCode(HttpServletRequest request) {
        String cityCode = request.getParameter("cityCode");
        return null;
    }
    
}
