package com.summer.tech.jws.rs.javase.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.summer.tech.jws.rs.javase.MainServer;
import com.summer.tech.jws.rs.javase.entity.Device;

public class DeviceResourceTest {

	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() {
		server = MainServer.startServer();
		Client c = ClientBuilder.newClient();
		target = c.target(MainServer.BASE_URI);
	}

	@After
	public void tearDown() {
		server.shutdown();
	}

	@Test
	public void testGetDevice() {
		final String testIp = "10.11.58.184";
		final Device device = target.path("device").queryParam("ip", testIp)
				.request().get(Device.class);
		System.out.println(device.getDeviceIp());
	}

	@Test
	public void testPutDevice() {
		final String testIp = "10.11.58.163";
		final Device device = new Device(testIp);
		device.setDeviceStatus(1);
		Entity<Device> entity = Entity.entity(device,
				MediaType.APPLICATION_XML_TYPE);
		final Device result = target.path("device").request()
				.put(entity, Device.class);
		System.out.println(result.getDeviceIp());
	}
}
