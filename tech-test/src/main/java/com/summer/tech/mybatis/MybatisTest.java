package com.summer.tech.mybatis;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.summer.tech.mybatis.dao.DoctorMapper;
import com.summer.tech.mybatis.model.DoctorWithBLOBs;

public class MybatisTest {

	public static void main(String[] args) throws IOException {

		SqlSession session = MybatisUtil.getSqlSession();
		DoctorMapper mapper = session.getMapper(DoctorMapper.class);
		DoctorWithBLOBs doc = mapper.selectByPrimaryKey(21L);
		System.out.println(doc.getName());

		session.close();
	}

	public void configuration() {
		/*
		 * DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
		 * TransactionFactory transactionFactory = new JdbcTransactionFactory();
		 * Environment environment = new Environment("development",
		 * transactionFactory, dataSource); Configuration configuration = new
		 * Configuration(environment);
		 * //configuration.addMapper(BlogMapper.class); SqlSessionFactory
		 * sqlSessionFactory = new
		 * SqlSessionFactoryBuilder().build(configuration);
		 */
	}
}
