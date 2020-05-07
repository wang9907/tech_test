package com.summer.tech.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationTest {
    public static void main(String[] args) throws Exception {
        //1.加载Class对象
        Class clazz = Class.forName("com.summer.tech.reflect.HeroPlus");

        //2.获取所有公有构造方法
        System.out.println("**********************所有公有成员，包括从父类继承下来的*********************************");
        Field[] fields = clazz.getFields();
        for(Field f : fields){
            System.out.println(f);
            for (Annotation annotation:f.getAnnotations()){
                System.out.println(annotation);
            }
            for (Annotation annotation:f.getDeclaredAnnotations()){
                System.out.println(annotation);
            }
        }

        System.out.println("************所有的成员(包括：私有、受保护、默认、公有)***************");
        fields = clazz.getDeclaredFields();
        for(Field f: fields){
            System.out.println(f);
            for (Annotation annotation:f.getAnnotations()){
                System.out.println(annotation);
            }
            for (Annotation annotation:f.getDeclaredAnnotations()){
                System.out.println(annotation);
            }
        }

        Method[] methods = clazz.getMethods();
        for(Method m : methods){
            System.out.println(m);
            for (Annotation annotation:m.getAnnotations()){
                System.out.println(annotation);
            }
        }
    }
}
