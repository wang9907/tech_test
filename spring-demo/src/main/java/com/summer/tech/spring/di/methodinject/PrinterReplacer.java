package com.summer.tech.spring.di.methodinject;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class PrinterReplacer implements MethodReplacer {

	@Override
	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		System.out.println("Print Replacer" + args[0]);
		System.out.println(obj.getClass().getName());
		System.out.println(method.getName());
		// 注意此处不能再通过反射调用了,否则会产生循环调用，知道内存溢出
		// method.invoke(obj, new String[]{"hehe"});
		return null;
	}
}