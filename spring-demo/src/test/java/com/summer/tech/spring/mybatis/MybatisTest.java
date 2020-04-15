package com.summer.tech.spring.mybatis;

import com.summer.tech.spring.mybatis.dao.StudentDao;
import com.summer.tech.spring.mybatis.entity.StudentBo;
import com.summer.tech.spring.mybatis.service.ICacheService;
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

    @Resource
    ICacheService iCacheService;

    @Test
    public void insertTest(){
        StudentBo studentBo=new StudentBo();
        studentBo.setName("小明1");
        studentBo.setAge(8);
        studentBo.setAddress("宝安裕丰花园1");
        studentDao.insert(studentBo);
    }

    @Test
    public void transactionTest(){
        iCacheService.insert();
    }

    @Test
    public void queryTest(){
        int id = 1;
        StudentBo studentBo = studentDao.selectByPrimaryKey(id);
        System.out.println(studentBo);
    }

}
