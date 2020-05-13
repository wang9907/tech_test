package com.summer.tech.springboot.service;

import com.summer.tech.springboot.jpa.entity.order.Order;
import com.summer.tech.springboot.jpa.entity.person.Person;
import com.summer.tech.springboot.jpa.respository.order.OrderRespository;
import com.summer.tech.springboot.jpa.respository.person.PersonRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class PersonService {

    @Autowired
    private PersonRespository personRespository;
    @Autowired
    private OrderRespository orderRespository;

    @Transactional
    public void insertTwo() {
        Person person = new Person();
        person.setName("刘虎林");
        person.setGender("男");
        person.setAge(20);
        personRespository.save(person);
        //int i=10/0;
        Order order = new Order();
        order.setPerson("liu");
        order.setDate(new Date());
        order.setOrderCode("2020051312121");
        orderRespository.save(order);

    }

    public Person findById(Integer id) {
        Person person = personRespository.findOne(id);
        return person;
    }

}
