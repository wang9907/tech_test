package com.summer.tech.spring.di.methodinject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MethodInjectTest {

	public void testLookup() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-methodinject.xml");
		System.out.println("=======singleton sayHello======");
		HelloApi helloApi1 = context.getBean("helloApi1", HelloApi.class);
		helloApi1.sayHello();
		helloApi1 = context.getBean("helloApi1", HelloApi.class);
		helloApi1.sayHello();
		System.out.println("=======prototype sayHello======");
		HelloApi helloApi2 = context.getBean("helloApi2", HelloApi.class);
		helloApi2.sayHello();
		helloApi2 = context.getBean("helloApi2", HelloApi.class);
		helloApi2.sayHello();

		Printer printer = context.getBean("printer", Printer.class);
        printer.print("我将被替换");
	}
}