package com.cruds.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cruds.entity.Reservation;
import com.cruds.service.FrsService;

@Controller
public class ViewTicketController {
	
	@Autowired
	private FrsService frsService;
	
	@RequestMapping(value="/viewTicket", method=RequestMethod.GET)
	public String showFlightDetails(ModelMap model,HttpSession session)
	{
		String userId = (String) session.getAttribute("USER_ID");
		System.out.println("Session userId : " + userId);
		model.addAttribute("TICKET_LIST", frsService.getReservationData(userId));
		
		model.addAttribute("command", new Reservation());
		return "viewTicket";
		
	}
	
	@RequestMapping(value="/viewTicket", method=RequestMethod.POST)
	public String handleFormSubmit(@RequestParam("delReservationId") String selReservationId,@RequestParam("ACTION") String ACTION,@RequestParam("cancel") String cancel ,ModelMap model,HttpSession session,
			RedirectAttributes redirectAttributes)
	{
		System.out.println("Table IndeX ReservationId : " + selReservationId);
		System.out.println("Cancel Value : " + cancel);
		
		if("CONFIRM".equals(cancel) && "CANCEL".equals(ACTION))
		{
			String status = frsService.cancelTicket(selReservationId);
			if(status.equals("SUCCESS"))
			{
				redirectAttributes.addAttribute("SUCCESS", "Your Flight Ticket has been cancelled successfully");
				return "redirect:viewTicket.html";
			}
			else if(status.equals("FAIL"))
			{
				redirectAttributes.addAttribute("FAIL", "Failed to cancel your Flight ticket");
				return "redirect:viewTicket.html";
			}
		}
		/*else if("DETAILS".equals(ACTION))
		{
			session.setAttribute("ReservationId", selReservationId);
			return "redirect:passengerDetails.html";
		}*/
		else
		{
			redirectAttributes.addAttribute("FAIL", "Operation Failed, Try again");
			return "redirect:viewTicket.html";
		}
		
		return "viewTicket";
	}
}
