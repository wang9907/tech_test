package com.summer.tech.jws;

import javax.jws.WebService;

@WebService
public class CalculateImpl implements Calculate {

	public int calculate(int x, int y) {
		return x + y;
	}

}
