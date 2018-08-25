package com.summer.tech.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/parameter1")
// ①处理器的通用映射前缀
public class RequestParameterController1 {
	// ②进行类级别的@RequestMapping窄化
	@RequestMapping(params = "create", method = RequestMethod.GET)
	public String showForm() {
		System.out.println("===============showForm");
		return "parameter/create";
	}

	// ③进行类级别的@RequestMapping窄化
	@RequestMapping(params = "create", method = RequestMethod.POST)
	public String submit() {
		System.out.println("================submit");
		return "redirect:/success";
	}
}