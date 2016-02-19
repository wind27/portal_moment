package com.wind.service;


import java.util.List;
import java.util.Map;

import com.wind.entity.Relation;

/**
 * IRelationService 接口
 * 
 * @author qianchun
 * @date 2016年2月19日 下午2:59:10
 */
public interface IRelationService {
    public Relation add(Relation relation);
    public boolean batchAdd(List<Relation> relationList);
    
    public List<Relation> findByUid(long uid);
    public List<Relation> findByTargetUid(long targetUid);
    public Relation findByUidAndTargetUid(long uid, long targetUid);

    public boolean update(Relation relation);
    public boolean updateByParams(Map<String, Object> params);
}
