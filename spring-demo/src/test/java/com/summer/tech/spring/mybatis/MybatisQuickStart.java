package com.summer.tech.spring.mybatis;

import com.summer.tech.spring.mybatis.dao.StudentDao;
import com.summer.tech.spring.mybatis.entity.StudentBo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisQuickStart {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void init() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 1.读取mybatis配置文件创SqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		inputStream.close();
	}

	@Test
	// 快速入门
	public void quickStart() throws IOException {
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.获取对应mapper
		StudentDao mapper = sqlSession.getMapper(StudentDao.class);
		// 4.执行查询语句并返回结果
		StudentBo studentBo = mapper.selectByPrimaryKey(1);
		System.out.println(studentBo.toString());

	}
	
}
