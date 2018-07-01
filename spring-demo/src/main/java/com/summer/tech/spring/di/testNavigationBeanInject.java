package com.summer.tech.spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testNavigationBeanInject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-navigation.xml");
		NavigationA navigationA = context.getBean("a", NavigationA.class);
		navigationA.getNavigationB().getNavigationC().sayNavigation();
		navigationA.getNavigationB().getList().get(0).sayNavigation();
		navigationA.getNavigationB().getMap().get("key").sayNavigation();
		navigationA.getNavigationB().getArray()[0].sayNavigation();
		((NavigationC) navigationA.getNavigationB().getProperties().get("1"))
				.sayNavigation();

	}

}