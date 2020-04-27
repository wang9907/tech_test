package com.summer.tech.springmvc.controller;

import com.summer.tech.springmvc.entity.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

//1、在控制器类头上添加@SessionAttributes注解
@SessionAttributes(value = {"user"})    //①
@Controller
public class SessionAttributeController {

    //2、@ModelAttribute注解的方法进行表单引用对象的创建
    @ModelAttribute("user")    //②
    public UserModel initUser() {
        UserModel user = new UserModel();
        user.setUsername("zhang");
        user.setPassword("123456");
        return user;
    }

    //3、@RequestMapping注解方法的@ModelAttribute注解的参数进行命令对象的绑定
    @RequestMapping("/session1")   //③
    public String session1(@ModelAttribute("user") UserModel user) {

        return "test";
    }

    //4、通过SessionStatus的setComplete()方法清除@SessionAttributes指定的会话数据
    @RequestMapping("/session2")   //③
    public String session(@ModelAttribute("user") UserModel user, SessionStatus status) {
        if (true) { //④
            status.setComplete();
        }
        return "success";
    }
}