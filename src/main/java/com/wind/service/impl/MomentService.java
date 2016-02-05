package com.wind.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wind.commons.DataSourceSwitch;
import com.wind.dao.IMomentDao;
import com.wind.entity.Moment;
import com.wind.service.IMomentService;

/**
 * UserService 实现
 * 
 * @author qianchun
 * @date 2016年1月29日 下午4:02:23
 */
@Service
public class MomentService implements IMomentService {
    
    public MomentService() {
        DataSourceSwitch.setDataSourceType("userDataSource");
    }
    
    @Resource
    IMomentDao momentDao;

    @Override
    public Moment findById(long id) {
        return momentDao.findById(id);
    }

    @Override
    public Moment create(Moment moment) {
        return momentDao.insert(moment);
    }

    public boolean updateStatus(long id, int status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("status", status);
        return momentDao.update(params);
    }
    
    public boolean update(Map<String, Object> params) {
        return momentDao.update(params);
    }

    public List<Moment> findList(Map<String, Object> params) {
        return momentDao.findList(params);
    }
    
    public List<Moment> findPageList(Map<String, Object> params, PageBounds pager) {
        return momentDao.findPageList(params, pager);
    }
}
