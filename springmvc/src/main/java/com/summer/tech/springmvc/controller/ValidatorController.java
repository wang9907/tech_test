package com.summer.tech.springmvc.controller;

import com.summer.tech.springmvc.entity.PhoneNumberModel;
import com.summer.tech.springmvc.entity.UserModel;
import com.summer.tech.springmvc.utils.PhoneNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class ValidatorController {

    @RequestMapping("/validate.htm")
    public String validate(@Valid @ModelAttribute("user") UserModel user, Errors errors) {
        System.out.println(user.getPhoneNumber());
        if(errors.hasErrors()) {
            return "error";
        }
        return "success";
    }

    // 使用类型转换器和格式化器的方式就是无法进行转换，需要在这里做下类型注册，使用转换服务不生效
    // 原因找到了，是配置了mvc:annotation-driven元素，导致转换服务注册无效
    @InitBinder
   public void InitBinder(WebDataBinder webDataBinder, WebRequest webRequest){
        webDataBinder.registerCustomEditor(PhoneNumberModel.class, new PhoneNumberEditor());
    }
}
