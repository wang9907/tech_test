package com.summer.tech.springboot.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.summer.tech.springboot.entity.Person;
import com.summer.tech.springboot.respository.PersonRespository;

public class PersonService {

	@Autowired
	private PersonRespository respository;

	@Transactional
	public void insertTwo() {
		Person person = new Person();
		person.setName("刘虎林");
		person.setGender("男");
		person.setAge(12);
		respository.save(person);

		person = new Person();
		person.setName("刘虎林");
		person.setGender("男");
		person.setAge(12);
		respository.save(person);

	}
	
	public Person findById(Integer id){
		return respository.findOne(id);
	}
	
}
