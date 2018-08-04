package com.summer.tech.javase7;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleProxies;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javassist.tools.reflect.Sample;

public class ReflectDemo {

	public static void main(String[] args) throws Throwable {
		//multipleBindTo();
		dropArguments();
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
	
	//dropArguments方法的使用示例
	public static void dropArguments()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodType type=MethodType.methodType(String.class, int.class, int.class);
		MethodHandle mhOld=lookup.findVirtual(String.class,"substring",type);
		String value=(String)mhOld.invoke("Hello",2,3);
		MethodHandle mhNew=MethodHandles.dropArguments(mhOld,0,float.class,String.class);
		value=(String)mhNew.invoke(0.5f,"Ignore","Hello",2,3);
		System.out.println(value);
	}
	
	//insertArguments方法的使用示例
	public void insertArguments()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodType type=MethodType.methodType(String.class, String.class);
		MethodHandle mhOld=lookup.findVirtual(String.class,"concat",type);
		String value=(String)mhOld.invoke("Hello","World");
		System.out.println(value);
		MethodHandle mhNew=MethodHandles.insertArguments(mhOld,1,"");
		value=(String)mhNew.invoke("Hello");//值为“Hello”
	}
			
	//filterArguments方法的使用示例
	public void filterArguments()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodType type=MethodType.methodType(int.class, int.class, int.class);
		MethodHandle mhGetLength=lookup.findVirtual(String.class,"length",MethodType.methodType(int.class));
		MethodHandle mhTarget=lookup.findStatic(Math.class,"max",type);
		MethodHandle mhNew=MethodHandles.filterArguments(mhTarget,0,mhGetLength,mhGetLength);
		int value=(int)mhNew.invoke("Hello","NewWorld");//值为9
	}
			
	//　foldArguments方法的使用示例
	public static int targetMethod(int arg1,int arg2,int arg3){
		return arg1;
	}
	public void foldArguments()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodType typeCombiner=MethodType.methodType(int.class, int.class, int.class);
		MethodHandle mhCombiner=lookup.findStatic(Math.class,"max",typeCombiner);
		MethodType typeTarget=MethodType.methodType(int.class, int.class, int.class, int.class);
		MethodHandle mhTarget=lookup.findStatic(ReflectDemo.class,"targetMethod",typeTarget);
		MethodHandle mhResult=MethodHandles.foldArguments(mhTarget, mhCombiner);
		int value=(int)mhResult.invoke(3,4);//输出为4
	}

	//　permuteArguments方法的使用示例
	public void permuteArguments()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodType type=MethodType.methodType(int.class, int.class, int.class);
		MethodHandle mhCompare=lookup.findStatic(Integer.class,"compare",type);
		int value=(int)mhCompare.invoke(3,4);//值为1
		MethodHandle mhNew=MethodHandles.permuteArguments(mhCompare, type,1,0);
		value=(int)mhNew.invoke(3,4);//值为1
		MethodHandle mhDuplicateArgs=MethodHandles.permuteArguments(mhCompare, type,1,1);
		value=(int)mhDuplicateArgs.invoke(3,4);//值为0
	
	}
	
	//　catchException方法的使用示例
	//原始方法句柄和异常处理方法句柄的返回值类型必须是相同的,这是因为当产生异常的时候,异常处理方法句柄的返回值会作为调用的结果;
	//而在两个方法句柄的参数方面,异常处理方法句柄的第一个参数是它所处理的异常类型,其他参数与原始方法句柄的参数相同。
	public int handleException(Exception e, String str){
		System.out.println(e.getMessage());
		return 0;
	}
	public void catchExceptions()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodType typeTarget=MethodType.methodType(int.class, String.class);
		MethodHandle mhParseInt=lookup.findStatic(Integer.class,"parseInt",typeTarget);
		MethodType typeHandler=MethodType.methodType(int.class, Exception.class, String.class);
		MethodHandle mhHandler=lookup.findVirtual(ReflectDemo.class,"handleException",typeHandler).bindTo(this);
		MethodHandle mh=MethodHandles.catchException(mhParseInt, NumberFormatException.class,mhHandler);
		mh.invoke("Hello");
	}
	
	//　guardWithTest方法的使用示例
	public static boolean guardTest(){
		return Math.random()>0.5;
	}
	public void guardWithTest()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodHandle mhTest=lookup.findStatic(ReflectDemo.class,"guardTest",MethodType.methodType(boolean.class));
		MethodType type=MethodType.methodType(int.class, int.class, int.class);
		MethodHandle mhTarget=lookup.findStatic(Math.class,"max",type);
		MethodHandle mhFallback=lookup.findStatic(Math.class,"min",type);
		MethodHandle mh=MethodHandles.guardWithTest(mhTest, mhTarget, mhFallback);
		int value=(int)mh.invoke(3,5);//值随机为3或5
	}
			
	//filterReturnValue方法的使用示例
	public void filterReturnValue()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodHandle mhSubstring=lookup.findVirtual(String.class,"substring",MethodType.methodType(String.class, int.class));
		MethodHandle mhUpperCase=lookup.findVirtual(String.class,"toUpperCase",MethodType.methodType(String.class));
		MethodHandle mh=MethodHandles.filterReturnValue(mhSubstring, mhUpperCase);
		String str=(String)mh.invoke("Hello World",5);//输出WORLD
	}
	
	// 　invoker方法的使用示例
	public void invoker()throws Throwable{
		MethodType typeInvoker=MethodType.methodType(String.class, Object.class, int.class, int.class);
		MethodHandle invoker=MethodHandles.invoker(typeInvoker);
		MethodType typeFind=MethodType.methodType(String.class, int.class, int.class);
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodHandle mh1=lookup.findVirtual(String.class,"substring",typeFind);
		MethodHandle mh2=lookup.findVirtual(ReflectDemo.class,"testMethod",typeFind);
		String result=(String)invoker.invoke(mh1,"Hello",2,3);
		result=(String)invoker.invoke(mh2,this,2,3);
	}

	//invoker和exactInvoker对方法句柄变换的影响
	public void invokerTransform()throws Throwable{
		MethodType typeInvoker=MethodType.methodType(String.class, String.class, int.class, int.class);
		MethodHandle invoker=MethodHandles.exactInvoker(typeInvoker);
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodHandle mhUpperCase=lookup.findVirtual(String.class,"toUpperCase",MethodType.methodType(String.class));
		invoker=MethodHandles.filterReturnValue(invoker, mhUpperCase);
		MethodType typeFind=MethodType.methodType(String.class, int.class, int.class);
		MethodHandle mh1=lookup.findVirtual(String.class,"substring",typeFind);
		String result=(String)invoker.invoke(mh1,"Hello",1,4);//值为“ELL”
	}

	//使用方法句柄实现接口的示例
	public void doSomething(){
		System.out.println("WORK");
	}
	public void useMethodHandleProxy()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodHandle mh=lookup.findVirtual(ReflectDemo.class,"doSomething",MethodType.methodType(void.class));
		mh=mh.bindTo(this);
		Runnable runnable=MethodHandleProxies.asInterfaceInstance(Runnable.class, mh);
		new Thread(runnable).start();
	}

	//交换点的使用示例
	public void useSwitchPoint()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodType type=MethodType.methodType(int.class, int.class, int.class);
		MethodHandle mhMax=lookup.findStatic(Math.class,"max",type);
		MethodHandle mhMin=lookup.findStatic(Math.class,"min",type);
		SwitchPoint sp=new SwitchPoint();
		MethodHandle mhNew=sp.guardWithTest(mhMin, mhMax);
		mhNew.invoke(3,4);//值为3
		SwitchPoint.invalidateAll(new SwitchPoint[]{sp});
		mhNew.invoke(3,4);//值为4
	}
	
	//方法句柄查找时的访问控制权限
	private void privateMethod(){
		System.out.println("PRIVATE");
	}
	public MethodHandle accessControl()throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodHandle mh=lookup.findSpecial(ReflectDemo.class,"privateMethod",MethodType.methodType(void.class),ReflectDemo.class);
		mh=mh.bindTo(this);
	}

	//使用方法句柄实现数组操作的示例
	private static final MethodType typeCallback=MethodType.methodType(Object.class, Object.class, int.class);
	public static void forEach(Object[]array, MethodHandle handle)throws Throwable{
		for(int i=0,len=array.length;i<len;i++){
			handle.invoke(array[i],i);
		}
	}
	public static Object[]map(Object[]array, MethodHandle handle)throws Throwable{
		Object[]result=new Object[array.length];
		for(int i=0,len=array.length;i<len;i++){
			result[i]=handle.invoke(array[i],i);
		}
		return result;
	}
	public static Object reduce(Object[]array, Object initalValue, MethodHandle handle)throws Throwable{
		Object result=initalValue;
		for(int i=0,len=array.length;i<len;i++){
			result=handle.invoke(result, array[i]);
		}
		return result;
	}
	//	使用方法句柄实现的柯里化	
	//。柯里化的含义是对一个方法的参数值进行预先设置之后，得到一个新的方法。比如一个做加法运算的方法，本来有两个参数，通过柯里化
	//把其中一个参数的值设为5之后，得到的新方法就只有一个参数。新方法的运行结果是用5加上这个唯一的参数的值。
	public static MethodHandle curry(MethodHandle handle, int value){
		return MethodHandles.insertArguments(handle,0,value);
	}
	public static int add(int a, int b){
		return a+b;
	}
	public static int add5(int a)throws Throwable{
		MethodHandles.Lookup lookup=MethodHandles.lookup();
		MethodType type=MethodType.methodType(int.class, int.class, int.class);
		MethodHandle mhAdd=lookup.findStatic(ReflectDemo.class,"add",type);
		MethodHandle mh=curry(mhAdd,5);
		return(int)mh.invoke(a);
	}
}
