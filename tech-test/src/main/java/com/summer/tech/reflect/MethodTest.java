package com.summer.tech.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MethodTest {

    public static void main(String[] args) throws Exception {

        //1.加载Class对象
        Class clazz = Class.forName("com.summer.tech.reflect.HeroPlus");

        //2.获取所有公有构造方法
        System.out.println("**********************所有公有成员，包括从父类继承下来的*********************************");
        Method[] methods = clazz.getMethods();
        for(Method m : methods){
            System.out.println(m);
        }

        System.out.println("************所有的成员(包括：私有、受保护、默认、公有)***************");
        methods = clazz.getDeclaredMethods();
        for(Method m: methods){
            System.out.println(m);
        }

        HeroPlus h = new HeroPlus();
        try {
            // 获取这个名字叫做setName，参数类型是String的方法
            Method m = h.getClass().getMethod("setName", String.class);
            // 对h对象，调用这个方法
            m.invoke(h, "盖伦");
            // 使用传统的方式，调用getName方法
            System.out.println(h.getName());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
