package com.summer.tech.spring.jdbc;

import java.sql.Types;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlQuery;

//省略import  
public class UserModelSqlQuery extends SqlQuery<UserModel> {
	public UserModelSqlQuery(JdbcTemplate jdbcTemplate) {
		// super.setDataSource(jdbcTemplate.getDataSource());
		super.setJdbcTemplate(jdbcTemplate);
		super.setSql("select * from test where name=?");
		super.declareParameter(new SqlParameter(Types.VARCHAR));
		compile();
	}

	@Override
	protected RowMapper<UserModel> newRowMapper(Object[] parameters, Map context) {
		return new UserRowMapper();
	}
}