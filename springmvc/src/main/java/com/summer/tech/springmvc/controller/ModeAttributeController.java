package com.summer.tech.springmvc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.tech.springmvc.entity.PhoneNumberModel;
import com.summer.tech.springmvc.entity.UserModel;
import com.summer.tech.springmvc.utils.PhoneNumberEditor;

@Controller
@RequestMapping("/model")
public class ModeAttributeController {

	@RequestMapping("/test1.htm")
	public String test1(@ModelAttribute("user") UserModel user) {

		return "model";
	}

	@ModelAttribute("cityList")
	public List<String> cityList() {
		System.out.println("citylist");
		return Arrays.asList("北京", "山东");
	}

	@ModelAttribute("user")
	public UserModel getUser(
			@RequestParam(value = "username", defaultValue = "") String username) {
		// TODO 去数据库根据用户名查找用户对象
		System.out.println("username:" + username);
		UserModel user = new UserModel();
		user.setUsername("zhang");
		user.setPassword("123456");
		return user;
	}

	@InitBinder  
	//此处的参数也可以是ServletRequestDataBinder类型  
	public void initBinder(WebDataBinder binder) throws Exception {  
	    //注册自定义的属性编辑器  
	    //1、日期  
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    CustomDateEditor dateEditor = new CustomDateEditor(df, true);  
	    //表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换  
	    binder.registerCustomEditor(Date.class, dateEditor);      
	    //自定义的电话号码编辑器(和一样)  
	    binder.registerCustomEditor(PhoneNumberModel.class, new PhoneNumberEditor());  
	}   
}
