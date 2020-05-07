package com.summer.tech.springboot.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HttpAspect {

	@Pointcut("execution(public * com.summer.tech.springboot.controller.PersonController.*(..))")
	public void log() {
		System.out.println("1111");
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		System.out.println("1111");
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		// url
		String url = request.getRequestURL().toString();
		System.out.println("url："+url);
		// ip
		String ip = request.getRemoteAddr();
		System.out.println("ip:"+ip);
		// 类
		String clazz = joinPoint.getSignature().getDeclaringTypeName();
		System.out.println("clazz:"+clazz);
		// 方法
		String method = joinPoint.getSignature().getName();
		System.out.println("method:"+method);
		// 参数
		Object[] arg = joinPoint.getArgs();
		System.out.println("arg:"+arg);
	}

	@After("log()")
	public void doAfter() {
		System.out.println("22222");
	}

	@AfterReturning(returning="object",pointcut="log()")
	public void doAfterReturning(Object object) {
		System.out.println(object);

	}
}
