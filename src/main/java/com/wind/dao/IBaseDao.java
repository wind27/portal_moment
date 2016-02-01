package com.wind.dao;

import java.util.List;
import java.util.Map;

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
   * 查询符合条件的纪录总条数
   * 
   * @param params
   * @return
   */
  boolean update(T t);
  
  
  List<T> findList(String mapperId, Map<String, Object> params);
}
