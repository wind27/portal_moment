package com.wind.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wind.dao.IAreaDao;
import com.wind.entity.Area;

/**
 * IAreaDao 接口实现
 * 
 * @author qianchun
 * @date 2016年2月25日 上午11:29:07
 */
@Service
public class AreaDao extends BaseDao<Area, Long> implements IAreaDao {

    @Override
    public List<Area> findAll() {
        return (List<Area>) super.findList("findAll", null);
    }

}
