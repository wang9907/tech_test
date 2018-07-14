package com.summer.tech.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs, int row_num) throws SQLException {
		UserModel userModel = new UserModel();
		userModel.setId(rs.getInt("id"));
		userModel.setName(rs.getString("name"));
		return userModel;
	}

}
