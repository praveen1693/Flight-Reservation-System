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
import com.cruds.entity.Schedule;
import com.cruds.service.FrsService;

@Controller
public class AddScheduleController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/addSchedule", method=RequestMethod.GET)
	public String showFlightDetails(ModelMap model)
	{
		model.addAttribute("FLIGHT_LIST", frsService.getAllFlight());
		model.addAttribute("ROUTE_LIST", frsService.getAllRoute());
		model.addAttribute("command", new Schedule());
		return "addSchedule";
		
	}
	
	@RequestMapping(value="/addSchedule", method=RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute("schedule")Schedule s,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		String status = frsService.addSchedule(s);
		System.out.println(s.getScheduleId() + " : " + s.getFlightId() + " : " + s.getRouteId() + " : " + s.getTravelDuration() + " : " + s.getAvailableDays() + " : " + s.getDepartureTime());
		session.setAttribute("SCHEDULE_ID",s.getScheduleId());
		if(status.equals("SUCCESS"))
		{
			redirectAttributes.addAttribute("SUCCESS", "Schedule details added successfully");
			redirectAttributes.addAttribute("SCHEDULEID", s.getScheduleId());
		}
		else if(status.equals("FAIL"))
		{
			redirectAttributes.addAttribute("FAIL", "Failed to add Schedule details");
			
		}
		else
		{
			redirectAttributes.addAttribute("ERROR", "Error while adding Schedule details");
			
		}
		return "redirect:addSchedule.html";
	}
}
