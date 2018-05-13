package com.summer.tech.jws.rs.javase;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.summer.tech.jws.rs.javase.resource.MyResource;

@ApplicationPath("/webapi/*")
public class AirApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(MyResource.class);
		return classes;
	}

	
}
