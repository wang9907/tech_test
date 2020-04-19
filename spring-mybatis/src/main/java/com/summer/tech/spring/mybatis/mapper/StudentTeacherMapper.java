package com.summer.tech.spring.mybatis.mapper;

import com.summer.tech.spring.mybatis.entity.StudentTeacher;

public interface StudentTeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentTeacher record);

    int insertSelective(StudentTeacher record);

    StudentTeacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentTeacher record);

    int updateByPrimaryKey(StudentTeacher record);
}