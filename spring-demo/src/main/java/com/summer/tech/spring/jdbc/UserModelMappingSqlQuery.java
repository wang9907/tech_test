package com.summer.tech.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

//省略import  
public class UserModelMappingSqlQuery extends MappingSqlQuery<UserModel> {
	public UserModelMappingSqlQuery(JdbcTemplate jdbcTemplate) {
		super.setDataSource(jdbcTemplate.getDataSource());
		super.setSql("select * from test where name=:name");
		super.declareParameter(new SqlParameter("name", Types.VARCHAR));
		compile();
	}

	@Override
	protected UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserModel model = new UserModel();
		model.setId(rs.getInt("id"));
		model.setName(rs.getString("name"));
		return model;
	}
}