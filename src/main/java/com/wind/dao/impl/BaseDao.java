package com.wind.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wind.commons.SysContext;
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

    @Override
    public boolean deleteById(PK pk) {
        int result = sqlSession.delete(String.format("%s.deleteById", nameSpace), pk);
        return result >0 ? true : false;
    }

    @Override
    public T findById(PK pk) {
        return sqlSession.selectOne(String.format("%s.findById", nameSpace), pk);
    }

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
    
    /**
     * 执行按照对象的更新操作
     *
     * @param mapperId
     * @param t
     */
    public boolean update(T t) {
        int result = sqlSession.update(String.format("%s.%s", nameSpace, "update"), t);
        return result > 0 ? true : false;
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
    
    public List<T> findPageList(String mapperId, Map<String, Object> params, PageBounds pager) {
        return sqlSession.selectList(String.format("%s.%s", nameSpace, mapperId), params, pager);
    }
    
    /**
     * 根据条件更新
     * 
     * @author qianchun  @date 2016年2月1日 下午3:07:57
     * @param mapperId
     * @param params
     * @return
     */
    public boolean update(String mapperId, Map<String, Object> params) {
        int result = sqlSession.update(String.format("%s.%s", nameSpace, mapperId), params);
        return result > 0 ? true : false;
    }
    
    /**
     * 查询记录，并分页
     *
     * @param mapperId   mapper文件对应的id
     * @param params     查询参数
     * @param sortString 排序方式
     * @return
     */
    protected final List<T> findLimit(String mapperId, Map<String, Object> params, String sortString) {
        int page = SysContext.getPage(); // 页号
        int pageSize = SysContext.getPageSize(); // 每页数据条数
        SysContext.removePage();
        SysContext.removePageSize();
        int startPoint = (page - 1) * pageSize;
        params.put("startPoint", startPoint);
        params.put("pageSize", pageSize);
        params.put("sortString", sortString);
        return sqlSession.selectList(String.format("%s.%s", nameSpace, mapperId), params);
    }
}
