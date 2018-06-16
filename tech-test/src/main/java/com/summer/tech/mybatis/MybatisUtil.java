package com.summer.tech.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	private static SqlSessionFactory sqlSessionFactory = null;

	public static synchronized void BuildSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			try {
				String resource = "com/summer/tech/mybatis/mybatis-config.xml";
				InputStream inputStream = MybatisUtil.class.getClassLoader()
						.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder()
						.build(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			BuildSqlSessionFactory();
		}
		return sqlSessionFactory;
	}

	public static SqlSession getSqlSession() {
		if(sqlSessionFactory ==null) {
			BuildSqlSessionFactory();
		}
		return sqlSessionFactory.openSession();
	}

}
