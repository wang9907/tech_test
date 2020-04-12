package com.summer.tech.spring;

import com.summer.tech.spring.mybatis.dao.StudentDao;
import com.summer.tech.spring.mybatis.entity.StudentBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class MybatisTest {

    @Resource
    StudentDao studentDao;

    @Test
    public void insertTest(){
        StudentBo studentBo=new StudentBo();
        studentBo.setName("小明");
        studentBo.setAge(8);
        studentBo.setAddress("宝安裕丰花园");
        studentDao.insert(studentBo);
    }

    @Test
    public void quertTest(){
        int id = 1;
        StudentBo studentBo = studentDao.selectByPrimaryKey(id);
        System.out.println(studentBo);
    }

}
