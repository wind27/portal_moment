package com.wind.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wind.dao.IBaseDao;

/**
 * 基础DAO类 ，（提供基本的方法，由于mybatis需要有配置文件mapper ID对应，所以在mapper文件中还是要有相应的insert deleteById findById,
 * findALL配置）
 * 
 * @param <T>  DTO对象
 * @param <PK> 主键类型
 * @author qianchun
 * @date 2016年1月29日 下午3:42:17
 */
public abstract class BaseDao<T, PK> implements IBaseDao<T, PK> {
    @Resource
    protected SqlSession sqlSession;
    protected String nameSpace;

    {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            @SuppressWarnings("unchecked")
            Class<T> clazz = (Class<T>) pType.getActualTypeArguments()[0];
            nameSpace = clazz.getName();
        } else {
            nameSpace = null;
        }
        if (nameSpace == null) {
            throw new RuntimeException("no name space specialed for mybaits");
        }
    }
    //---------------------------- 插入 -----------------------------------
    @Override
    public T insert(T t) {
        sqlSession.insert(String.format("%s.insert", nameSpace), t);
        return t;
    }
    
    @Override
    public boolean batchInsert(List<T> t) {
        int result = sqlSession.insert(String.format("%s.batchInsert", nameSpace), t);
        if(result > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //---------------------------- 删除 -----------------------------------
    @Override
    public boolean deleteById(PK pk) {
        int result = sqlSession.delete(String.format("%s.deleteById", nameSpace), pk);
        return result >0 ? true : false;
    }
    
    //---------------------------- 修改 -----------------------------------
    /**
     * 执行按照对象的更新操作
     *
     * @param mapperId
     * @param t
     */
    
    /**
     * 
     */
    public boolean update(T t) {
        int result = sqlSession.update(String.format("%s.%s", nameSpace, "update"), t);
        return result > 0 ? true : false;
    }
    
    /**
     * 更改
     * 
     * @author qianchun  @date 2016年2月24日 下午5:59:54
     * @param mapperId
     * @param params
     * @return
     */
    public boolean update(String mapperId, Map<String, Object> params) {
        int result = sqlSession.update(String.format("%s.%s", nameSpace, mapperId), params);
        return result > 0 ? true : false;
    }
    
    //---------------------------- 查询 -----------------------------------
    /**
     * 根据id查询
     * 
     * @author qianchun  @date 2016年2月24日 下午5:59:54
     * @param mapperId
     * @param params
     * @return
     */
    @Override
    public T findById(PK pk) {
        return sqlSession.selectOne(String.format("%s.findById", nameSpace), pk);
    }
    
    /**
     * 根据id查询
     * 
     * @author qianchun  @date 2016年2月24日 下午5:59:54
     * @param mapperId
     * @param params
     * @return
     */
    @Override
    public T findOne(String mapperId, Map<String, Object> params) {
        return sqlSession.selectOne(String.format("%s.%s", nameSpace, mapperId), params);
    }

    /**
     * 查询list
     * 
     * @author qianchun  @date 2016年2月1日 下午3:06:18
     * @param mapperId
     * @param params
     * @return
     */
    public List<T> findList(String mapperId, Map<String, Object> params) {
        return sqlSession.selectList(String.format("%s.%s", nameSpace, mapperId), params);
    }
    
    /**
     * 分页查询
     * 
     * @author qianchun  @date 2016年2月24日 下午5:59:54
     * @param mapperId
     * @param params
     * @return
     */
    public List<T> findPageList(String mapperId, Map<String, Object> params, PageBounds pager) {
        return sqlSession.selectList(String.format("%s.%s", nameSpace, mapperId), params, pager);
    }
}
