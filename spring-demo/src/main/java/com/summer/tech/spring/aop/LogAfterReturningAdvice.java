package com.summer.tech.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class LogAfterReturningAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("==============调用成功返回，返回值是：" + returnValue);
        System.out.println("Method: " + method);
    }

}