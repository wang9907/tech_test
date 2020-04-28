package com.summer.tech.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.summer.tech.springmvc.entity.UserModel;
import com.summer.tech.springmvc.utils.UserModelValidator;

@Controller
public class RegisterSimpleFormController {
	private UserModelValidator validator = new UserModelValidator();

	/*@ModelAttribute("user")
	// ① 暴露表单引用对象为模型数据
	public UserModel getUser() {
		return new UserModel();
	}*/

	@RequestMapping(value = "/validator", method = RequestMethod.GET)
	public String showRegisterForm() { // ② 表单展示
		return "register";
	}

	@RequestMapping(value = "/validator", method = RequestMethod.POST)
	public String submitForm(UserModel user, Errors errors) {
		// ③ 表单提交
		validator.validate(user, errors); // 1调用UserModelValidator的validate方法进行验证
		if (errors.hasErrors()) { // 2如果有错误再回到表单展示页面
			return showRegisterForm();
		}
		return "success";
	}
}