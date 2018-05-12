package com.summer.tech.jws.rs.javase.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Device {

	private String deviceIp;
	private int deviceStatus;
	
	public Device(){
		
	}
	
	public Device(String deviceIp){
		super();
		this.deviceIp = deviceIp;
	}

	@XmlAttribute
	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	@XmlAttribute
	public int getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(int deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	
}
