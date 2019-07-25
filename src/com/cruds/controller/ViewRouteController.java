package com.cruds.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cruds.entity.Flight;
import com.cruds.service.FrsService;

@Controller
public class ViewRouteController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/viewRoute", method=RequestMethod.GET)
	public String showFlightDetails(ModelMap model)
	{
		model.addAttribute("ROUTE_LIST", frsService.getAllRoute());
		model.addAttribute("command", new Flight());
		return "viewRoute";
		
	}
	
	@RequestMapping(value="/viewRoute", method=RequestMethod.POST)
	public String handleFormSubmit(@RequestParam("delRouteId") String selRouteId,@RequestParam("ACTION") String ACTION ,ModelMap model,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		System.out.println("Table IndeX RouteID : " + selRouteId);
		System.out.println("Action Value : " + ACTION);
		
		if("DELETE".equals(ACTION))
		{
			String status = frsService.delRoute(selRouteId);
			if(status.equals("SUCCESS"))
			{
				redirectAttributes.addAttribute("SUCCESS", "Route details deleted successfully");
				return "redirect:viewRoute.html";
			}
			else if(status.equals("FAIL"))
			{
				redirectAttributes.addAttribute("FAIL", "Failed to delete Route details");
				return "redirect:viewRoute.html";
			}
		}
		else if("EDIT".equals(ACTION))
		{
			session.setAttribute("editRt", selRouteId);
			return "redirect:editRoute.html";
		}
		
		
		model.addAttribute("ROUTE_LIST", frsService.getAllRoute());
		return "viewRoute";
	}
}
