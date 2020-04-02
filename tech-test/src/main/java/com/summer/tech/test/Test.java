package com.summer.tech.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {

	public static void main(String[] args) {
		// System.out.println(System.getProperty("java.io.tmpdir"));
		// List<Object> list = new LinkedList<>();
		// int i=0;
		// while(true) {
		// i++;
		// if(i%10000==00) {
		// System.out.println("i:"+i);
		// }
		// list.add(new Object());
		// }
		// String[] arr = new String[10000000];
		System.out.println(System.getProperty("user.home"));
		BigDecimal dd = new BigDecimal("1992.2255");
		System.out.println(dd.setScale(2, RoundingMode.HALF_UP));
	}
}
