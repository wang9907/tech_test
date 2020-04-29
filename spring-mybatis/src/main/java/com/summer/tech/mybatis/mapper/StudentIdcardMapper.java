package com.summer.tech.mybatis.mapper;

import com.summer.tech.mybatis.entity.StudentIdcard;

public interface StudentIdcardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentIdcard record);

    int insertSelective(StudentIdcard record);

    StudentIdcard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentIdcard record);

    int updateByPrimaryKey(StudentIdcard record);
}