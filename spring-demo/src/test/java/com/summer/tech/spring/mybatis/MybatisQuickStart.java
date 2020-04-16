package com.summer.tech.spring.mybatis;

import com.summer.tech.spring.mybatis.dao.StudentDao;
import com.summer.tech.spring.mybatis.entity.StudentBo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

		sqlSession.select("selectAllStudent", new ResultHandler() {
			@Override
			public void handleResult(ResultContext resultContext) {
				System.out.println(resultContext.getResultCount());
			}
		});
		sqlSession.select("selectAllStudent",null,new RowBounds(1,2), new ResultHandler() {
			@Override
			public void handleResult(ResultContext resultContext) {
				StudentBo student = (StudentBo) resultContext.getResultObject();
				System.out.println(student.getName()+""+student.getAddress());
			}
		});
		List<Object> list = sqlSession.selectList("com.summer.tech.spring.mybatis.dao.StudentDao.selectByPrimaryKey",1);
		System.out.println(list.size());
	}
	
}
