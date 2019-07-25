package com.cruds.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cruds.entity.Route;
import com.cruds.service.FrsService;

@Controller
public class AddRouteController {
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/addRoute", method=RequestMethod.GET)
	public String showLoginForm(ModelMap model)
	{
		model.addAttribute("command", new Route());
		return "addRoute";
	}

	@RequestMapping(value="/addRoute", method=RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute("route")Route rt,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		String status = frsService.addRoute(rt);
		System.out.println(rt.getRouteId() + " : " + rt.getSource() + " : " + rt.getDestination() + " : " + rt.getDistance() + " : " + rt.getFare());
		session.setAttribute("ROUTE_ID",rt.getRouteId());
		if(status.equals("SUCCESS"))
		{
			redirectAttributes.addAttribute("SUCCESS", "Route details added successfully");
			redirectAttributes.addAttribute("ROUTEID", rt.getRouteId());
		}
		else if(status.equals("FAIL"))
		{
			redirectAttributes.addAttribute("FAIL", "Failed to add Route details");
			
		}
		else
		{
			redirectAttributes.addAttribute("ERROR", "Error while adding Route details");
			
		}
		return "redirect:addRoute.html";
	}

}
