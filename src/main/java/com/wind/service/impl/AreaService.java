package com.wind.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wind.commons.DataSourceSwitch;
import com.wind.dao.IAreaDao;
import com.wind.entity.Area;
import com.wind.service.IAreaService;

/**
 * IAreaService 接口实现
 * 
 * @author qianchun
 * @date 2016年2月25日 下午12:05:12
 */
@Service
public class AreaService implements IAreaService {
    
    public AreaService() {
        DataSourceSwitch.setDataSourceType("userDataSource");
    }
    
    @Resource
    IAreaDao areaDao;

    @Override
    public List<Area> findAll() {
        return areaDao.findAll();
    }

}
