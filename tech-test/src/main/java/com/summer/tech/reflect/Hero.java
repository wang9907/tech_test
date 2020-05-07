package com.summer.tech.reflect;

public class Hero {
    //---------------构造方法-------------------
    //（默认的构造方法）
    Hero(String str){
        System.out.println("(默认)的构造方法 s = " + str);
    }

    //无参构造方法
    public Hero(){
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    //有一个参数的构造方法
    public Hero(char name){
        System.out.println("姓名：" + name);
    }

    //有多个参数的构造方法
    public Hero(String name ,float hp){
        System.out.println("姓名："+name+"血量："+ hp);
    }

    //受保护的构造方法
    protected Hero(boolean n){
        System.out.println("受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private Hero(float hp){
        System.out.println("私有的构造方法   血量："+ hp);
    }
}
