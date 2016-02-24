package com.wind.controller;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wind.entity.Province;
import com.wind.service.IProvinceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class AreaCityProvinceTest {
    @Resource
    IProvinceService provinceService;
    @Test
    public void main() {
        List<Province> provinceList = provinceService.findAll();
        String result1 = JSONArray.fromObject(provinceList).toString();
        System.out.println(result1);
    }
}    