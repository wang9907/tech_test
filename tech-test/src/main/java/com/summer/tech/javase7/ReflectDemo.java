package com.summer.tech.javase7;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javassist.tools.reflect.Sample;

public class ReflectDemo {

	public static void main(String[] args) throws Throwable {
		multipleBindTo();

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
		MethodType mt3 = MethodType.methodType(Void.class, int.class, int.class,
				char[].class, int.class);
		// String.startsWith(String prefix)
		MethodType mt4 = MethodType.methodType(boolean.class, mt2);
	}

	public void generateMethodTypesFromDescriptor() {
		ClassLoader cl = this.getClass().getClassLoader();
		String descriptor = "(Ljava/lang/String;)Ljava/lang/String;";
		MethodType mt1 = MethodType.fromMethodDescriptorString(descriptor, cl);
	}

	public void changeMethodType() {
		// (int,int) String
		MethodType mt = MethodType.methodType(String.class, int.class,
				int.class);
		// (int,int,float) String
		mt = mt.appendParameterTypes(float.class);
		// (int,double,long,int,float) String
		mt = mt.insertParameterTypes(1, double.class, long.class);
		// (int,double,int,float) String
		mt = mt.dropParameterTypes(2, 3);
		// (int,double,String,float) String
		mt = mt.changeParameterType(2, String.class);
		// (int,double,String,float) void
		mt = mt.changeReturnType(void.class);
	}

	public void wrapAndGeneric() {
		// (int,double) Integer
		MethodType mt = MethodType.methodType(Integer.class, int.class,
				double.class);
		// (Integer,Double)Integer
		MethodType wrapped = mt.wrap();
		// (int,double)int
		MethodType unwrapped = mt.unwrap();
		// (Object,Object) Object
		MethodType generic = mt.generic();
		// (int,double) Object
		MethodType erased = mt.erase();

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

	public void normalMethod(String arg1, int arg2, int[] arg3) {

	}

	// 数组参数变为可变参数
	public void asVarargsCollector() throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mh = lookup.findVirtual(ReflectDemo.class, "normalMethod",
				MethodType.methodType(void.class, String.class, int.class,
						int[].class));
		mh = mh.asVarargsCollector(int[].class);
		mh.invoke(this, "Hello", 2, 3, 4, 5);
	}

	// 数组参数变为可变参数
	public void asCollector() throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mh = lookup.findVirtual(ReflectDemo.class, "normalMethod",
				MethodType.methodType(void.class, String.class, int.class,
						int[].class));
		mh = mh.asCollector(int[].class, 2);
		mh.invoke(this, "Hello", 2, 3, 4);
	}

	public void toBeSpreaded(String arg1, int arg2, int arg3, int arg4) {
	}

	// 把可变参数转换为数组
	public void asSpreader() throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mh = lookup.findVirtual(ReflectDemo.class, "toBeSpreaded",
				MethodType.methodType(void.class, String.class, int.class,
						int.class, int.class));
		mh = mh.asSpreader(int[].class, 3);
		mh.invoke(this, "Hello", new int[] { 3, 4, 5 });
	}

	public void varargsMethod(String arg1, int... args) {
	}

	// 最后一个方法asFixedArity是把参数长度可变的方法转换成参数长度不变的方法。经过这样的转换之后,最后一个长度可变的参数实际上就变成了对应的数组类型。在
	public void asFixedArity() throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mh = lookup.findVirtual(ReflectDemo.class, "varargsMethod",
				MethodType.methodType(void.class, String.class, int[].class));
		mh = mh.asFixedArity();
		mh.invoke(this, "Hello", new int[] { 2, 4 });
	}

	// 绑定对象,调用方法时不需要指定对象
	public static void bindTo() throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mh = lookup.findVirtual(String.class, "length",
				MethodType.methodType(int.class));
		int len = (int) mh.invoke("Hello");// 值为5
		mh = mh.bindTo("HelloWorld");
		len = (int) mh.invoke();// 值为11
	}

	// 多次参数绑定
	public static void multipleBindTo() throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mh = lookup.findVirtual(String.class, "indexOf",
				MethodType.methodType(int.class, String.class, int.class));
		mh = mh.bindTo("Hello").bindTo("l");
		System.out.println(mh.invoke(2));// 值为2
	}

	// 查找构造方法、一般方法和静态方法的方法句柄的示例
	public void lookupMethod()
			throws NoSuchMethodException, IllegalAccessException {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		// 构造方法
		lookup.findConstructor(String.class,
				MethodType.methodType(void.class, byte[].class));
		// String.substring
		lookup.findVirtual(String.class, "substring",
				MethodType.methodType(String.class, int.class, int.class));
		// String.format
		lookup.findStatic(String.class, "format", MethodType
				.methodType(String.class, String.class, Object[].class));
	}

	// 查找类中特殊方法的方法句柄
	public MethodHandle lookupSpecial()
			throws NoSuchMethodException, IllegalAccessException, Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle mh = lookup.findSpecial(ReflectDemo.class,
				"private Method", MethodType.methodType(void.class),
				ReflectDemo.class);
		return mh;
	}

	// 查找类中的静态域和一般域对应的获取和设置的方法句柄的示例
	public void lookupFieldAccessor()
			throws NoSuchFieldException, IllegalAccessException {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		lookup.findGetter(Sample.class, "name", String.class);
		lookup.findSetter(Sample.class, "name", String.class);
		lookup.findStaticGetter(Sample.class, "value", int.class);
		lookup.findStaticSetter(Sample.class, "value", int.class);
	}

	// 通过反射API获取方法句柄的示例
	public void unreflect() throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		Constructor constructor = String.class.getConstructor(byte[].class);
		lookup.unreflectConstructor(constructor);
		Method method = String.class.getMethod("substring", int.class,
				int.class);
		lookup.unreflect(method);
		Method privateMethod = ReflectDemo.class
				.getDeclaredMethod("privateMe thod");
		lookup.unreflectSpecial(privateMethod, ReflectDemo.class);
		Field field = ReflectDemo.class.getField("name");
		lookup.unreflectGetter(field);
		lookup.unreflectSetter(field);
	}

	// 获取和设置数组中元素的值的方法句柄的使用示例
	public void arrayHandles() throws Throwable {
		int[] array = new int[] { 1, 2, 3, 4, 5 };
		MethodHandle setter = MethodHandles.arrayElementSetter(int[].class);
		setter.invoke(array, 3, 6);
		MethodHandle getter = MethodHandles.arrayElementGetter(int[].class);
		int value = (int) getter.invoke(array, 3);// 值为6
	}

	// MethodHandles类的identity方法的使用示例
	public void identity() throws Throwable {
		MethodHandle mh = MethodHandles.identity(String.class);
		String value = (String) mh.invoke("Hello");// 值为"Hello"
	}
	// MethodHandles类的constant方法的使用示例

	public void constant() throws Throwable {
		MethodHandle mh = MethodHandles.constant(String.class, "Hello");
		String value = (String) mh.invoke();// 值为"Hello"}
	}
}
