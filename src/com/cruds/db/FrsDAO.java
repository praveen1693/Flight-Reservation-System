package com.cruds.db;

import java.util.List;

import com.cruds.entity.Credentials;
import com.cruds.entity.Flight;
import com.cruds.entity.Passenger;
import com.cruds.entity.Reservation;
import com.cruds.entity.Route;
import com.cruds.entity.Schedule;
import com.cruds.entity.User;

public interface FrsDAO {

	public String loginCheck(Credentials c);
	
	public String create(User U);
	
	public String reserveTicket(Reservation R);
	
	public String addPassenger(Passenger passenger);
	
	public String cancelTicket(String str);
	
	public String addFlight(Flight flight);
	
	public String editFlight(Flight flight);
	
	public String delFlight(String str);
	
	public String addRoute(Route route);
	
	public String editRoute(Route route);
	
	public String delRoute(String str);
	
	public String addSchedule(Schedule schedule);
	
	public String delSchedule(String str);
	
	public String editSchedule(Schedule schedule);
	
	public String changePwd(String str1, String str2, String str3);
	
	public List<Flight> getAllFlight();
	
	public List<Flight> getFlightData(String str);
	
	public List<Route> getRouteData(String str);
	
	public List<Schedule> getAllSchedule();
	
	public List<Schedule> getScheduleData(String str);
	
	public List<Route> getAllRoute();
	
	public List<Reservation> getReservationData(String str);
	
	public List<Passenger> getPassengerData();
	
	//public List<Passenger> getPassengerScheduleData(String str);
}
