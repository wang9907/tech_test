package com.summer.tech.springboot.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "dataSource1")
    @Qualifier("dataSource1")
    public DataSource dataSource1(DBConfigPrimary dbConfigPrimary) {
        return DataSourceBuilder.create().driverClassName(dbConfigPrimary.getDriverClassName()).url(dbConfigPrimary.getUrl())
                .username(dbConfigPrimary.getUsername()).password(dbConfigPrimary.getPassword()).build();
    }

    @Bean(name = "dataSource2")
    @Qualifier("dataSource2")
    public DataSource dataSource2(DBConfigOther dbConfigOther) {
        return DataSourceBuilder.create().driverClassName(dbConfigOther.getDriverClassName()).url(dbConfigOther.getUrl())
                .username(dbConfigOther.getUsername()).password(dbConfigOther.getPassword()).build();
    }
}