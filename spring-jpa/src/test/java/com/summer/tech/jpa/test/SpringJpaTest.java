package com.summer.tech.jpa.test;

import com.summer.tech.jpa.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringJpaTest {

    @PersistenceContext(name="entityManagerFactory")
    EntityManager entityManager;

    @Test
    @Transactional(rollbackFor = {RuntimeException.class})
    public void test(){
        Person person = new Person();
        person.setName("阿布多瑞111");
        person.setAge(20);
        person.setGender("男");
        //持久层操作CRUD,写入数据库
        entityManager.persist(person);
    }
}
