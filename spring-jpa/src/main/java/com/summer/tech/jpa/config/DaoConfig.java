package com.summer.tech.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@Import({ DataSourceConfig.class })
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactory", transactionManagerRef="jpaTransactionManager",
        basePackages = "com.summer.tech.jpa.repository")
public class DaoConfig {

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        return adapter;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter());
        emfb.setPackagesToScan("com.summer.tech.jpa.entity");
        return emfb;
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager(DataSource dataSource) {
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
        return jtm;
    }

}
