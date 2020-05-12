package com.summer.tech.springboot.service.mybatis;

import com.summer.tech.springboot.mybatis.mapper.orders.OrdersMapper;
import com.summer.tech.springboot.mybatis.mapper.users.UsersMapper;
import com.summer.tech.springboot.mybatis.model.Orders;
import com.summer.tech.springboot.mybatis.model.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl  implements IOrderService{

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public void addOrder(Orders orders, Users users) {
        usersMapper.insertSelective(users);
        ordersMapper.insertSelective(orders);
    }
}
