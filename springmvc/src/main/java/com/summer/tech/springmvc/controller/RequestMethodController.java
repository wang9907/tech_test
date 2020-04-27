package com.summer.tech.springmvc.controller;

import com.summer.tech.springmvc.entity.PhoneNumberModel;
import com.summer.tech.springmvc.utils.PhoneNumber;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

	@RequestMapping(value = "/format", method = RequestMethod.GET)
	public String format(@PhoneNumber @RequestParam(name="phoneNumber") PhoneNumberModel phoneNumber, @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name="date") Date date) {
		System.out.println(phoneNumber);
		System.out.println(date);
		return "test";
	}

}