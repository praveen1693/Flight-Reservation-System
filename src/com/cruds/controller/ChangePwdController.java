package com.cruds.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cruds.entity.Flight;
import com.cruds.entity.User;
import com.cruds.service.FrsService;

@Controller
public class ChangePwdController {
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/changePwd", method=RequestMethod.GET)
	public String showLoginForm(ModelMap model)
	{
		model.addAttribute("command", new User());
		return "changePwd";
	}
	
	@RequestMapping(value="/changePwd", method=RequestMethod.POST)
	public String changePwdForm(@RequestParam("password") String password, @RequestParam("newPass") String newPass, @RequestParam("confirmPass") String confirmPass, HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		String userId = (String) session.getAttribute("USER_ID");
		System.out.println(userId + " : " + password + " : " + newPass + " : " + confirmPass);
		String status = null;
		if(newPass.equals(confirmPass))
		{
			status = frsService.changePwd(userId, password, newPass);
		}
		else if(password.equals(null) || newPass.equals(null) || confirmPass.equals(null) )
		{
			redirectAttributes.addAttribute("INVALID", "Invalid inputs");
			
		}
		if(status.equals("SUCCESS"))
		{
			redirectAttributes.addAttribute("SUCCESS", "Password changed successfully");
		}
		else if(status.equals("FAIL"))
		{
			redirectAttributes.addAttribute("FAIL", "Failed to change the password");
		}
		return "redirect:changePwd.html";
	}
}
