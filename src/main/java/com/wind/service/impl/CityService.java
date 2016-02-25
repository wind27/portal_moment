package com.wind.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wind.commons.DataSourceSwitch;
import com.wind.dao.ICityDao;
import com.wind.entity.City;
import com.wind.service.ICityService;

/**
 * ICityService 接口实现
 * 
 * @author qianchun
 * @date 2016年2月25日 上午11:24:35
 */
@Service
public class CityService implements ICityService {
    
    public CityService() {
        DataSourceSwitch.setDataSourceType("userDataSource");
    }
    
    @Resource
    ICityDao cityDao;

    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }

}
