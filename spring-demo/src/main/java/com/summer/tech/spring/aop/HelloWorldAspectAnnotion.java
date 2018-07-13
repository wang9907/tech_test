package com.summer.tech.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//2、定义切面：
@Aspect
public class HelloWorldAspectAnnotion {

	// 3、定义切入点：
	@Pointcut(value = "execution(* com.summer.tech.spring.aop.*.*(..))")
	public void pointcut() {
	}

	// 4、定义通知：
	@Before(value = "pointcut()")
	public void beforeAdvice() {
		System.out.println("BeforeHelloWorldAspect2:");
	}

	@After(value = "pointcut()")
	public void aferAdvice() {
		System.out.println("AfterHelloWorldAspect2:");
	}

	@AfterReturning(value = "pointcut()")
	public void aferReturnAdvice() {
		System.out.println("AfterReturnHelloWorldAspect2:");
	}

	@AfterThrowing(value = "pointcut()")
	public void aferThrowAdvice() {
		System.out.println("AfterThrowHelloWorldAspect2:");
	}

	@Around(value = "pointcut()")
	public Object aroundAdvice(ProceedingJoinPoint pjp)
			throws Throwable {
		System.out.println("===========around before advice");
		Object retVal = pjp.proceed(new Object[] { "replace" });
		System.out.println(pjp.getStaticPart().toLongString());
		System.out.println(Arrays.toString(pjp.getArgs()));
		System.out.println("===========around after advice");
		return retVal;
	}
}