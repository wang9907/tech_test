package com.summer.tech.jpa.repository;

import com.summer.tech.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}