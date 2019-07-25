package com.cruds.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cruds.entity.Reservation;
import com.cruds.entity.User;
import com.cruds.service.FrsService;

@Controller
public class BookTicketController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/bookTicket", method=RequestMethod.GET)
	public String showLoginForm(ModelMap model)
	{
		model.addAttribute("command", new Reservation());
		return "bookTicket";
	}
	
	@RequestMapping(value="/bookTicket", method=RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute("reservation")Reservation reserve,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		String userid = (String) session.getAttribute("USER_ID");
		reserve.setUserId(userid);
		Calendar cal = Calendar.getInstance(); // generating current date
		java.sql.Date currentDate = new java.sql.Date(cal.getTime().getTime());
		reserve.setBookingDate(currentDate);
		reserve.setNoOfSeats(1);
		
		String reservationId = frsService.reserveTicket(reserve);
		
		System.out.println(reserve.getReservationId() + " : " + reserve.getScheduleId() + " : " + reserve.getUserId() + " : " + reserve.getReservationType() + " : "
							+ reserve.getBookingDate() + " : " + reserve.getJourneyDate() + " : " + reserve.getNoOfSeats() + " : " + reserve.getTotalFare() + " : "
							+ reserve.getBookingStatus());
		//session.setAttribute("USER_ID", u.getUserId());
		redirectAttributes.addAttribute("SUCCESS", "Reserved a ticket Successfully");
		redirectAttributes.addAttribute("RESERVATIONID", reservationId);
		session.setAttribute("RESERVATIONID", reservationId);
		return "redirect:bookTicket.html";
	}
}
