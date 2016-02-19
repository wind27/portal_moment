package com.wind.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wind.commons.DataSourceSwitch;
import com.wind.dao.IRelationDao;
import com.wind.entity.Relation;
import com.wind.entity.Token;
import com.wind.service.IRelationService;

/**
 * TokenService 实现
 * 
 * @author qianchun
 * @date 2016年2月19日 下午2:59:33
 */
@Service
public class RelationService implements IRelationService {
    
    public RelationService() {
        DataSourceSwitch.setDataSourceType("userDataSource");
    }
    
    @Resource
    IRelationDao relationDao;

    @Override
    public Relation add(Relation relation) {
        return relationDao.insert(relation);
    }

    @Override
    public List<Relation> findByUid(long uid) {
        return relationDao.findByUid(uid);
    }

    @Override
    public List<Relation> findByTargetUid(long targetUid) {
        return relationDao.findByTargetUid(targetUid);
    }

    @Override
    public boolean update(Relation relation) {
        return relationDao.update(relation);
    }

    @Override
    public boolean updateByParams(Map<String, Object> params) {
        return relationDao.updateByParams(params);
    }

    @Override
    public Relation findByUidAndTargetUid(long uid, long targetUid) {
        return relationDao.findByUidAndTargetUid(uid, targetUid);
    }

    @Override
    public boolean batchAdd(List<Relation> relationList) {
        return relationDao.batchAdd(relationList);
    }
}
