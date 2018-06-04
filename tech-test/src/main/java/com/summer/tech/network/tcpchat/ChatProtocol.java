package com.summer.tech.network.tcpchat;

public interface ChatProtocol {

	int PROTOCOL_LEN = 2;
	//协议字符串
	String MSG_ROND = "##";
	String USER_ROND = "@@";
	String LOGIN_SUCCESS = "1";
	String NAME_REP="-1";
	String PRIVATE_ROND = "%%";
	String SPLIT_SIGN = "}";
	
}
