/**
  * @Title: NaiveWaiter.java
  * @Package com.summer.tech.proxy
  * @Description:
  * @author 000807
  * @date 2019年9月4日
  * @version V1.0
  */

package com.summer.tech.spring.aop;

/**
 * @ClassName: NaiveWaiter
 * @Description:
 * @author 000807
 * @date 2019年9月4日 下午1:56:26
 *
 */

public class NaiveWaiter implements Waiter {

	@Override
	public void greetTo(String name) {
		System.out.println("greet to " + name + "...");
	}

	@Override
	public void serveTo(String name) {
		System.out.println("serve to " + name + "...");
	}

}
