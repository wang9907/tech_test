package com.summer.tech.springboot.jpa.respository.order;

import com.summer.tech.springboot.jpa.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends JpaRepository<Order, Integer> {

    public Order findByOrderCode(String orderCode);

 }
