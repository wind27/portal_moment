package com.wind.commons;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

/**
 * 通过拦截<code>StatementHandler</code>的<code>prepare</code>方法，重写sql语句实现物理分页。 老规矩，签名里要拦截的类型只能是接口。
 * 
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PaginationInterceptor implements Interceptor {

  private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
  private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
    MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");

    String pageSqlId = configuration.getVariables().getProperty("pageSqlId");
    if (null == pageSqlId || "".equals(pageSqlId)) {
      System.out.println("Property pageSqlId is not setted,use default '.*Page$' ");
    }
    MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

    if (!mappedStatement.getId().matches(pageSqlId)) {
      return invocation.proceed();
    }

    RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");

    if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
      return invocation.proceed();
    }

    // Separating agent object chain (due to the target class may be more
    // interceptors, thereby forming a multiple agent, after two cycles of
    // the following can be separated from the original target class)
    while (metaStatementHandler.hasGetter("h")) {
      Object object = metaStatementHandler.getValue("h");
      metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }
    // The final separation of a proxy object target class
    while (metaStatementHandler.hasGetter("target")) {
      Object object = metaStatementHandler.getValue("target");
      metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }

    BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");

    String sql = boundSql.getSql();
    StringBuffer sbSql = new StringBuffer();

    // Rewrite SQL
    // Configuration configuration = (Configuration) metaStatementHandler
    // .getValue("delegate.configuration");

    sbSql.append(sql).append(" LIMIT ").append(rowBounds.getOffset()).append(", ").append(rowBounds.getLimit());
    metaStatementHandler.setValue("delegate.boundSql.sql", sbSql.toString());

    // The physical page, do not need mybatis paging, so the two parameter
    // resetting the following
    metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
    metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

    // The executive power to an interceptor
    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    // When the target is a StatementHandler type, before packing the target
    // class, or directly to the target itself, reduce the number of target
    // is agent
    if (target instanceof StatementHandler) {
      return Plugin.wrap(target, this);
    } else {
      return target;
    }
  }

  @Override
  public void setProperties(Properties properties) {

  }

}