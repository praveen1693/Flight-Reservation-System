package com.cruds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerMenuController {
	
	@RequestMapping(value="/customerMenu", method=RequestMethod.GET)
	public String showLoginForm()
	{
		return "customerMenu";
	}
	
}
