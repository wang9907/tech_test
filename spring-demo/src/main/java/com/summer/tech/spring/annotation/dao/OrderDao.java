package com.summer.tech.spring.annotation.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class OrderDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(){
        jdbcTemplate.update("INSERT INTO goods(name)VALUES (?)", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,"马铃薯");
            }
        });
    }
}
