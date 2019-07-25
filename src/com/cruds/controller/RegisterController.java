package com.cruds.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cruds.entity.User;
import com.cruds.service.FrsService;

@Controller
public class RegisterController {
	
	@Autowired
	private FrsService frsService;

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegForm(ModelMap model)
	{
		model.addAttribute("command", new User());
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute("user")User u,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		String userid = frsService.create(u);
		
		System.out.println(u.getUserId() + " : " + u.getFirstName() + " : " + u.getLastName() + " : " + u.getDOB() + " : " + u.getGender() + " : " + u.getUserType() + " : " +
				u.getStreet() + " : " + u.getLocation() + " : " + u.getCity() + " : " + u.getState() + " : " + u.getPincode() + " : " + u.getPhNo() + " : " +
				u.getEmailId() + " : " + u.getPassword());
		//session.setAttribute("USER_ID", u.getUserId());
		redirectAttributes.addAttribute("SUCCESS", "Registration Successful");
		redirectAttributes.addAttribute("USERID", userid);
		return "redirect:register.html";
		//return userid;
	}
}
