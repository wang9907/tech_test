package com.summer.tech.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.summer.tech.mybatisplus.entity.Student;

/**
 * 注意：Dao层接口直接对应mapper.xml。
 * Dao层路径+接口名=mapper文件的namespace
 * @author Administrator
 *
 */
public interface StudentMapper extends BaseMapper<Student> {

    public Integer selectMaxAge();

}