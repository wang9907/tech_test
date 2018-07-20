package com.summer.tech.spring.mybatis.service;

import java.util.List;

import com.summer.tech.spring.mybatis.entity.StudentBo;

public interface ICacheService {
	final String SERVICEID = "ICacheService";

	public List<StudentBo> getAllStudent();

	public StudentBo getStudentById(int id);

}