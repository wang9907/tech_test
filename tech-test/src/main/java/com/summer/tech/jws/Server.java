package com.summer.tech.jws;

import javax.xml.ws.Endpoint;

public class Server {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8088/calculate", new CalculateImpl());
	}
}
