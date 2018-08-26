package com.summer.tech.springmvc.entity;

import javax.validation.constraints.NotNull;

public class UserModel {
	
	//从配置文件取错误消息{username.not.empty}
	@NotNull(message="{username.not.empty}") 
	private String username;
	@NotNull(message="密码不能为空")
	private String password;

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

}