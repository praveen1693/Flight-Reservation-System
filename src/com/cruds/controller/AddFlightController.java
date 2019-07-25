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
import com.cruds.service.FrsService;

@Controller
public class AddFlightController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/addFlight", method=RequestMethod.GET)
	public String showLoginForm(ModelMap model)
	{
		model.addAttribute("command", new Flight());
		return "addFlight";
	}
	
	@RequestMapping(value="/addFlight", method=RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute("flight")Flight ft,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		String status = frsService.addFlight(ft);
		System.out.println(ft.getFlightId() + " : " + ft.getFlightName() + " : " + ft.getSeatingCap() + " : " + ft.getReserveCap());
		session.setAttribute("FLIGHT_ID",ft.getFlightId());
		if(status.equals("SUCCESS"))
		{
			redirectAttributes.addAttribute("SUCCESS", "Flight details added successfully");
			redirectAttributes.addAttribute("FLIGHTID", ft.getFlightId());
			return "redirect:addFlight.html";
		}
		else if(status.equals("FAIL"))
		{
			redirectAttributes.addAttribute("FAIL", "Failed to add Flight details");
			return "redirect:addFlight.html";
		}
		else
		{
			redirectAttributes.addAttribute("ERROR", "Error while adding Flight details");
			return "redirect:addFlight.html";
		}
		
	}
}
