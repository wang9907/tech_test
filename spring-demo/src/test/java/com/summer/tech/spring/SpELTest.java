package com.summer.tech.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.*;

public class SpELTest {
	@Test
	public void helloWorld() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser
				.parseExpression("('Hello' + ' World').concat(#end)");
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("end", "!");
		Assert.assertEquals("Hello World!", expression.getValue(context));
	}

	@Test
	public void testClassTypeExpression() {
		ExpressionParser parser = new SpelExpressionParser();
		// java.lang包类访问
		Class<String> result1 = parser.parseExpression("T(String)").getValue(
				Class.class);
		Assert.assertEquals(String.class, result1);
		// 其他包类访问
		String expression2 = "T(com.summer.tech.spring.spel.SpELTest)";
		Class<String> result2 = parser.parseExpression(expression2).getValue(
				Class.class);
		Assert.assertEquals(SpELTest.class, result2);
		// 类静态字段访问
		int result3 = parser.parseExpression("T(Integer).MAX_VALUE").getValue(
				int.class);
		Assert.assertEquals(Integer.MAX_VALUE, result3);
		// 类静态方法调用
		int result4 = parser.parseExpression("T(Integer).parseInt('1')")
				.getValue(int.class);
		Assert.assertEquals(1, result4);
	}

	@Test
	public void testConstructorExpression() {
		ExpressionParser parser = new SpelExpressionParser();
		String result1 = parser.parseExpression("new String('haha')").getValue(
				String.class);
		Assert.assertEquals("haha", result1);
		Date result2 = parser.parseExpression("new java.util.Date()").getValue(
				Date.class);
		Assert.assertNotNull(result2);
	}

	@Test
	public void testVariableExpression() {
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("variable", "haha");
		context.setVariable("variable", "haha");
		String result1 = parser.parseExpression("#variable").getValue(context,
				String.class);
		Assert.assertEquals("haha", result1);

		context = new StandardEvaluationContext("haha");
		String result2 = parser.parseExpression("#root").getValue(context,
				String.class);
		Assert.assertEquals("haha", result2);
		String result3 = parser.parseExpression("#this").getValue(context,
				String.class);
		Assert.assertEquals("haha", result3);
	}

	@Test
	public void testFunctionExpression() throws SecurityException,
			NoSuchMethodException {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		Method parseInt = Integer.class.getDeclaredMethod("parseInt",
				String.class);
		context.registerFunction("parseInt", parseInt);
		context.setVariable("parseInt2", parseInt);
		String expression1 = "#parseInt('3') == #parseInt2('3')";
		boolean result1 = parser.parseExpression(expression1).getValue(context,
				boolean.class);
		Assert.assertEquals(true, result1);
	}

	@Test
	public void testAssignExpression() {
		ExpressionParser parser = new SpelExpressionParser();
		// 1.给root对象赋值
		EvaluationContext context = new StandardEvaluationContext("aaaa");
		String result1 = parser.parseExpression("#root='aaaaa'").getValue(
				context, String.class);
		Assert.assertEquals("aaaaa", result1);
		String result2 = parser.parseExpression("#this='aaaa'").getValue(
				context, String.class);
		Assert.assertEquals("aaaa", result2);

		// 2.给自定义变量赋值
		context.setVariable("#variable", "variable");
		String result3 = parser.parseExpression("#variable=#root").getValue(
				context, String.class);
		Assert.assertEquals("aaaa", result3);
	}

	public void testObjectNatigation() {
		ExpressionParser parser = new SpelExpressionParser();
		// 1.访问root对象属性
		Date date = new Date();
		StandardEvaluationContext context = new StandardEvaluationContext(date);
		int result1 = parser.parseExpression("Year").getValue(context,
				int.class);
		Assert.assertEquals(date.getYear(), result1);
		int result2 = parser.parseExpression("year").getValue(context,
				int.class);
		Assert.assertEquals(date.getYear(), result2);

		// 2.安全访问
		context.setRootObject(null);
		Object result3 = parser.parseExpression("#root?.year").getValue(
				context, Object.class);
		Assert.assertEquals(null, result3);

		// 3.给root对象属性赋值
		context.setRootObject(date);
		int result4 = parser.parseExpression("Year = 4").getValue(context,
				int.class);
		Assert.assertEquals(4, result4);
		parser.parseExpression("Year").setValue(context, 5);
		int result5 = parser.parseExpression("Year").getValue(context,
				int.class);
		Assert.assertEquals(5, result5);

		int result6 = parser.parseExpression("getYear()").getValue(context,
				int.class);
		Assert.assertEquals(date.getYear(), result6);
	}

	@Test
	public void testBeanExpression() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
		ctx.refresh();
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver(new BeanFactoryResolver(ctx));
		Properties result1 = parser.parseExpression("@systemProperties")
				.getValue(context, Properties.class);
		Assert.assertEquals(System.getProperties(), result1);
	}

	public void testCollection() {
		ExpressionParser parser = new SpelExpressionParser();
		// 将返回不可修改的空List
		List<Integer> result2 = parser.parseExpression("{}").getValue(
				List.class);
		// 对于字面量列表也将返回不可修改的List
		List<Integer> result1 = parser.parseExpression("{1,2,3}").getValue(
				List.class);
		Assert.assertEquals(new Integer(1), result1.get(0));
		try {
			result1.set(0, 2);
			// 不可能执行到这，对于字面量列表不可修改
			Assert.fail();
		} catch (Exception e) {
		}

		// 对于列表中只要有一个不是字面量表达式，将只返回原始List，
		// 不会进行不可修改处理
		String expression3 = "{{1+2,2+4},{3,4+4}}";
		List<List<Integer>> result3 = parser.parseExpression(expression3)
				.getValue(List.class);
		result3.get(0).set(0, 1);
		Assert.assertEquals(2, result3.size());

		// 声明一个大小为2的一维数组并初始化
		int[] result4 = parser.parseExpression("new int[2]{1,2}").getValue(
				int[].class);

		// 定义一维数组但不初始化
		int[] result5 = parser.parseExpression("new int[1]").getValue(
				int[].class);

		// 定义多维数组但不初始化
		int[][][] result6 = parser.parseExpression("new int[1][2][3]")
				.getValue(int[][][].class);

		// 错误的定义多维数组，多维数组不能初始化
		String expression4 = "new int[1][2][3]{{1}{2}{3}}";
		try {
			int[][][] result7 = parser.parseExpression(expression4).getValue(
					int[][][].class);
			Assert.fail();
		} catch (Exception e) {
		}

		// SpEL内联List访问
		int result8 = parser.parseExpression("{1,2,3}[0]").getValue(int.class);
		// 即list.get(0)
		Assert.assertEquals(1, result1);

		// SpEL目前支持所有集合类型的访问
		Collection<Integer> collection = new HashSet<Integer>();
		collection.add(1);
		collection.add(2);
		EvaluationContext context2 = new StandardEvaluationContext();
		context2.setVariable("collection", collection);
		int result9 = parser.parseExpression("#collection[1]").getValue(
				context2, int.class);
		// 对于任何集合类型通过Iterator来定位元素
		Assert.assertEquals(2, result2);

		// SpEL对Map字典元素访问的支持
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("a", 1);
		EvaluationContext context3 = new StandardEvaluationContext();
		context3.setVariable("map", map);
		int result10 = parser.parseExpression("#map['a']").getValue(context3,
				int.class);
		Assert.assertEquals(1, result3);

		// 1.修改数组元素值
		int[] array = new int[] { 1, 2 };
		EvaluationContext context1 = new StandardEvaluationContext();
		context1.setVariable("array", array);
		int result11 = parser.parseExpression("#array[1] = 3").getValue(
				context1, int.class);
		Assert.assertEquals(3, result1);

		// 2.修改集合值
		Collection<Integer> collection1 = new ArrayList<Integer>();
		collection1.add(1);
		collection1.add(2);
		EvaluationContext context5 = new StandardEvaluationContext();
		context5.setVariable("collection", collection1);
		int result12 = parser.parseExpression("#collection[1] = 3").getValue(
				context2, int.class);
		Assert.assertEquals(3, result2);
		parser.parseExpression("#collection[1]").setValue(context2, 4);
		result12 = parser.parseExpression("#collection[1]").getValue(context5,
				int.class);
		Assert.assertEquals(4, result2);

		// 3.修改map元素值
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		map1.put("a", 1);
		EvaluationContext context6 = new StandardEvaluationContext();
		context6.setVariable("map", map);
		int result13 = parser.parseExpression("#map['a'] = 2").getValue(
				context6, int.class);
		Assert.assertEquals(2, result13);
	}
}