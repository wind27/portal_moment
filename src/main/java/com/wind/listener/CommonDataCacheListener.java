package com.wind.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.wind.entity.Area;
import com.wind.entity.City;
import com.wind.entity.Province;
import com.wind.service.IAreaService;
import com.wind.service.ICityService;
import com.wind.service.IProvinceService;
import com.wind.utils.ProvinceCityAreaUtil;

import net.sf.json.JSONObject;

/**
 * 常用数据缓存
 * 
 * @author qianchun
 * @date 2016年2月25日 下午12:13:05
 */
public class CommonDataCacheListener implements InitializingBean, ServletContextAware{

    @Override
    public void afterPropertiesSet() throws Exception {
        
    }
    
    @Override
    public void setServletContext(ServletContext servletContext) {
        List<Province> provinceList = provinceService.findAll();
        List<City> cityList = cityService.findAll();
        List<Area> areaList = areaService.findAll();
        
        Map<String, Map<String, List<Area>>> provinceMap = new HashMap<String, Map<String, List<Area>>>();
        if(provinceList!=null) {
            for(int i=0; i<provinceList.size(); i++) {
                Province province = provinceList.get(i);
                if(province!=null) {
                    List<City> citys = ProvinceCityAreaUtil.getCityByProvinceCode(province.getCode(), cityList);
                    Map<String, List<Area>> cityMap = new HashMap<String, List<Area>>();
                    if(citys!=null && citys.size()>0) {
                        for(int k=0; k<citys.size(); k++) {
                            City city = citys.get(k);
                            if(city!=null) {
                                List<Area> areas = ProvinceCityAreaUtil.getAreaByCityCode(city.getCode(), areaList);
                                cityMap.put(city.getCode(), areas);
                            }
                        }
                    }
                    provinceMap.put(province.getCode(), cityMap);
                }
            }
        }
        
        String tmp = JSONObject.fromObject(provinceMap).toString();
        System.out.println(tmp);
    }
    //-------------------------------------------
    private IAreaService areaService;
    private ICityService cityService;
    private IProvinceService provinceService;
    
    public IAreaService getAreaService() {
        return areaService;
    }
    public void setAreaService(IAreaService areaService) {
        this.areaService = areaService;
    }
    public ICityService getCityService() {
        return cityService;
    }
    public void setCityService(ICityService cityService) {
        this.cityService = cityService;
    }
    public IProvinceService getProvinceService() {
        return provinceService;
    }
    public void setProvinceService(IProvinceService provinceService) {
        this.provinceService = provinceService;
    }
}

