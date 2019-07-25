package com.cruds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminMenuController {
	
	@RequestMapping(value="/adminMenu", method=RequestMethod.GET)
	public String showLoginForm()
	{
		return "adminMenu";
	}
	
}
