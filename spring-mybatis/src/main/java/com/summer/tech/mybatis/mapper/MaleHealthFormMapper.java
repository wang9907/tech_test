package com.summer.tech.mybatis.mapper;

import com.summer.tech.mybatis.entity.MaleHealthForm;

public interface MaleHealthFormMapper {
    MaleHealthForm findMaleHealthByStudentId(int id);
}