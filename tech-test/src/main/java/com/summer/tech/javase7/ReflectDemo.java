package com.summer.tech.javase7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 反射API设置对象属性
	public static void invokeSetter(Object obj, String field, Object value)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String methodName = "set" + field.substring(0, 1).toUpperCase()
				+ field.substring(1);
		Class<?> clazz = obj.getClass();
		Method method = clazz.getMethod(methodName, value.getClass());
		method.invoke(obj, value);
	}

}
