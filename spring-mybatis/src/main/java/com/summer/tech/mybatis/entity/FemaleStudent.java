package com.summer.tech.mybatis.entity;

import java.util.List;

public class FemaleStudent extends Student {

    private List<FemaleHealthForm> femaleHealthForms;

    public List<FemaleHealthForm> getFemaleHealthForms() {
        return femaleHealthForms;
    }

    public void setFemaleHealthForms(List<FemaleHealthForm> femaleHealthForms) {
        this.femaleHealthForms = femaleHealthForms;
    }
}
