package com.summer.tech.spring.mybatis.service;

import com.summer.tech.spring.mybatis.entity.StudentBo;

import java.util.List;

public interface ICacheService {
	final String SERVICEID = "ICacheService";

	public List<StudentBo> getAllStudent();

	public StudentBo getStudentById(int id);

	public void insert();

}