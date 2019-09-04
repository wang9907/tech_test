/**
  * @Title: BeforeAdviceTest.java
  * @Package com.summer.tech.spring
  * @Description:
  * @author 000807
  * @date 2019年9月4日
  * @version V1.0
  */

package com.summer.tech.spring;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import com.summer.tech.spring.aop.LogBeforeAdvice;
import com.summer.tech.spring.aop.NaiveWaiter;
import com.summer.tech.spring.aop.Waiter;

/**
 * @ClassName: BeforeAdviceTest
 * @Description:
 * @author 000807
 * @date 2019年9月4日 下午2:00:24
 *
 */

public class BeforeAdviceTest {

	@Test
	public void before() {
		Waiter target = new NaiveWaiter();
		BeforeAdvice advice = new LogBeforeAdvice();

		// spring提供代理工厂
		ProxyFactory pf = new ProxyFactory();
		// 设置代理目标
		pf.setTarget(target);
		// 为代理目标添加增强
		pf.addAdvice(advice);
		// 生成代理实例
		Waiter proxy = (Waiter) pf.getProxy();
		proxy.greetTo("wang");
		proxy.serveTo("liu");

	}
}
