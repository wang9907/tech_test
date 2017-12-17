package com.summer.tech.springboot.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.summer.tech.springboot.entity.Person;
import com.summer.tech.springboot.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonApplicationTest {
	
	@Autowired
	PersonService personService;

	@Test
	public void queryTest(){
		Integer id = 1;
		Person pserson = personService.findById(id);
		Assert.assertEquals(new Integer(1), pserson.getId());
	}
	
}
