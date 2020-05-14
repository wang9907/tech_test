package com.summer.tech.springboot.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactoryPrimary",
		transactionManagerRef="transactionManagerPrimary",
		// Repository定义所在的包
		basePackages={"com.summer.tech.springboot.jpa.respository.person"})
public class PrimaryDataSourceConfig {

	@Autowired
	@Qualifier("dataSource1")
	private DataSource dataSource;
	
	@Primary
	@Bean(name="entityManagerPrimary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder){
		return entityManagerFactoryBean(builder).getObject().createEntityManager();
	}
	
	@Primary
	@Bean(name="entityManagerFactoryPrimary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder){
		return builder
				.dataSource(dataSource)
				.properties(getProperties())
				.packages("com.summer.tech.springboot.jpa.entity.person") // Entity定义所在的包
				.persistenceUnit("primaryPersistentUnit")
				.build();
	}
	
	public Map<String, String> getProperties(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("format_sql", "true");
		map.put("max_fetch_depth", "1");
		return map;
	}
	
	
	@Primary
	@Bean(name="transactionManagerPrimary")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
		return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
	}
}