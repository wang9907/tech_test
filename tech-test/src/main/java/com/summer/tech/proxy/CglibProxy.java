/**
  * @Title: CglibProxy.java
  * @Package com.summer.tech.proxy
  * @Description:
  * @author 000807
  * @date 2019年9月4日
  * @version V1.0
  */

package com.summer.tech.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @ClassName: CglibProxy
 * @Description:
 * @author 000807
 * @date 2019年9月4日 下午1:05:43
 *
 */

public class CglibProxy implements MethodInterceptor {
	private Enhancer enhancer = new Enhancer();

	public Object getProxy(Class<?> clazz) {
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("方法调用开始");
		Object result = proxy.invokeSuper(obj, args);
		System.out.println("方法调用结束");
		return result;
	}

	public static void main(String[] args) {
		CglibProxy cglibProxy = new CglibProxy();
		ArrayList proxy = (ArrayList) cglibProxy.getProxy(ArrayList.class);
		System.out.println(proxy.size());
	}
}
