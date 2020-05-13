package com.summer.tech.jpa.test;

import com.summer.tech.jpa.entity.Person;
import com.summer.tech.jpa.utils.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaTest {

    @Test
    public void test(){
        Person person = new Person();
        person.setName("阿布多瑞");
        person.setAge(20);
        person.setGender("男");

        // 通过持久化类创建一个实体管理工厂
        //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
        //创建一个实体管理器，可以实现CRUD
        EntityManager entityManager = JPAUtil.getEntityManager();
        // 有entityManager来开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //持久层操作CRUD,写入数据库
        entityManager.persist(person);
        //提交事务
        transaction.commit();
        //关闭资源
        JPAUtil.close(entityManager);
    }
}
