package com.summer.tech.spring.mybatis.mapper;

import com.summer.tech.spring.mybatis.entity.MaleHealthForm;

public interface MaleHealthFormMapper {
    MaleHealthForm findMaleHealthByStudentId(int id);
}