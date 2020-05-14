package com.summer.tech.springboot.mybatis.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.summer.tech.springboot.mybatis.mapper.orders", sqlSessionFactoryRef = "test2SqlSessionFactory")
public class DataSource2Config {

//    @Bean(name = "test2DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.mybatis2")
//    public DataSource testDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean("test2DataSource")
    public DataSource testDataSource(DBConfig2 dbConfig2) {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(dbConfig2.getUrl());
        mysqlXADataSource.setUser(dbConfig2.getUsername());
        mysqlXADataSource.setPassword(dbConfig2.getPassword());

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("test12DataSource");
        return  atomikosDataSourceBean;
    }


    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/orders/*.xml"));
        return bean.getObject();
    }

//    分布式事务用不到
//    @Bean(name = "test2TransactionManager")
//    public DataSourceTransactionManager testTransactionManager(@Qualifier("test2DataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

//    //分布式事务管理器
//    @Bean(name = "atomikosTransactionManager2")
//    public TransactionManager atomikosTransactionManager() throws Throwable {
//        UserTransactionManager userTransactionManager = new UserTransactionManager();
//        userTransactionManager.setForceShutdown(false);
//        return userTransactionManager;
//    }
//
//    @Bean(name = "userTransaction2")
//    public UserTransaction userTransaction() throws Throwable {
//        UserTransactionImp userTransactionImp = new UserTransactionImp();
//        userTransactionImp.setTransactionTimeout(10000);
//        return userTransactionImp;
//    }
//
//    @Bean(name = "txManager2")
//    @DependsOn({ "userTransaction2", "atomikosTransactionManager2" })
//    public PlatformTransactionManager transactionManager() throws Throwable {
//        UserTransaction userTransaction = userTransaction();
//        TransactionManager atomikosTransactionManager = atomikosTransactionManager();
//        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
//    }

    @Bean(name = "test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
