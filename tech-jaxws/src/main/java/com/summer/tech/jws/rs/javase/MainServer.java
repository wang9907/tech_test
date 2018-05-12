package com.summer.tech.jws.rs.javase;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class MainServer {

	// ·þÎñÆ÷Â·¾¶
	public static final String BASE_URI = "http://localhost:8080/myapp/";
	
	public static HttpServer startServer(){
		final ResourceConfig rc = new ResourceConfig().packages("com.summer.tech.jws.rs.javase.resource");
		HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
		return httpServer;
	}
}
	
