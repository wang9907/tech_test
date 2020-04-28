package com.summer.tech.springmvc.entity;

import com.summer.tech.springmvc.utils.PhoneNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserModel {
	
	//从配置文件取错误消息{username.not.empty}
	@NotNull(message="{username.not.empty}")
	@NotBlank(message = "{username.not.empty}")
	private String username;
	@NotNull(message="密码不能为空")
	private String password;
	@PhoneNumber
	private PhoneNumberModel phoneNumber;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PhoneNumberModel getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumberModel phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}