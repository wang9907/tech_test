package com.summer.tech.springboot.respository;

import com.summer.tech.springboot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRespository extends JpaRepository<Person, Integer> {

 public List<Person> findByName(String name);

 }
