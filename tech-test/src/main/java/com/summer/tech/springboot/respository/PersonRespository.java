package com.summer.tech.springboot.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.summer.tech.springboot.entity.Person;

public interface PersonRespository extends JpaRepository<Person, Integer>{

}
