package com.summer.tech.spring.mybatis.entity;

import java.awt.print.PrinterGraphics;
import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer age;

    private String address;

    private int gender;

    private StudentIdcard studentIdcard;

    private List<BankCard> bankCards;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public StudentIdcard getStudentIdcard() {
        return studentIdcard;
    }

    public void setStudentIdcard(StudentIdcard studentIdcard) {
        this.studentIdcard = studentIdcard;
    }

    public List<BankCard> getBankCards() {
        return bankCards;
    }

    public void setBankCards(List<BankCard> bankCards) {
        this.bankCards = bankCards;
    }

    @Override
    public String toString() {
        return "StudentBo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}