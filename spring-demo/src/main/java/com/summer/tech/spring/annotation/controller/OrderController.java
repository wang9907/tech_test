package com.summer.tech.spring.annotation.controller;

import com.summer.tech.spring.annotation.dao.OrderDao;
import com.summer.tech.spring.annotation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class OrderController {

//    @Qualifier("orderService")
//    @Resource
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDao orderDao;

}
