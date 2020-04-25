package com.summer.tech.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class RequestMethodController {

	@RequestMapping(value = "/cookie", method = RequestMethod.GET)
	public String cookie(@CookieValue("JSESSIONID") String sessionId, @RequestParam(value = "name") String username , Model model) {
		System.out.println("sessionId:"+sessionId);
		model.addAttribute("name",username);
		return "test";
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public String getUser(@PathVariable(value = "userId") String userId) {
		System.out.println("userId:"+userId);
		return "test";
	}

	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String header(@RequestHeader(value = "User-Agent") String userAgent,@RequestHeader(value = "Accept") String[] accepts) {
		System.out.println("userAgent:"+userAgent);
		System.out.println("Accept:"+accepts);
		return "test";
	}

}