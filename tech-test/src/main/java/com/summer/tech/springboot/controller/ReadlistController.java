package com.summer.tech.springboot.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
@ConfigurationProperties("amazon")
public class ReadlistController {

	private String associateId;
	
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	public ReadlistController() {
		
	}

	@RequestMapping(value="getassId",method=RequestMethod.GET)
	public String getassId(HttpServletRequest request,HttpServletResponse response){
		try {
			response.getWriter().println(associateId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
