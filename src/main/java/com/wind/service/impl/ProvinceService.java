package com.wind.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wind.commons.DataSourceSwitch;
import com.wind.dao.IProvinceDao;
import com.wind.entity.Province;
import com.wind.service.IProvinceService;

/**
 * IProvinceService 接口实现
 * 
 * @author qianchun
 * @date 2016年2月25日 上午11:24:59
 */
@Service
public class ProvinceService implements IProvinceService {
    
    public ProvinceService() {
        DataSourceSwitch.setDataSourceType("userDataSource");
    }
    
    @Resource
    IProvinceDao provinceDao;

    @Override
    public List<Province> findAll() {
        return provinceDao.findAll();
    }

}
