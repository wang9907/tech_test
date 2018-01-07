package com.summer.tech.ezmorph;

import java.util.Date;

public class Student {  
    private int age;  
    private String name; 
    private Date birthday;
    
    public int getAge() {  
        return age;  
    }  
    public void setAge(int age) {  
        this.age = age;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    
    public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public void hi(int age,String name){  
        System.out.println("我是"+name+"，今年"+age+"岁");  
    }  
}  