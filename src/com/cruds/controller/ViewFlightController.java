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
import com.cruds.entity.Schedule;
import com.cruds.service.FrsService;

@Controller
public class ViewFlightController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/viewFlight", method=RequestMethod.GET)
	public String showFlightDetails(ModelMap model)
	{
		model.addAttribute("FLIGHT_LIST", frsService.getAllFlight());
		model.addAttribute("command", new Flight());
		return "viewFlight";
		
	}
	
	@RequestMapping(value="/viewFlight", method=RequestMethod.POST)
	public String handleFormSubmit(@RequestParam("delFlightId") String selFlightId,@RequestParam("ACTION") String ACTION ,ModelMap model,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		System.out.println("Table IndeX FlightID : " + selFlightId);
		System.out.println("Action Value : " + ACTION);
		
		if("DELETE".equals(ACTION))
		{
			String status = frsService.delFlight(selFlightId);
			if(status.equals("SUCCESS"))
			{
				redirectAttributes.addAttribute("SUCCESS", "Flight details deleted successfully");
				return "redirect:viewFlight.html";
			}
			else if(status.equals("FAIL"))
			{
				redirectAttributes.addAttribute("FAIL", "Failed to delete Flight details");
				return "redirect:viewFlight.html";
			}
		}
		else if("EDIT".equals(ACTION))
		{
			session.setAttribute("editFt", selFlightId);
			return "redirect:editFlight.html";
		}
		
		
		model.addAttribute("FLIGHT_LIST", frsService.getAllFlight());
		return "viewFlight";
	}
}
