package com.summer.tech.mybatis.entity;

import java.util.List;

public class MaleStudent extends Student {

    private List<MaleHealthForm> maleHealthForms;

    public List<MaleHealthForm> getMaleHealthForms() {
        return maleHealthForms;
    }

    public void setMaleHealthForms(List<MaleHealthForm> maleHealthForms) {
        this.maleHealthForms = maleHealthForms;
    }
}
