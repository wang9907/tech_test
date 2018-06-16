package com.summer.tech.mybatis.dao;

import com.summer.tech.mybatis.model.Doctor;
import com.summer.tech.mybatis.model.DoctorWithBLOBs;

public interface DoctorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DoctorWithBLOBs record);

    int insertSelective(DoctorWithBLOBs record);

    DoctorWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DoctorWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DoctorWithBLOBs record);

    int updateByPrimaryKey(Doctor record);
}