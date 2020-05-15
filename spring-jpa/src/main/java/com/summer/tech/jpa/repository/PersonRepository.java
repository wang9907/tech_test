package com.summer.tech.jpa.repository;

import com.summer.tech.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    public List<Person> findByNameAndAge(String name,int age);
}