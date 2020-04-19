package com.summer.tech.spring.mybatis.mapper;

import com.summer.tech.spring.mybatis.entity.StudentIdcard;

public interface StudentIdcardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentIdcard record);

    int insertSelective(StudentIdcard record);

    StudentIdcard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentIdcard record);

    int updateByPrimaryKey(StudentIdcard record);
}