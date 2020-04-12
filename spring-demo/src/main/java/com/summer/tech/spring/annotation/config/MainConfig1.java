package com.summer.tech.spring.annotation.config;

import com.summer.tech.spring.annotation.entity.Person;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(value="com.summer.tech.spring.annotation", excludeFilters={
		@Filter(type=FilterType.CUSTOM, classes={JamesTypeFilter.class})
}, useDefaultFilters=false)
public class MainConfig1 {
	//给容器中注册一个bean, 类型为返回值的类型,

	/*
	 * 懒加载: 主要针对单实例bean:默认在容器启动的时候创建对象
	 * 懒加载:容器启动时候不创建对象, 仅当第一次使用(获取)bean的时候才创建被初始化
	 */
	@Lazy()
	/*
	 * prototype:多实例: IOC容器启动的时候,IOC容器启动并不会去调用方法创建对象, 而是每次获取的时候才会调用方法创建对象
	 * singleton:单实例(默认):IOC容器启动的时候会调用方法创建对象并放到IOC容器中,以后每次获取的就是直接从容器中拿(大Map.get)的同一个bean
	 * request: 主要针对web应用, 递交一次请求创建一个实例
	 * session:同一个session创建一个实例
	 */
	//@Scope("singleton")
	//@Bean(name={"person"},initMethod = "init",destroyMethod = "destroy")
	@Bean
	public Person person01(){
		return new Person("james",20);
	}
}
