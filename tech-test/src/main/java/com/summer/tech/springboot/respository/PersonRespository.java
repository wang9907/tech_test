package com.summer.tech.springboot.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.summer.tech.springboot.entity.Person;

public interface PersonRespository extends JpaRepository<Person, Integer>{

	public List<Person> findByName(String name);
}
