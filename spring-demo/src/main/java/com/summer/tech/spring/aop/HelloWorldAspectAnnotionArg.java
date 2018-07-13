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
public class HelloWorldAspectAnnotionArg {

	// 3、定义切入点：
	@Pointcut(value = "execution(* com.summer.tech.spring.aop.*.*(..)) && args(param)", argNames = "param")
	public void pointcut(String param) {
	}

	// 4、定义通知：
	@Before(value = "pointcut(param)")
	public void beforeAdvice(String param) {
		System.out.println("BeforeHelloWorldAspect2:" + param);
	}

	@After(value = "pointcut(param)")
	public void aferAdvice(String param) {
		System.out.println("AfterHelloWorldAspect2:" + param);
	}

	@AfterReturning(value = "pointcut(param)")
	public void aferReturnAdvice(String param) {
		System.out.println("AfterReturnHelloWorldAspect2:" + param);
	}

	@AfterThrowing(value = "pointcut(param)")
	public void aferThrowAdvice(String param) {
		System.out.println("AfterThrowHelloWorldAspect2:" + param);
	}

	@Around(value = "pointcut(param)")
	public Object aroundAdvice(ProceedingJoinPoint pjp, String param)
			throws Throwable {
		System.out.println("===========around before advice");
		Object retVal = pjp.proceed(new Object[] { "replace" });
		System.out.println(pjp.getStaticPart().toLongString());
		System.out.println(Arrays.toString(pjp.getArgs()));
		System.out.println("===========around after advice");
		return retVal;
	}
}