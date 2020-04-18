package com.summer.tech.spring.mybatis;

import com.summer.tech.spring.mybatis.mapper.StudentMapper;
import com.summer.tech.spring.mybatis.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatisPlus.xml" })
public class MybatisPlusTest {

    @Resource
    StudentMapper studentMapper;

    @Test
    public void insertTest(){
        Student student=new Student();
        student.setName("小明222");
        student.setAge(8);
        student.setAddress("宝安裕丰花园1");
        studentMapper.insert(student);
    }

    @Test
    public void queryTest(){
        int id = 1;
        Student student = studentMapper.selectById(id);
        System.out.println(student);

        int maxAge = studentMapper.selectMaxAge();
        System.out.println(maxAge);
    }

}
