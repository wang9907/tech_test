package com.summer.tech.mybatis.plugin;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Properties;

@Intercepts(value = {@Signature(type = StatementHandler.class,method = "prepare",
        args = {Connection.class})})
public class PaginationInterceptor implements Interceptor {

    private static final Logger LOGGER = Logger.getLogger(PaginationInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        LOGGER.info("插件执行");
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        MetaObject metaobject = MetaObject.forObject(statementHandler,new DefaultObjectFactory(),new DefaultObjectWrapperFactory(),new DefaultReflectorFactory());
        RowBounds rowBounds = (RowBounds) metaobject.getValue("delegate.rowBounds");
        String originalSql = (String)metaobject.getValue("delegate.boundSql.sql");
        LOGGER.info("originalSql:"+originalSql);
        if(rowBounds ==null || rowBounds==RowBounds.DEFAULT){
            return invocation.proceed();
        }
        metaobject.setValue("delegate.rowBounds.offset",RowBounds.NO_ROW_OFFSET);
        metaobject.setValue("delegate.rowBounds.offset",RowBounds.NO_ROW_LIMIT);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        LOGGER.info("测试插件");
    }
}
