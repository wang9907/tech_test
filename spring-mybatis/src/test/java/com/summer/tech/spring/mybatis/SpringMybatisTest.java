package com.summer.tech.spring.mybatis;

import com.summer.tech.spring.mybatis.mapper.StudentMapper;
import com.summer.tech.spring.mybatis.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class SpringMybatisTest {

    @Resource
    StudentMapper studentMapper;
    
    @Test
    public void insertTest(){
        Student Student=new Student();
        Student.setName("小明1");
        Student.setAge(8);
        Student.setAddress("宝安裕丰花园1");
        studentMapper.insert(Student);
    }

    @Test
    public void queryTest(){
        int id = 1;
        Student Student = studentMapper.selectByPrimaryKey(id);
        System.out.println(Student);
    }

}
