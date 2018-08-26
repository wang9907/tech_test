package com.summer.tech.springmvc.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.tech.springmvc.entity.UserModel;

@Controller
public class ValidatorController {

	@RequestMapping("/validate.htm")  
    public String validate(@Valid @ModelAttribute("user") UserModel user, Errors errors) {  
          
        if(errors.hasErrors()) {  
            return "error";  
        }  
        return "success";  
    }  
}
