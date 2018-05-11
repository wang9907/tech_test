package com.summer.tech.jws.calculate;

import javax.xml.ws.Endpoint;

public class Server {

	public static void main(String[] args) {
		Endpoint endpoint = Endpoint.publish("http://localhost:8088/calculate", new CalculateImpl());
		System.out.println(endpoint);
	}
}
