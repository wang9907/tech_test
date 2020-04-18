package com.summer.tech.spring.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("student")
public class Student implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    private Integer age;

    private String address;

}