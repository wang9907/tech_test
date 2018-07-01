package com.summer.tech.spring.di;
import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        //初始化父容器  
        ApplicationContext parentBeanContext =  new ClassPathXmlApplicationContext("spring-parent.xml");  
        //初始化当前容器  
        ApplicationContext beanContext = new ClassPathXmlApplicationContext( new String[] {"spring-beans.xml"}, parentBeanContext);        
        HelloDate hd1 = (HelloDate) beanContext.getBean("hd1");  
        HelloDate hd2 = (HelloDate) beanContext.getBean("hd2");  
        HelloDate hd3 = (HelloDate) beanContext.getBean("hd3");  
        String[] alises = beanContext.getAliases("dateBean");
        System.out.println(Arrays.toString(alises));
        
        hd1.sayHello();  
        hd2.sayHello();  
        hd3.sayHello(); 
        

    }

}