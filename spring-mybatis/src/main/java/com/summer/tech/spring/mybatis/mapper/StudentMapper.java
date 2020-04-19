package com.summer.tech.spring.mybatis.mapper;

import java.util.List;

import com.summer.tech.spring.mybatis.entity.Student;
/**
 * 注意：Dao层接口直接对应mapper.xml。
 * Dao层路径+接口名=mapper文件的namespace
 * @author Administrator
 *
 */
public interface StudentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    //自己新增的方法。查询全部学生信息
    List<Student> selectAllStudent();

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectStudentAndIdcard();

}