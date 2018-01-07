package com.summer.tech.apache.common.beanutils;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.summer.tech.ezmorph.Student;

public class BeanutilsTest {
	
	public void test1() throws Exception {
		// 先演示一下不用工具时的做法
		// 1.生成对象
		Student s = new Student();

		// 2.通过set方法赋值
		s.setName("VN");
		s.setAge(19);
		// 用以上这种方法来给对象的属性赋值实在是太麻烦了，下面我们用BeanUtils来进行赋值

		// 1.得到javaBean的一个字节码对象
		Class clazz = Class.forName("com.summer.tech.ezmorph.Student");

		// 2.生成该字节码的一个对象
		Object obj = clazz.newInstance();

		// 4.注册一个日期格式转换器
		ConvertUtils.register(new DateLocaleConverter(), Date.class);

		// 3.使用工具对该对象进行赋值
		// 注意： 对于基本数据类型，beanutils工具进行自动类型转换。把String自动转成Integer,Double,Float
		BeanUtils.setProperty(obj, "name", "VN");
		BeanUtils.setProperty(obj, "age", "19");
		// 如果要使用特殊的日期类型，则String->Date 不能自动转换,这时候就要注册一个转换器
		BeanUtils.setProperty(obj, "birthday", "1996-06-06");

		System.out.println(obj);
	}

	// 约定前提： 请求中的参数名称 需要和javabean的属性名称保持一致！！！！
	public static <T> T requestToBean(HttpServletRequest request, Class<T> clazz) {
		// 创建javaBean对象
		Object obj = null;
		try {
			obj = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		// 得到请求中的每个参数
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			// 获得参数名
			String name = enu.nextElement();
			// 获得参数值
			String value = request.getParameter(name);
			// 然后把参数拷贝到javaBean对象中
			try {
				BeanUtils.setProperty(obj, name, value);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return (T) obj;
	}

	public void test2() throws Exception {
		// 1.生成对象
		Student s1 = new Student();
		Student s2 = new Student();

		// 2.通过set方法赋值
		s1.setName("VN");
		s1.setAge(19);// 基本数据类型可以为null，null也能拷贝

		// 需求：把s1的属性值拷贝到S2中，注意参数的顺序
		BeanUtils.copyProperties(s2, s1);

		System.out.println(s1);
		System.out.println(s2);
	}

	public void test3() throws Exception {
		// 1.生成对象
		Map<String, Object> map = new HashMap<String, Object>();

		// 2.给一些参数
		map.put("id", 2);
		map.put("name", "EZ");
		map.put("age", 22);
		map.put("classID", 3);
		map.put("birthday", new Date());

		// 需求：把map的属性值拷贝到S中
		Student s = new Student();
		BeanUtils.copyProperties(s, map);

		System.out.println(s);
	}

}
