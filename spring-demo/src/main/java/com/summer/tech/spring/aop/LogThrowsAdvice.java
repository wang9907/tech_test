package com.summer.tech.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class LogThrowsAdvice implements ThrowsAdvice {

    /**
     * 处理IllegalArgumentException
     * @param e
     */
    public void afterThrowing(IllegalArgumentException e) {
        System.out.println("=====================方法调用异常，抛出了IllegalArgumentException");
    }

    /**
     * 处理NumberFormatException
     * @param e
     */
    public void afterThrowing(NumberFormatException e) {
        System.out.println("=====================方法调用异常，抛出了NumberFormatException");
    }

    /**
     * 处理其它所有的异常
     * @param method
     * @param args
     * @param target
     * @param e
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.println("=====================方法调用异常了，" + e);
        System.out.println("Method: " + method);
        System.out.println("Args: " + args);
        System.out.println("Target: " + target);
    }
}