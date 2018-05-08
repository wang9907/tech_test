package com.summer.tech.jws.calculate;

import javax.jws.WebService;

@WebService
public class CalculateImpl implements Calculate {

	public String add(int x, int y) {
		return "hello world";
	}

}
