package com.summer.tech.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-aop.xml");
		IHelloWorldService helloworldService = ctx.getBean("helloService", IHelloWorldService.class);
		Class clazz = helloworldService.getClass();
		System.out.println(clazz.getName());
		System.out.println(clazz.getSuperclass().getName());

		String hello = helloworldService.toHello("hhh");
		System.out.println(hello);

	}

}