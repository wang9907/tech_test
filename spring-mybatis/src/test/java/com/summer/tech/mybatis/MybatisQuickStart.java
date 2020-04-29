package com.summer.tech.mybatis;

import com.summer.tech.mybatis.entity.Student;
import com.summer.tech.mybatis.mapper.StudentMapper;
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
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		// 4.执行查询语句并返回结果
		Student Student = mapper.selectByPrimaryKey(1);
		System.out.println(Student.toString());

		sqlSession.select("selectAllStudent", new ResultHandler() {
			@Override
			public void handleResult(ResultContext resultContext) {
				System.out.println(resultContext.getResultCount());
			}
		});
		sqlSession.select("selectAllStudent",null,new RowBounds(1,2), new ResultHandler() {
			@Override
			public void handleResult(ResultContext resultContext) {
				Student student = (Student) resultContext.getResultObject();
				System.out.println(student.getName()+""+student.getAddress());
			}
		});
		List<Object> list = sqlSession.selectList("com.summer.tech.spring.mybatis.dao.StudentMapper.selectByPrimaryKey",1);
		System.out.println(list.size());
	}

	@Test
	public void cacheTest(){
		// 2.获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 3.获取对应mapper
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		// 4.执行查询语句并返回结果
		Student Student = mapper.selectByPrimaryKey(1);
		System.out.println(Student.toString());
		Student = mapper.selectByPrimaryKey(1);
		System.out.println(Student.toString());

	}
}
