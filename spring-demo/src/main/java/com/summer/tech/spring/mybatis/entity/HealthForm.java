package com.summer.tech.spring.mybatis.entity;

//体检表父类
public abstract class HealthForm {
    private Long id;
    private Long empId;
    private String heart;
    private String liver;
    private String spleen;
    private String lung;
    private String kidney;
    private String note;
}