package com.summer.tech.springboot.test;

import com.summer.tech.springboot.mybatis.model.Orders;
import com.summer.tech.springboot.mybatis.model.Users;
import com.summer.tech.springboot.service.mybatis.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Resource
    private IOrderService iOrderService;

    @Test
    public void test1() {
        Users users = new Users();
        users.setUsername("enjoy");
        users.setPasswd("123");
        //users.setId(1);
        Orders orders = new Orders();
        orders.setAccount(12);
        orders.setName("娃娃");
        //orders.setUserId(1);
        iOrderService.addOrder(orders,users);
    }

}
