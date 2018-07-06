package com.summer.tech.spring.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DependsOn
{
    public static void main( String[] args ) throws Exception
    {
        ClassPathXmlApplicationContext context =  new ClassPathXmlApplicationContext("dependson.xml");  
        //一点要注册销毁回调，否则我们定义的销毁方法不执行  
        //context.registerShutdownHook();  
        DependentBean dependentBean =  context.getBean("dependentBean", DependentBean.class);  
        dependentBean.write("aaa");  
        
    }
}