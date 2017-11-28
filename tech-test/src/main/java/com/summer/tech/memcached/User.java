package com.summer.tech.memcached;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -372274003834027815L;

	String userId;

	public User(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("userId=" + this.userId);
		return sb.toString();
	}
}