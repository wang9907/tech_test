package com.summer.tech.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class HelloWorldAroundAspect {
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("===========around before advice");
		Object retVal = pjp.proceed(new Object[] { "replace" });
		System.out.println("===========around after advice");
		return retVal;
	}
}