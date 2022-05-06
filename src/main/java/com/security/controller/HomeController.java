package com.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HomeController {

	

   @RequestMapping("/test")
	public String index()
	{
		return "rest of the code";
	}

	
}
