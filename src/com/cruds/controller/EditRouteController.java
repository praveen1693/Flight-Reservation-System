package com.cruds.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cruds.entity.Flight;
import com.cruds.entity.Route;
import com.cruds.service.FrsService;

@Controller
public class EditRouteController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/editRoute", method=RequestMethod.GET)
	public String showFlightDetails(ModelMap model,HttpSession session,RedirectAttributes redirectAttributes)
	{
		String editRouteId = (String) session.getAttribute("editRt");
		redirectAttributes.addAttribute("RouteID", editRouteId);
		System.out.println("Selected RouteId : " + editRouteId);
		model.addAttribute("ROUTE_DATA", frsService.getRouteData(editRouteId));
		model.addAttribute("command", new Route());
		return "editRoute";
	}
	
	@RequestMapping(value="/editRoute", method=RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute("route")Route rt,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		String status = frsService.editRoute(rt);
		System.out.println(rt.getRouteId() + " : " + rt.getSource() + " : " + rt.getDestination() + " : " + rt.getDistance() + " : " + rt.getFare());
		session.setAttribute("ROUTE_ID",rt.getRouteId());
		if(status.equals("SUCCESS"))
		{
			redirectAttributes.addAttribute("SUCCESS", "Route details added successfully");
			return "redirect:viewRoute.html";
		}
		else if(status.equals("FAIL"))
		{
			redirectAttributes.addAttribute("FAIL", "Failed to add Route details");
			return "redirect:viewRoute.html";
		}
		else
		{
			redirectAttributes.addAttribute("ERROR", "Error while adding Route details");
			return "redirect:viewRoute.html";
		}
		
	}
}
