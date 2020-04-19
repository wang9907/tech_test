package com.summer.tech.spring.mybatis.mapper;

import com.summer.tech.spring.mybatis.entity.FemaleHealthForm;
import com.summer.tech.spring.mybatis.entity.MaleHealthForm;

public interface FemaleHealthFormMapper {
    FemaleHealthForm findFemaleHealthByStudentId(int id);
}