package com.summer.tech.jpa.test;

import com.summer.tech.jpa.config.AppConfig;
import com.summer.tech.jpa.config.DaoConfig;
import com.summer.tech.jpa.config.DataSourceConfig;
import com.summer.tech.jpa.entity.Person;
import com.summer.tech.jpa.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DaoConfig.class, DataSourceConfig.class})
public class SpringDataJpaTest {

    @Autowired
    private PersonService personService;

    @Test
    public void save() {
        Person person = new Person();
        person.setName("阿布多瑞111");
        person.setAge(20);
        person.setGender("男");
        //持久层操作CRUD,写入数据库
        personService.save(person);
    }

}
