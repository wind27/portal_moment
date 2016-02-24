package com.wind.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * IBaseDao 接口
 * 
 * @author qianchun
 * @date 2016年2月24日 下午5:57:13
 */
public interface IBaseDao<T, PK> {
    T insert(T t);
    boolean batchInsert(List<T> t);

    boolean deleteById(PK pk);

    boolean update(T t);

    T findById(PK pk);
    T findOne(String mapperId, Map<String, Object> params);
    List<T> findList(String mapperId, Map<String, Object> params);
    List<T> findPageList(String mapperId, Map<String, Object> params, PageBounds pager);
}
