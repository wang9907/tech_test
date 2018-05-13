package com.summer.tech.jws.rs.javase;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.glassfish.jersey.servlet.ServletContainer;

@WebServlet(initParams = @WebInitParam(name = "jersey.config.server.provider.packages", value = "com.summer.tech.jws.rs.javase.resource"), urlPatterns = "/webapp", loadOnStartup = 1)
public class AirServlet extends ServletContainer {

}
