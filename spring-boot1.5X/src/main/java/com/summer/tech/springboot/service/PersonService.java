package com.summer.tech.springboot.service;

import com.summer.tech.springboot.jpa.entity.Person;
import com.summer.tech.springboot.jpa.respository.OrderRespository;
import com.summer.tech.springboot.jpa.respository.PersonRespository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

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
        person.setAge(12);
        personRespository.save(person);

        person = new Person();
        person.setName("刘虎林");
        person.setGender("男");
        person.setAge(12);
        personRespository.save(person);

    }

    public Person findById(Integer id) {
        Person person = personRespository.findOne(id);
        return person;
    }

}
