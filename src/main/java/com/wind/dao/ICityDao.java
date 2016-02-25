package com.wind.dao;

import java.util.List;

import com.wind.entity.City;

/**
 * ICityDao 接口
 * 
 * @author qianchun
 * @date 2016年2月25日 上午11:21:08
 */
public interface ICityDao extends IBaseDao<City, Long> {
    public List<City> findAll();
}
