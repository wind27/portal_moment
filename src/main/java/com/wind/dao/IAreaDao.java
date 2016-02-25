package com.wind.dao;

import java.util.List;

import com.wind.entity.Area;

/**
 * IAreaDao 接口
 * 
 * @author qianchun
 * @date 2016年2月25日 上午11:27:52
 */
public interface IAreaDao extends IBaseDao<Area, Long> {
    public List<Area> findAll();
}
