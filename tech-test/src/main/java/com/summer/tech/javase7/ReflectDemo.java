package com.summer.tech.javase7;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
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

	// MethodType 类中的methodType方法使用
	public void generateMethodTypes() {
		// String.length()
		MethodType mt1 = MethodType.methodType(int.class);
		// String.concat(String str)
		MethodType mt2 = MethodType.methodType(String.class, String.class);
		// String.getChar(int srcBegin,int srcEnd,char[] dst,int dstBegin)
		MethodType mt3 = MethodType.methodType(Void.class, int.class,
				int.class, char[].class, int.class);
		// String.startsWith(String prefix)
		MethodType mt4 = MethodType.methodType(boolean.class, mt2);
	}

	public void generateMethodTypesFromDescriptor() {
		ClassLoader cl = this.getClass().getClassLoader();
		String descriptor = "(Ljava/lang/String;)Ljava/lang/String;";
		MethodType mt1 = MethodType.fromMethodDescriptorString(descriptor, cl);
	}

	// 使用invokeExact方法调用方法句柄
	public void invokeExact() throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodType type = MethodType.methodType(String.class, int.class,
				int.class);
		MethodHandle mh = lookup.findVirtual(String.class, "substring", type);
		String str = (String) mh.invokeExact("Hello World", 1, 3);
		System.out.println(str);
	}
}
