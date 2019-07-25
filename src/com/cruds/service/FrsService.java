package com.cruds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruds.db.CredentialsDAO;
import com.cruds.db.FrsDAO;
import com.cruds.db.RegisterDAO;
import com.cruds.entity.Credentials;
import com.cruds.entity.Flight;
import com.cruds.entity.Passenger;
import com.cruds.entity.Reservation;
import com.cruds.entity.Route;
import com.cruds.entity.Schedule;
import com.cruds.entity.User;

@Service
public class FrsService {
	@Autowired
	private FrsDAO frsDAO;
	
	public String create(User u)
	{
		return frsDAO.create(u);
	}
	
	public String loginCheck(Credentials c)
	{
		String c1 = frsDAO.loginCheck(c);
		return c1;
	}
	
	public String reserveTicket(Reservation r)
	{
		return frsDAO.reserveTicket(r);
	}
	
	public String cancelTicket(String str)
	{
		return frsDAO.cancelTicket(str);
	}
	
	public String addPassenger(Passenger passenger)
	{
		return frsDAO.addPassenger(passenger);
	}
	
	public String addFlight(Flight flight)
	{
		return frsDAO.addFlight(flight);
	}
	
	public String editFlight(Flight flight)
	{
		return frsDAO.editFlight(flight);
	}
	
	public String delFlight(String str)
	{
		return frsDAO.delFlight(str);
	}
	
	public String addRoute(Route route)
	{
		return frsDAO.addRoute(route);
	}
	
	public String delRoute(String str)
	{
		return frsDAO.delRoute(str);
	}
	
	public String editRoute(Route route)
	{
		return frsDAO.editRoute(route);
	}
	
	public String addSchedule(Schedule schedule)
	{
		return frsDAO.addSchedule(schedule);
	}
	
	public String delSchedule(String str)
	{
		return frsDAO.delSchedule(str);
	}
	
	public String editSchedule(Schedule schedule)
	{
		return frsDAO.editSchedule(schedule);
	}
	
	public List<Flight> getAllFlight() 
	{
		return frsDAO.getAllFlight();
	}
	
	public List<Flight> getFlightData(String str) 
	{
		return frsDAO.getFlightData(str);
	}
	
	public List<Route> getRouteData(String str) 
	{
		return frsDAO.getRouteData(str);
	}
	
	public List<Schedule> getScheduleData(String str) 
	{
		return frsDAO.getScheduleData(str);
	}
	
	public List<Route> getAllRoute() 
	{
		return frsDAO.getAllRoute();
	}
	
	public List<Schedule> getAllSchedule() 
	{
		return frsDAO.getAllSchedule();
	}
	
	public List<Reservation> getReservationData(String str) 
	{
		return frsDAO.getReservationData(str);
	}
	
	public List<Passenger> getPassengerData() 
	{
		return frsDAO.getPassengerData();
	}
	
	public String changePwd(String userId, String curPass, String newPass)
	{
		return frsDAO.changePwd(userId, curPass, newPass);
	}
	
}
