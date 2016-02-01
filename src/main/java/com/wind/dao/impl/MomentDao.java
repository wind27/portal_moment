package com.wind.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wind.dao.IMomentDao;
import com.wind.entity.Moment;

@Service
public class MomentDao extends BaseDao<Moment, Long> implements IMomentDao {
    public Moment insert(Moment moment) {
        moment = super.insert(moment);
        return moment;
    }

    public Moment findById(long id) {
        Moment moment = super.findById(id);
        return moment;
    }

    @Override
    public boolean update(Map<String, Object> params) {
        return super.update("updateByParams", params);
    }
    
    public List<Moment> findList(Map<String, Object> params) {
        return (List<Moment>) super.findList("findByParam", params);
    }
}
