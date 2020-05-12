package com.summer.tech.springboot.service.mybatis;

import com.summer.tech.springboot.mybatis.model.Orders;
import com.summer.tech.springboot.mybatis.model.Users;

public interface IOrderService {
     void addOrder(Orders orders, Users users);
}
