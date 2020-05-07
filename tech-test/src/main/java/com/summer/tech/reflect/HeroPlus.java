package com.summer.tech.reflect;

import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class HeroPlus {
    @Id
    public String name;
    @Column
    private float hp;
    public int damage;
    private int id;

    public String getName() {
        return name;
    }

    @Test
    public void setName(String name) {
        this.name = name;
    }

    public HeroPlus() {
    }

    public HeroPlus(String string) {
        name = string;
    }

    @Override
    public String toString() {
        return "Hero [name=" + name + "]";
    }

    public boolean isDead() {
        // TODO Auto-generated method stub
        return false;
    }

    public void attackHero(HeroPlus h2) {
        System.out.println(this.name + " 正在攻击 " + h2.getName());
    }
}
