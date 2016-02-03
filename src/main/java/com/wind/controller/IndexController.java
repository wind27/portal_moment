package com.wind.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView visitAllFeedForPage(HttpServletRequest request) {
        return new ModelAndView("moment/list");
    }
    
}
