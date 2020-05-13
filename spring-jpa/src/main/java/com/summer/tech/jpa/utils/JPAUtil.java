package com.summer.tech.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 工具类:单例模式/静态单例模式  静态方法
 */
public class JPAUtil {
    // 私有化这个构造器,不让其它人创建这个类
    private JPAUtil(){}

    // 实体管理工厂
    // 注意:EntityManagerFactory这个类是线程安全
    private static EntityManagerFactory entityManagerFactory;

    /**
     * 静态代码块，类加载的时候就会执行里面的代码，只会执行一次
     */
    static{
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("拿到EntityManagerFactory运行时出错："+e.getMessage());
        }
    }

    // 拿到一个EntityManager对象
    // 每次拿EntityManager都需要重新创建(EntityManager不是线程安全的对象，每次使用都重新创建一次)
    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    public static void close(EntityManager entityManager){
        //关闭资源
        entityManager.close();
        entityManagerFactory.close();
    }
}