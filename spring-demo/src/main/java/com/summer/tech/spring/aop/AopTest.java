package com.summer.tech.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ctx =  new ClassPathXmlApplicationContext("spring-aop.xml");
        IHelloWorldService helloworldService =  ctx.getBean("helloService", IHelloWorldService.class);
        helloworldService.sayHello("ddd");

    }

}