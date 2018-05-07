package com.summer.tech.jws;

import javax.jws.WebService;

@WebService
public interface Calculate {

	public int calculate(int x, int y);
}
