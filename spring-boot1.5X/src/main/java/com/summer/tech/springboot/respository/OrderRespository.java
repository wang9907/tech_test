package com.summer.tech.springboot.respository;

import com.summer.tech.springboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends JpaRepository<Order, Integer> {

    public Order findByOrderCode(String orderCode);

 }
