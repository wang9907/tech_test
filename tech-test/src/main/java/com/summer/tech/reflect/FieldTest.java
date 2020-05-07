package com.summer.tech.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class FieldTest {

	public static void main(String[] args) throws Exception {
		//1.加载Class对象
		Class clazz = Class.forName("com.summer.tech.reflect.HeroPlus");

		//2.获取所有公有构造方法
		System.out.println("**********************所有公有成员，包括从父类继承下来的*********************************");
		Field[] fields = clazz.getFields();
		for(Field f : fields){
			System.out.println(f);
		}

		System.out.println("************所有的成员(包括：私有、受保护、默认、公有)***************");
		fields = clazz.getDeclaredFields();
		for(Field f: fields){
			System.out.println(f);
		}

		HeroPlus h =new HeroPlus();
		//使用传统方式修改name的值为garen
		h.name = "garen";
		//获取类HeroPlus的名字叫做name的字段
		Field f1= clazz.getDeclaredField("name");
		//修改这个字段的值
		f1.set(h, "teemo");
		//打印被修改后的值
		System.out.println(h.name);

	}
	

}
