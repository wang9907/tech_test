package com.summer.tech.spring.mybatis.dao;

import java.util.List;

import com.summer.tech.spring.mybatis.entity.StudentBo;
/**
 * 注意：Dao层接口直接对应mapper.xml。
 * Dao层路径+接口名=mapper文件的namespace
 * @author Administrator
 *
 */
public interface StudentDao {

    int deleteByPrimaryKey(Integer id);

    int insert(StudentBo record);

    int insertSelective(StudentBo record);

    StudentBo selectByPrimaryKey(Integer id);

    //自己新增的方法。查询全部学生信息
    List<StudentBo> selectAllStudent();

    int updateByPrimaryKeySelective(StudentBo record);

    int updateByPrimaryKey(StudentBo record);
}