package com.wind.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wind.dao.IProvinceDao;
import com.wind.entity.Province;

/**
 * IProvinceDao 实现
 * 
 * @author qianchun
 * @date 2016年2月24日 下午7:04:58
 */
@Service
public class ProvinceDao extends BaseDao<Province, Long> implements IProvinceDao {

    @Override
    public List<Province> findAll() {
        return (List<Province>) super.findList("findAll", null);
    }

}
