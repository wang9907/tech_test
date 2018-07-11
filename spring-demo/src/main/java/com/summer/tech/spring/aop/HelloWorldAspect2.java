package com.summer.tech.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//2、定义切面：
@Aspect
public class HelloWorldAspect2 {

	// 3、定义切入点：
	@Pointcut(value = "execution(* com.summer.tech.spring.aop.*.*(..)) && args(param)", argNames = "param")
	public void beforePointcut(String param) {
	}

	// 4、定义通知：
	@Before(value = "beforePointcut(param)", argNames = "param")
	public void beforeAdvice(String param) {
		System.out.println("HelloWorldAspect2:" + param);
	}

}