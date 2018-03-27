package com.summer.tech.jpa.service.impl;

import com.summer.tech.jpa.dao.UserDao;
import com.summer.tech.jpa.dao.impl.UserDaoImpl;
import com.summer.tech.jpa.entity.AccountInfo;
import com.summer.tech.jpa.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	public AccountInfo createNewAccount(String user, String pwd, Integer init) {
		// 封装域对象
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setBalance(init);
		accountInfo.setAccountId(10L);
		// 调用持久层，完成数据的保存
		return userDao.save(accountInfo);
	}
}