package com.summer.tech.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@EnableAutoConfiguration(exclude = {JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@EntityScan("com.summer.tech.springboot.jpa.entity")
//@EnableJpaRepositories(basePackages ="com.summer.tech.springboot")
public class DatasourceTest {

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void query(){
// 获取配置的数据源
        DataSource dataSource = applicationContext.getBean("dataSource1",DataSource.class);
        String[] beanNames = applicationContext.getBeanNamesForType(DataSource.class);
        System.out.println(beanNames);
        // 查看配置数据源信息
        System.out.println(dataSource);
        System.out.println(dataSource.getClass().getName());
        System.out.println(dataSourceProperties);

        //执行SQL,输出查到的数据
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<?> resultList = jdbcTemplate.queryForList("select * from person");
        System.out.println("===>>>>>>>>>>>" + resultList);
    }
}
