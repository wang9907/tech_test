package com.summer.tech.jws.rs.javase.dao;

import java.util.concurrent.ConcurrentHashMap;

import com.summer.tech.jws.rs.javase.entity.Device;

public class DeviceDao {

	ConcurrentHashMap<String, Device> fakeDB = new ConcurrentHashMap<>();

	public DeviceDao() {
		fakeDB.put("10.11.58.163", new Device("10.11.58.163"));
		fakeDB.put("10.11.58.184", new Device("10.11.58.184"));
	}

	public Device getDevice(String deviceIp) {
		return fakeDB.get(deviceIp);
	}

	public Device updateDevice(Device device) {
		String ip = device.getDeviceIp();
		fakeDB.put(ip, device);
		return fakeDB.get(ip);
	}
}
