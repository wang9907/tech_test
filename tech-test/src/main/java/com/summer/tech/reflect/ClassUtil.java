package com.summer.tech.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {

    public void printClassInfo(Class clazz) throws NoSuchMethodException {
        Class superclass = clazz.getSuperclass();
        Class[] interfaces = clazz.getInterfaces();

        int modifiers = clazz.getModifiers();

        String name = clazz.getCanonicalName();

        Annotation[] annotations = clazz.getAnnotations();

        //所有"公有的"构造方法
        Constructor[] constructors = clazz.getConstructors();
        //获取所有的构造方法(包括私有、受保护、默认、公有)
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        // 获取单个"公有的"构造方法
        Constructor constructor = clazz.getConstructor(String.class);
        //获取"某个构造方法"可以是私有的，或受保护、默认、公有；
        clazz.getDeclaredConstructor(String.class);


        Field[] declaredFields = clazz.getDeclaredFields();

        Method[] declaredMethods = clazz.getDeclaredMethods();

    }


}
