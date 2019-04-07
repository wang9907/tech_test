package com.summer.tech.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LogBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("===============before advice start==============");
        System.out.println("method: " + method);
        System.out.println("args: " + args);
        System.out.println("target: " + target);
        System.out.println("===============before advice end================");
    }

}