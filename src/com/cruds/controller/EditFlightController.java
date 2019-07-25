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
public class EditFlightController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/editFlight", method=RequestMethod.GET)
	public String showFlightDetails(ModelMap model,HttpSession session,RedirectAttributes redirectAttributes)
	{
		String editFlightId = (String) session.getAttribute("editFt");
		redirectAttributes.addAttribute("FlightID", editFlightId);
		System.out.println("Selected FlightId : " + editFlightId);
		model.addAttribute("FLIGHT_DATA", frsService.getFlightData(editFlightId));
		model.addAttribute("command", new Flight());
		return "editFlight";
		
	}
	
	@RequestMapping(value="/editFlight", method=RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute("flight")Flight ft,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		String status = frsService.editFlight(ft);
		System.out.println(ft.getFlightId() + " : " + ft.getFlightName() + " : " + ft.getSeatingCap() + " : " + ft.getReserveCap());
		session.setAttribute("FLIGHT_ID",ft.getFlightId());
		if(status.equals("SUCCESS"))
		{
			redirectAttributes.addAttribute("SUCCESS", "Flight details added successfully");
			return "redirect:viewFlight.html";
		}
		else if(status.equals("FAIL"))
		{
			redirectAttributes.addAttribute("FAIL", "Failed to add Flight details");
			return "redirect:editFlight.html";
		}
		else
		{
			redirectAttributes.addAttribute("ERROR", "Error while adding Flight details");
			return "redirect:editFlight.html";
		}
		
	}
}
