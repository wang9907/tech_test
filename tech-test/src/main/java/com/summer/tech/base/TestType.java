package com.summer.tech.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Map;

public class TestType {
	Map<String, String> map;

	public static void main(String[] args) throws Exception {

	}

	public void parameterTypeTest() throws Exception {
		Field f = TestType.class.getDeclaredField("map");
		System.out.println(f.getGenericType()); // java.util.Map<java.lang.String,
												// java.lang.String>
		System.out.println(f.getGenericType() instanceof ParameterizedType); // true
		ParameterizedType pType = (ParameterizedType) f.getGenericType();
		System.out.println(pType.getRawType()); // interface java.util.Map
		for (Type type : pType.getActualTypeArguments()) {
			System.out.println(type); // 打印两遍: class java.lang.String
		}
		System.out.println(pType.getOwnerType()); // null
	}

	public void typeVariableTest() throws Exception {
		// 获取字段的类型
		Field fk = TestTypeVariable.class.getDeclaredField("key");
		Field fv = TestTypeVariable.class.getDeclaredField("value");
		System.out.println(fk.getGenericType() instanceof TypeVariable);
		System.out.println(fv.getGenericType() instanceof TypeVariable);
		TypeVariable keyType = (TypeVariable) fk.getGenericType();
		TypeVariable valueType = (TypeVariable) fv.getGenericType();
		// getName 方法
		System.out.println(keyType.getName()); // K
		System.out.println(valueType.getName()); // V
		// getGenericDeclaration 方法
		System.out.println(keyType.getGenericDeclaration()); // class
																// com.test.TestType
		System.out.println(valueType.getGenericDeclaration()); // class
																// com.test.TestType
		// getBounds 方法
		System.out.println("K 的上界:"); // 有两个
		for (Type type : keyType.getBounds()) { // interface
												// java.lang.Comparable
			System.out.println(type); // interface java.io.Serializable
		}
		System.out.println("V 的上界:"); // 没明确声明上界的, 默认上界是 Object
		for (Type type : valueType.getBounds()) { // class java.lang.Object
			System.out.println(type);
		}
	}

	public void genericArrayTest() throws Exception {
		Method method = Test.class.getDeclaredMethods()[0];
		// public void
		// com.test.Test.show(java.util.List[],java.lang.Object[],java.util.List,java.lang.String[],int[])
		System.out.println(method);
		Type[] types = method.getGenericParameterTypes(); // 这是 Method 中的方法
		for (Type type : types) {
			System.out.println(type instanceof GenericArrayType);
		}

	}

	public void wildcardTest() throws Exception {
		Field fieldA = TestType.class.getDeclaredField("a");
        Field fieldB = TestType.class.getDeclaredField("b");
        // 先拿到范型类型
        System.out.println(fieldA.getGenericType() instanceof ParameterizedType);
        System.out.println(fieldB.getGenericType() instanceof ParameterizedType);
        ParameterizedType pTypeA = (ParameterizedType) fieldA.getGenericType();
        ParameterizedType pTypeB = (ParameterizedType) fieldB.getGenericType();
        // 再从范型里拿到通配符类型
        System.out.println(pTypeA.getActualTypeArguments()[0] instanceof WildcardType);
        System.out.println(pTypeB.getActualTypeArguments()[0] instanceof WildcardType);
        WildcardType wTypeA = (WildcardType) pTypeA.getActualTypeArguments()[0];
        WildcardType wTypeB = (WildcardType) pTypeB.getActualTypeArguments()[0];
        // 方法测试
        System.out.println(wTypeA.getUpperBounds()[0]);   // class java.lang.Number
        System.out.println(wTypeB.getLowerBounds()[0]);   // class java.lang.String
        // 看看通配符类型到底是什么, 打印结果为: ? extends java.lang.Number
        System.out.println(wTypeA);

	}
}

class TestTypeVariable<K extends Comparable & Serializable, V> {
	K key;
	V value;
}

class Test<T> {
	public void show(List<String>[] pTypeArray, T[] vTypeArray,
			List<String> list, String[] strings, int[] ints) {
	}
}

class WilcardType {
    private List<? extends Number> a;  // // a没有下界, 取下界会抛出ArrayIndexOutOfBoundsException
    private List<? super String> b;
}
