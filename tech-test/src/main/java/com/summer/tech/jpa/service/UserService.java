package com.summer.tech.jpa.service;

import com.summer.tech.jpa.entity.AccountInfo;

public interface UserService {
	public AccountInfo createNewAccount(String user, String pwd, Integer init);
}