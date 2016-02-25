package com.wind.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wind.dao.ICityDao;
import com.wind.entity.City;

/**
 * ICityDao 接口实现
 * 
 * @author qianchun
 * @date 2016年2月25日 上午11:21:51
 */
@Service
public class CityDao extends BaseDao<City, Long> implements ICityDao {

    @Override
    public List<City> findAll() {
        return (List<City>) super.findList("findAll", null);
    }

}
