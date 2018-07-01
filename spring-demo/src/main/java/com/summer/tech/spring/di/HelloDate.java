package com.summer.tech.spring.di;

import java.util.Date;  

public class HelloDate {  
    private Date date;  
  
    private HelloBean hb;  
  
    public void setDate(Date date) {  
        this.date = date;  
    }  
  
    public void setHb(HelloBean hb) {  
        this.hb = hb;  
    }  
  
    public void sayHello() {  
        System.out.println(hb.getHello());  
    }  
  
}