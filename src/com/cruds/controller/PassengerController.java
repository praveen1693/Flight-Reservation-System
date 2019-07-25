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
import com.cruds.entity.Passenger;
import com.cruds.service.FrsService;

@Controller
public class PassengerController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/passenger", method=RequestMethod.GET)
	public String showLoginForm(ModelMap model)
	{
		model.addAttribute("command", new Passenger());
		return "passenger";
	}
	
	@RequestMapping(value="/passenger", method=RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute("passenger")Passenger passenger,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		String reservationId = (String) session.getAttribute("RESERVATIONID");
		passenger.setReservationId(reservationId);
		
		String status = frsService.addPassenger(passenger);
		System.out.println(passenger.getReservationId() + " : " + passenger.getName() + " : " + passenger.getGender() + "  : " + passenger.getAge() + " : " + passenger.getSeatNo());
		//session.setAttribute("FLIGHT_ID",ft.getFlightId());
		if(status.equals("SUCCESS"))
		{
			redirectAttributes.addAttribute("SUCCESS", "Passenger details added successfully");
			return "redirect:ViewTicket.html";
		}
		else if(status.equals("NOSEAT"))
		{
			redirectAttributes.addAttribute("NOSEAT", "Seat no is taken! Try again");
			return "redirect:passenger.html";
		}
		else if(status.equals("FAIL"))
		{
			redirectAttributes.addAttribute("FAIL", "Failed to add Passenger details");
			return "redirect:passenger.html";
		}
		else if(status.equals("ERROR"))
		{
			redirectAttributes.addAttribute("ERROR", "Error while adding Passenger details");
			return "redirect:passenger.html";
		}
		return "redirect:passenger.html";
	}
}
