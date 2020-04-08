package com.summer.tech.spring;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.summer.tech.spring.di.HelloBean;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("spring-parent.xml");
		beanFactory.getBean("helloBean", HelloBean.class);

		// System.out.println(beanFactory.getBeanNamesForType(CustomScopeConfigurer.class)[0]);
		// System.out.println(beanFactory.getBeanNamesForType(CustomScopeConfigurer.class).length);

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {

		}

		beanFactory.close();
	}
}
