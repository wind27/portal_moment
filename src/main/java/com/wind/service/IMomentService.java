package com.wind.service;


import java.util.List;
import java.util.Map;

import com.wind.entity.Moment;

/**
 * UserService 接口
 * 
 * @author qianchun
 * @date 2016年1月29日 下午4:02:47
 */
public interface IMomentService {
    public Moment findById(long id);
    public Moment add(Moment moment);
    public boolean updateStatus(long id, int status);
    public boolean update(Map<String, Object> params);
    
    public List<Moment> findList(Map<String, Object> params);
}
