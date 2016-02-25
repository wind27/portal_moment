package com.wind.utils;

import java.util.ArrayList;
import java.util.List;

import com.wind.entity.Area;
import com.wind.entity.City;

/**
 * 省市县辅助类
 * 
 * @author qianchun
 * @date 2016年2月25日 下午2:47:34
 */
public final class ProvinceCityAreaUtil {
    /**
     * 根据市的code获取地区集合
     * 
     * @author qianchun  @date 2016年2月25日 下午2:25:51
     * @param cityCode
     * @param areaList
     * @return
     */
    public static List<Area> getAreaByCityCode(String cityCode, List<Area> areaList) {
        List<Area> areas = new ArrayList<Area>();
        if(areaList!=null && areaList.size()>0) {
            for(int i=0; i<areaList.size(); i++) {
                Area area = areaList.get(i);
                if(area!=null && area.getCityCode().equals(cityCode)) {
                    areas.add(area);
                }
            }
        }
        return areas;
    }
    
    /**
     * 根据省的code获取市集合
     * 
     * @author qianchun  @date 2016年2月25日 下午2:25:19
     * @param provinceCode
     * @param cityList
     * @return
     */
    public static List<City> getCityByProvinceCode(String provinceCode, List<City> cityList) {
        List<City> citys = new ArrayList<City>();
        if(cityList!=null && cityList.size()>0) {
            for(int i=0; i<cityList.size(); i++) {
                City city = cityList.get(i);
                if(city!=null && city.getProvinceCode().equals(provinceCode)) {
                    citys.add(city);
                }
            }
        }
        return citys;
    }
}