package com.summer.tech.springboot.test;

import com.summer.tech.springboot.jpa.entity.person.Person;
import com.summer.tech.springboot.jpa.respository.person.PersonRespository;
import com.summer.tech.springboot.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

    @Resource
    private PersonRespository personRespository;

    @Resource
    private PersonService personService;

    @Test
    public void test1() {
        Person person = new Person();
        person.setName("wang");
        person.setAge(20);
        person.setGender("男");

        personRespository.save(person);
    }

    @Test
    public void test2() {
        personService.insertTwo();
    }

}
