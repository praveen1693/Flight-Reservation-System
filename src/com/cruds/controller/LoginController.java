package com.cruds.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cruds.entity.Credentials;
import com.cruds.entity.User;
import com.cruds.service.FrsService;

@Controller
public class LoginController {
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginForm()
	{
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("credentials")Credentials c, HttpSession session,
								RedirectAttributes redirectAttributes)
	{
		session.setAttribute("USER_ID", c.getUserId());
		String name = frsService.loginCheck(c);
		c.setUserType(name);
		session.setAttribute("userType", name);
		System.out.println(session.getAttribute("USER_ID"));
		System.out.println(session.getAttribute("userType"));
		System.out.println(name);
		if(name.equals("A"))
		{
			
			return "redirect:adminMenu.html";
		}
		else if(name.equals("C"))
		{
			
			return "redirect:customerMenu.html";
		}
		else if(c.getUserId().equals(null) && c.getPassword().equals(null)) 
		{
			redirectAttributes.addAttribute("ERROR", "Invalid Credentials");
			return "redirect:login.html";
		}
		else
		{
			redirectAttributes.addAttribute("ERROR", "Invalid Credentials");
			return "redirect:login.html";
		}
		/*if(uid.equals("abc") && pwd.equals("123"))
		{
			session.setAttribute("USER_ID", uid);
			return "redirect:adminMenu.html";
		}
		else
		{
			redirectAttributes.addAttribute("ERROR", "Invalid Credentials");
			return "redirect:login.html";
		}*/
	}
}
