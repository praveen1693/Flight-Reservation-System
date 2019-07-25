package com.cruds.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cruds.entity.Schedule;
import com.cruds.service.FrsService;

@Controller
public class ViewScheduleController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/viewSchedule", method=RequestMethod.GET)
	public String showFlightDetails(ModelMap model)
	{
		model.addAttribute("SCHEDULE_LIST", frsService.getAllSchedule());
		model.addAttribute("command", new Schedule());
		return "viewSchedule";
		
	}
	
	@RequestMapping(value="/viewSchedule", method=RequestMethod.POST)
	public String handleFormSubmit(@RequestParam("delScheduleId") String selScheduleId,@RequestParam("ACTION") String ACTION ,ModelMap model,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		System.out.println("Table IndeX ScheduleID : " + selScheduleId);
		System.out.println("Action Value : " + ACTION);
		
		if("DELETE".equals(ACTION))
		{
			String status = frsService.delSchedule(selScheduleId);
			if(status.equals("SUCCESS"))
			{
				redirectAttributes.addAttribute("SUCCESS", "Schedule details deleted successfully");
				return "redirect:viewSchedule.html";
			}
			else if(status.equals("FAIL"))
			{
				redirectAttributes.addAttribute("FAIL", "Failed to delete Schedule details");
				return "redirect:viewSchedule.html";
			}
		}
		else if("EDIT".equals(ACTION))
		{
			session.setAttribute("editSt", selScheduleId);
			return "redirect:editSchedule.html";
		}
		
		model.addAttribute("FLIGHT_LIST", frsService.getAllFlight());
		return "viewSchedule";
	}
}
