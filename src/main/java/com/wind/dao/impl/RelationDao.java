package com.wind.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wind.dao.IRelationDao;
import com.wind.entity.Relation;
import com.wind.entity.Token;

/**
 * IRelationDao 实现
 * 
 * @author qianchun
 * @date 2016年2月19日 下午2:57:55
 */
@Service
public class RelationDao extends BaseDao<Relation, Long> implements IRelationDao {
    public Relation insert(Relation relation) {
        relation = super.insert(relation);
        return relation;
    }

    @Override
    public boolean updateByParams(Map<String, Object> params) {
        return super.update("updateByParams", params);
    }

    @Override
    public List<Relation> findByUid(long uid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", uid);
        return super.findList("findByUid", params);
    }

    @Override
    public List<Relation> findByTargetUid(long targetUid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("targetUid", targetUid);
        return super.findList("findByTargetUid", params);
    }

    @Override
    public Relation findByUidAndTargetUid(long uid, long targetUid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", uid);
        params.put("targetUid", targetUid);
        List<Relation> relationList = super.findList("findByUidAndTargetUid", params);
        if(relationList!=null && relationList.size()==1) {
            return relationList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean batchAdd(List<Relation> relationList) {
        return super.batchInsert(relationList);
    }
}
