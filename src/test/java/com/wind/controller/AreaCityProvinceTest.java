package com.wind.controller;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wind.entity.Area;
import com.wind.entity.City;
import com.wind.entity.Province;
import com.wind.service.IAreaService;
import com.wind.service.ICityService;
import com.wind.service.IProvinceService;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class AreaCityProvinceTest {
    @Resource
    IProvinceService provinceService;
    
    @Resource
    ICityService cityService;
    
    @Resource
    IAreaService AreaService;
    
    @Test
    public void main() {
        List<Province> provinceList = provinceService.findAll();
        String result1 = JSONArray.fromObject(provinceList).toString();
        System.out.println(result1);
        
        List<City> cityList = cityService.findAll();
        String result2 = JSONArray.fromObject(cityList).toString();
        System.out.println(result2);
        
        List<Area> areaList = AreaService.findAll();
        String result3 = JSONArray.fromObject(areaList).toString();
        System.out.println(result3);
    }
}    