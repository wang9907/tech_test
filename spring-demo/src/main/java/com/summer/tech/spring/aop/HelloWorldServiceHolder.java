package com.summer.tech.spring.aop;

import javax.annotation.Resource;

public class HelloWorldServiceHolder {

	@Resource
	IHelloWorldService helloService;

	public IHelloWorldService getHelloService() {
		return helloService;
	}

	public void setHelloService(IHelloWorldService helloService) {
		this.helloService = helloService;
	}

}