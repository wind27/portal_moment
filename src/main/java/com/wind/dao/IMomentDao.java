package com.wind.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wind.entity.Moment;

public interface IMomentDao extends IBaseDao<Moment, Long> {
    public Moment insert(Moment moment);
    
    public Moment findById(long id);
    
    public boolean update(Moment moment);
    
    public boolean update(Map<String, Object> params);
    
    public List<Moment> findList(Map<String, Object> params);
    
    public List<Moment> findPageList(Map<String, Object> params, PageBounds pager);
}
