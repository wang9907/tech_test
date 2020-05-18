package com.summer.tech.redis.test;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JedisTest {

    Jedis jedis = null;

    @Before
    public void init() {
        jedis = new Jedis("localhost");
    }

    @Test
    public void keyTest() {
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }
    }

    @Test
    public void stringTest() {
        jedis.set("str_city", "深圳");// 不存在就新建，存在就覆盖
        // nx ： not exists, 只有key不存在时才把key value set 到redis
        // xx ： is exists ，只有key存在时，才把key value set 到redis
        // expx参数有两个值可选 ：
        //   ex ： seconds 秒
        //   px :   milliseconds 毫秒
        jedis.set("str_title", "新一线逆袭1", "nx", "ex", 10);
        jedis.setnx("str_tree", "白岩松");
        jedis.append("str_city", "宝安区");

        jedis.mset("str_key1","key1","str_key2","key2");
        jedis.msetnx("str_keynx1","keynx11","str_keynx2","keynx22");
        jedis.setbit("str_bit1",1,"bitt");
        jedis.getbit("str_bit1",100);
        jedis.setex("str_ex1",10,"keyex1");
        jedis.strlen("strlen");
//        jedis.incr("str_incr1");
//        jedis.incrBy("str_incrBy1",10);
//        jedis.incrByFloat("str_incrByFloat",101.1);
//        jedis.decr("decr1");
//        jedis.decrBy("decrby",11);
//        List<String> values = jedis.mget("key1", "key2");

        System.out.println(jedis.get("str_city"));
        System.out.println(jedis.get("str_title"));
        System.out.println(jedis.get("str_key1"));
        System.out.println(jedis.get("str_keynx1"));
    }

    @Test
    public void listTest() {
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }
    }

    @Test
    public void setTest() {

    }

    @Test
    public void sortsetTest() {

    }

    @Test
    public void hashTest() {

    }
}
