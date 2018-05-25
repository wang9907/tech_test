package com.summer.tech.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.summer.tech.springboot.entity.Person;
import com.summer.tech.springboot.entity.Result;
import com.summer.tech.springboot.enums.ResultEnum;
import com.summer.tech.springboot.exception.PersonException;
import com.summer.tech.springboot.respository.PersonRespository;
import com.summer.tech.springboot.utils.ResultUtil;

@RestController
public class PersonController {

	@Autowired
	private PersonRespository respository;

	@GetMapping(value = "/persons")
	public Result<List<Person>> personList() {
		return ResultUtil.success(respository.findAll());
	}

	@PostMapping(value = "/persons")
	public Result<Person> psersonAdd(@RequestParam("name") String name,
			@RequestParam("gender") String gender,
			@RequestParam("age") Integer age) {
		Person person = new Person();
		person.setName(name);
		person.setGender(gender);
		person.setAge(age);
		return ResultUtil.success(respository.save(person));
	}

	@PostMapping(value = "/persons1")
	public Result<Person> psersonAdd(@Valid Person person, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result.getFieldError().getDefaultMessage());
			// return ResultUtil.error(301, result.getFieldError()
			// .getDefaultMessage());
			throw new PersonException(ResultEnum.SAMLL_ERROR);
		}
		return ResultUtil.success(respository.save(person));
	}

	/**
	 * 获取某个人
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/persons/{id}")
	public Person psersonAdd(@PathVariable("id") Integer id) {
		return respository.findOne(id);
	}

	@PutMapping(value = "/persons/{id}")
	public Person psersonUpdate(@PathVariable("id") Integer id,
			@RequestParam("name") String name, @RequestParam("age") Integer age) {
		Person person = new Person();
		person.setId(id);
		person.setName(name);
		person.setAge(age);
		return respository.save(person);
	}

	@DeleteMapping(value = "/persons/{id}")
	public void psersonUpdate(@PathVariable("id") Integer id) {
		respository.delete(id);
	}

	@GetMapping(value = "/persons/name/{name}")
	public List<Person> psersonListByName(@PathVariable("name") String name) {
		return respository.findByName(name);
	}
}
