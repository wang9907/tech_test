package com.summer.tech.jws.rs.javase;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class AirResourceConfig extends ResourceConfig {

	public AirResourceConfig() {
		packages("com.summer.tech.jws.rs.javase.resource");
		property(ServerProperties.WADL_FEATURE_DISABLE, true);
	}

	
}
