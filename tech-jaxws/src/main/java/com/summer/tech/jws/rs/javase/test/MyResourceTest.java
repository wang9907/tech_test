package com.summer.tech.jws.rs.javase.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.summer.tech.jws.rs.javase.MainServer;

public class MyResourceTest {

	private HttpServer server;
	private WebTarget target;
	
	@Before
	public void setUp(){
		server = MainServer.startServer();
		Client c = ClientBuilder.newClient();
		target = c.target(MainServer.BASE_URI);
	}
	
	@After
	public void tearDown(){
		server.shutdown();
	}
	
	@Test
	public void testGetIt(){
		String responseMsg = target.path("myresource").request().get(String.class);
		System.out.println(responseMsg);
	}
}
