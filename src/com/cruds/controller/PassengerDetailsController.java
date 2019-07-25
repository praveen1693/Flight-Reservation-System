package com.cruds.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cruds.entity.Passenger;
import com.cruds.service.FrsService;

@Controller
public class PassengerDetailsController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/passengerDetails", method=RequestMethod.GET)
	public String showFlightDetails(ModelMap model,HttpSession session)
	{
		//String reservationId = (String) session.getAttribute("ReservationId");
		model.addAttribute("PASSENGER_LIST", frsService.getPassengerData());
		//model.addAttribute("PASSENGERTICKET_LIST", frsService.getPassengerScheduleData(reservationId));
		model.addAttribute("command", new Passenger());
		return "passengerDetails";
		
	}
	
}
