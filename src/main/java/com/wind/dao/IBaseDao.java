package com.wind.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 基础DAO接口
 * 
 * @author ZhangYanchun
 * @param <T> DTO
 * @param <PK> 主键
 */
public interface IBaseDao<T, PK> {

  /**
   * 根据主键删除对象
   * 
   * @param pk
   */
  boolean deleteById(PK pk);

  /**
   * 根据主键查询对象
   * 
   * @param pk
   * @return
   */
  T findById(PK pk);

  /**
   * 保存对象
   * 
   * @param t
   */
  T insert(T t);
  
  /**
   * 批量新增
   * 
   * @author qianchun  @date 2016年2月19日 下午6:47:52
   * @param t
   * @return
   */
  boolean batchInsert(List<T> t);

  /**
   * 查询符合条件的纪录总条数
   * 
   * @param params
   * @return
   */
  boolean update(T t);
  
  
  List<T> findList(String mapperId, Map<String, Object> params);
  
  List<T> findPageList(String mapperId, Map<String, Object> params, PageBounds pager);
}
