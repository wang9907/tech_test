package com.summer.tech.spring.aop;

public class HelloWorldService implements IHelloWorldService {

	public void sayHello() {
		System.out.println("============Hello World!");
	}

	@Override
	public void sayHello(String param) {
		System.out.println("============" + param);
	}
}