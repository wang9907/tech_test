package com.summer.tech.spring.aop;

public class HelloWorldService implements IHelloWorldService {

	@Override
	public void sayHello() {
		System.out.println("============Hello World!");
	}

	@Override
	public void sayHello(String param) throws Exception{
		System.out.println("============" + param);
		if (true) {
			throw new Exception("ddd");
		}
	}

	@Override
	public String toHello(String param) throws Exception {
		return "你好" + param;
	}
	
	public void sayHello1(String param) {
		System.out.println("============"+param);
	}
}