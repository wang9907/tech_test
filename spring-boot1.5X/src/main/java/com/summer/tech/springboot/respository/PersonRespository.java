package com.summer.tech.springboot.respository;

import com.summer.tech.springboot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRespository extends JpaRepository<Person, Integer> {

 public List<Person> findByName(String name);
 }
