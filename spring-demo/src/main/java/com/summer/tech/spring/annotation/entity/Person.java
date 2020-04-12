package com.summer.tech.spring.annotation.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import javax.inject.Inject;

public class Person {
	private String name;
	private Integer age;

//	@Qualifier("com.summer.tech.spring.annotation.entity.Dog")
//	@Resource
	@Inject()
	@Autowired
	Dog dog;

	public Person(){
		super();
	}

	public void init(){
		System.out.println("init");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Integer getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	private void destroy() {
		System.out.println("destroy");
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}
}
