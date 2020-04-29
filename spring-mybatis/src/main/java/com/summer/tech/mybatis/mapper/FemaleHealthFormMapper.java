package com.summer.tech.mybatis.mapper;

import com.summer.tech.mybatis.entity.FemaleHealthForm;

public interface FemaleHealthFormMapper {
    FemaleHealthForm findFemaleHealthByStudentId(int id);
}