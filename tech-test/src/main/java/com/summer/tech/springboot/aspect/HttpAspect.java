package com.summer.tech.springboot.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class HttpAspect {

	@Before("execution(public * com.summer.tech.springboot.controller.PersonController.*(..))")
	public void log(){
		System.out.println("1111");
	}
	
	
}
