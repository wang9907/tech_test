package com.summer.tech.spring.annotation.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Tiger {

    @PostConstruct
    public void init(){
        System.out.println("init");
    }

    @PreDestroy
    public void desctroy(){
        System.out.println("destroy");
    }
}
