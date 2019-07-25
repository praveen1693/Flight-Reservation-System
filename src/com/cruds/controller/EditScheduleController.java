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
import com.cruds.entity.Schedule;
import com.cruds.service.FrsService;

@Controller
public class EditScheduleController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/editSchedule", method=RequestMethod.GET)
	public String showFlightDetails(ModelMap model,HttpSession session,RedirectAttributes redirectAttributes)
	{
		String editScheduleId = (String) session.getAttribute("editSt");
		redirectAttributes.addAttribute("ScheduletID", editScheduleId);
		System.out.println("Selected FlightId : " + editScheduleId);
		model.addAttribute("SCHEDULE_DATA", frsService.getScheduleData(editScheduleId));
		model.addAttribute("command", new Schedule());
		return "editSchedule";
		
	}
	
	@RequestMapping(value="/editSchedule", method=RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute("flight")Schedule st,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		String status = frsService.editSchedule(st);
		System.out.println(st.getScheduleId() + " : " + st.getFlightId() + " : " + st.getRouteId() + " : " + st.getTravelDuration() + " : " + st.getAvailableDays() + " : " + st.getDepartureTime());
		session.setAttribute("FLIGHT_ID", st.getFlightId());
		if(status.equals("SUCCESS"))
		{
			redirectAttributes.addAttribute("SUCCESS", "Schedule details added successfully");
			return "redirect:viewSchedule.html";
		}
		else if(status.equals("FAIL"))
		{
			redirectAttributes.addAttribute("FAIL", "Failed to add Schedule details");
			return "redirect:editSchedule.html";
		}
		else
		{
			redirectAttributes.addAttribute("ERROR", "Error while adding Schedule details");
			return "redirect:editSchedule.html";
		}
		
	}
}
