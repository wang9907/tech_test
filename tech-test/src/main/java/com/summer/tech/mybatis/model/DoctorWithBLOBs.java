package com.summer.tech.mybatis.model;

import java.io.Serializable;

public class DoctorWithBLOBs extends Doctor implements Serializable {
    private String introduction;

    private String description;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}