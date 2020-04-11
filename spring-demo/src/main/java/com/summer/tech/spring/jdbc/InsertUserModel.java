package com.summer.tech.spring.jdbc;  

import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

//省略import  
public class InsertUserModel extends SqlUpdate {  
    public InsertUserModel(JdbcTemplate jdbcTemplate) {  
        super.setJdbcTemplate(jdbcTemplate);  
        super.setSql("insert into goods(name) values(?)");
        super.declareParameter(new SqlParameter(Types.VARCHAR));  
        compile();  
    }  
}