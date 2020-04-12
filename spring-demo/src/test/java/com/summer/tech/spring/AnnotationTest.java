package com.summer.tech.spring;

import com.summer.tech.spring.annotation.aop.Calculator;
import com.summer.tech.spring.annotation.config.*;
import com.summer.tech.spring.annotation.dao.OrderDao;
import com.summer.tech.spring.annotation.entity.Bird;
import com.summer.tech.spring.annotation.entity.Dog;
import com.summer.tech.spring.annotation.entity.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationTest {

    @Test
    public void test01(){
//        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig1.class);
//        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig2.class);
//        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig3.class);
//        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig4.class);
//        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig5.class);
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig6.class);
        String[] definitionNames = app.getBeanDefinitionNames();
        for(String name:definitionNames){
            System.out.println(name);
        }
//        Dog dog = app.getBean("dog",Dog.class);
//        Person person = app.getBean("com.summer.tech.spring.annotation.entity.Person",Person.class);
//        System.out.println(person.getDog().getName());
//        Calculator calculator = app.getBean(Calculator.class);
//        System.out.println(calculator.div(10,4));
        OrderDao orderDao = app.getBean("orderDao",OrderDao.class);
        orderDao.insert();
    }
}
