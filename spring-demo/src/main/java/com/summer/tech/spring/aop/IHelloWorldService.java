package com.summer.tech.spring.aop;

public interface IHelloWorldService {
	public void sayHello();

	public void sayHello(String param) throws Exception;

	public String toHello(String param) throws Exception;
}