package com.summer.tech.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customers/**")
// ①处理器的通用映射前缀
public class RequestMethodController {
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	// ②类级别的@RequestMapping窄化
	public String showForm() {
		System.out.println("===============GET");
		return "customer/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	// ③类级别的@RequestMapping窄化
	public String submit() {
		System.out.println("================POST");
		return "redirect:/success";
	}
}