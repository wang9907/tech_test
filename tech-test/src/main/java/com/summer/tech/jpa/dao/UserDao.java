package com.summer.tech.jpa.dao;

import com.summer.tech.jpa.entity.AccountInfo;

public interface UserDao {
	public AccountInfo save(AccountInfo accountInfo);
}