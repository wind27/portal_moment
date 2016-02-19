package com.wind.dao;

import java.util.List;
import java.util.Map;

import com.wind.entity.Relation;

/**
 * IRelationDao 接口
 * 
 * @author qianchun
 * @date 2016年2月19日 下午2:58:20
 */
public interface IRelationDao extends IBaseDao<Relation, Long> {
    public Relation insert(Relation relation);
    
    public List<Relation> findByUid(long uid);
    public List<Relation> findByTargetUid(long targetUid);

    public boolean update(Relation relation);
    public boolean updateByParams(Map<String, Object> params);

    public Relation findByUidAndTargetUid(long uid, long targetUid);

    public boolean batchAdd(List<Relation> relationList);
}
