package com.summer.tech.jpa.service;

import com.summer.tech.jpa.entity.Person;
import com.summer.tech.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void save(Person person) {
        personRepository.save(person);
    }

    public void insertAndUpdate(){
        Person o = new Person();
        o.setName("guest");
        o.setGender("男");
        save(o);//JPA保存用户

    }
}