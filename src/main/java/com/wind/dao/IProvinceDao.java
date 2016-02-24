package com.wind.dao;

import java.util.List;

import com.wind.entity.Province;

/**
 * IProvinceDao 接口
 * 
 * @author qianchun
 * @date 2016年2月24日 下午7:04:30
 */
public interface IProvinceDao extends IBaseDao<Province, Long> {
    public List<Province> findAll();
}
