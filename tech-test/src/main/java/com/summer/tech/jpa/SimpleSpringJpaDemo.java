package com.summer.tech.jpa;

import com.summer.tech.jpa.service.impl.UserServiceImpl;

public class SimpleSpringJpaDemo {
   public static void main(String[] args) {
       new UserServiceImpl().createNewAccount("ZhangJianPing", "123456", 1);
   }
}