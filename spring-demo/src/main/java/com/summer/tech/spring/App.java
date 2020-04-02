package com.summer.tech.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath.xml");
	}
}
