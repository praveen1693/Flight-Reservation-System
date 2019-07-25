package com.cruds.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cruds.entity.Credentials;
import com.cruds.entity.Flight;
import com.cruds.entity.Passenger;
import com.cruds.entity.Reservation;
import com.cruds.entity.Route;
import com.cruds.entity.Schedule;
import com.cruds.entity.User;

@Repository
public class FrsDAOImpl implements FrsDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	UtilDAO utilDAO;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//Login
	@Override
	public String loginCheck(Credentials c) {
		String sql = "select userType from loginCheck where userId LIKE (?) AND password LIKE (?)";
		String userType = "";
		try {
			userType = (String)getJdbcTemplate().queryForObject(
					sql, new Object[] {c.getUserId(),c.getPassword()  }, String.class);
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userType;
	}
	
	//Registration
	@Override
	public String create(User user) {
		
		//String sql3 = "select next value for frs_seq_userid";
		//String seqUserId = (String)getJdbcTemplate().queryForObject(
		//sql3, new Object[] {}, String.class);
		String subStr = user.getFirstName().substring(0, 2);
		String dummyUserID = subStr+utilDAO.getNextValForSeq(DBStringConstants.SEQ_FRS_USERID);
		user.setUserId(dummyUserID);
		
		String sql1 = "insert into logincheck values(?,?,?,?)";
		jdbcTemplate.update(sql1, new Object[]{user.getUserId(),user.getPassword(),user.getUserType(),0});
		
		String sql2 = "insert into registration values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql2, new Object[]{user.getUserId(),user.getFirstName(),user.getLastName(),user.getDOB(),
							user.getGender(),user.getStreet(),user.getLocation(),user.getCity(),user.getState(),user.getPincode(),
							user.getPhNo(),user.getEmailId(),user.getPassword()});
		return dummyUserID;
	}
	
	//adding flights
	@Override
	public String addFlight(Flight flight) {
		
		try {
			/*String sql1 = "select next value for frs_seq_flightid";
			String seqFlightId = (String)getJdbcTemplate().queryForObject(
					sql1, new Object[] {}, String.class);*/
			String subStr = flight.getFlightName().substring(0, 2);
			String dummyFlightID = subStr+utilDAO.getNextValForSeq(DBStringConstants.SEQ_FRS_FLIGHTID);
			flight.setFlightId(dummyFlightID);
			
			String sql2 = "insert into flight values(?,?,?,?)";
			jdbcTemplate.update(sql2, new Object[]{flight.getFlightId(),flight.getFlightName(),flight.getSeatingCap(),flight.getReserveCap()});
			return "SUCCESS";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	//view flights by admin
	@Override
	public List<Flight> getAllFlight() {
		String sql = "select flightId, flightName, seatingCap, reservationCap from flight";
		List<Flight> flightList = jdbcTemplate.query(sql, new FlightRowMapper());
		return flightList;
	}
	class FlightRowMapper implements RowMapper<Flight>
	{

		@Override
		public Flight mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return new Flight(rs.getString("flightId"),rs.getString("flightName"),rs.getInt("seatingCap"),rs.getInt("reservationCap"));
		}	
	}
	
	//change password
	@Override
	public String changePwd(String userId, String curPass, String newPass) {
		
		
			try {
				String sql1 = "update logincheck set password=(?) where userId LIKE (?) AND password LIKE (?)";
				jdbcTemplate.update(sql1, new Object []{newPass,userId,curPass});
				
				String sql2 = "update registration set password=(?) where userId LIKE (?) AND password LIKE (?)";
				jdbcTemplate.update(sql2, new Object []{newPass,userId,curPass});
				return "SUCCESS";
			} catch (DataAccessException e) {
				e.printStackTrace();
				return "FAIL";
			}
	}
	
	//Adding Routes
	@Override
	public String addRoute(Route route) {
		try {
			/*String sql1 = "select next value for frs_seq_routeid";
			String seqRouteId = (String)getJdbcTemplate().queryForObject(
					sql1, new Object[] {}, String.class);*/
			String subStr1 = route.getSource().substring(0, 2);
			String subStr2 = route.getDestination().substring(0, 2);
			String dummyRouteID = subStr1+subStr2+utilDAO.getNextValForSeq(DBStringConstants.SEQ_FRS_ROUTEID);
			route.setRouteId(dummyRouteID);
			
			String sql2 = "insert into route values(?,?,?,?,?)";
			jdbcTemplate.update(sql2, new Object[]{route.getRouteId(),route.getSource(),route.getDestination(),route.getDistance(),route.getFare()});
			return "SUCCESS";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	//view Route
	@Override
	public List<Route> getAllRoute() {
		String sql = "select routeId, source, destination, distance, fare from route";
		List<Route> routeList = jdbcTemplate.query(sql, new RouteRowMapper());
		return routeList;
	}
	class RouteRowMapper implements RowMapper<Route>
	{

		@Override
		public Route mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return new Route(rs.getString("routeId"),rs.getString("source"),rs.getString("destination"),rs.getInt("distance"),rs.getInt("fare"));
		}
		
	}
	
	//Schedule
	@Override
	public String addSchedule(Schedule schedule) {
		try {
			/*String sql1 = "select next value for frs_seq_scheduleid";
			String seqScheduleId = (String)getJdbcTemplate().queryForObject(
					sql1, new Object[] {}, String.class);*/
			String subStr1 = schedule.getRouteId().substring(0, 4);
			String dummyScheduleID = subStr1+utilDAO.getNextValForSeq(DBStringConstants.SEQ_FRS_SCHEDULEID);
			schedule.setScheduleId(dummyScheduleID);
			
			String sql2 = "insert into schedule values(?,?,?,?,?,?)";
			jdbcTemplate.update(sql2, new Object[]{schedule.getScheduleId(),schedule.getFlightId(),schedule.getRouteId(),schedule.getTravelDuration(),
								schedule.getAvailableDays(),schedule.getDepartureTime()});
			return "SUCCESS";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	//Schedule List
	@Override
	public List<Schedule> getAllSchedule() {
		String sql = "select scheduleId, flightId, routeId, travelDuration, availableDays, departureTime from schedule";
		List<Schedule> scheduleList = jdbcTemplate.query(sql, new ScheduleRowMapper());
		return scheduleList;
	}
	class ScheduleRowMapper implements RowMapper<Schedule>
	{

		@Override
		public Schedule mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return new Schedule(rs.getString("scheduleId"),rs.getString("flightId"),rs.getString("routeId"),rs.getInt("travelDuration"),
								rs.getString("availableDays"),rs.getString("departureTime"));
		}
	}
	
	//returning flight data based on the flight Id
	@Override
	public List<Flight> getFlightData(String selFlightId) {
		String sql = "select flightId, flightName, seatingCap, reservationCap from flight where flightId=(?)";
		List<Flight> flightData = jdbcTemplate.query(sql, new Object[]{selFlightId}, new FlightDataMapper());
		return flightData;
		
	}
	class FlightDataMapper implements RowMapper<Flight>
	{

		@Override
		public Flight mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return new Flight(rs.getString("flightId"),rs.getString("flightName"),rs.getInt("seatingCap"),rs.getInt("reservationCap"));
		}
	}
	
	//Delete flight
	@Override
	public String delFlight(String delFlight) {
		try {
			String sql = "delete from flight where flightId=(?)"; 
			jdbcTemplate.update(sql, new Object []{delFlight});
			return "SUCCESS";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	//updating flight details
	@Override
	public String editFlight(Flight flight) {
		try {
			String sql = "update flight set flightName=(?), seatingCap=(?), reservationCap=(?) where flightId LIKE (?)";
			jdbcTemplate.update(sql, new Object[]{flight.getFlightName(),flight.getSeatingCap(),flight.getReserveCap(),flight.getFlightId()});
			return "SUCCESS";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	//Delete Route
	@Override
	public String delRoute(String delRoute) {
		try {
			String sql = "delete from route where routeId=(?)"; 
			jdbcTemplate.update(sql, new Object []{delRoute});
			return "SUCCESS";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	//returning route data based on the route Id
	@Override
	public List<Route> getRouteData(String selRouteId) {
		String sql = "select routeId, source, destination, distance, fare from route where routeId=(?)";
		List<Route> routeData = jdbcTemplate.query(sql, new Object[]{selRouteId}, new RouteDataMapper());
		return routeData;
	}
	class RouteDataMapper implements RowMapper<Route>
	{

		@Override
		public Route mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return new Route(rs.getString("routeId"),rs.getString("source"),rs.getString("destination"),rs.getInt("distance"),rs.getInt("fare"));
		}
	}
	
	//Updating Route
	@Override
	public String editRoute(Route route) {
		try {
			String sql = "update route set source=(?), destination=(?), distance=(?), fare=(?) where routeId LIKE (?)";
			jdbcTemplate.update(sql, new Object[]{route.getSource(),route.getDestination(),route.getDistance(),route.getFare(),route.getRouteId()});
			return "SUCCESS";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	//Delete Schedule
	@Override
	public String delSchedule(String delSchedule) {
		try {
			String sql = "delete from schedule where scheduleId=(?)";
			jdbcTemplate.update(sql, new Object []{delSchedule});
			return "SUCCESS";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	//returning route data based on the route Id
	@Override
	public List<Schedule> getScheduleData(String selScheduleId) {
		String sql = "select scheduleId, flightId, routeId, travelDuration, availableDays, departureTime from schedule where scheduleId=(?)";
		List<Schedule> scheduleData = jdbcTemplate.query(sql, new Object[]{selScheduleId}, new ScheduleDataMapper());
		return scheduleData;
	}
	class ScheduleDataMapper implements RowMapper<Schedule>
	{

		@Override
		public Schedule mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return new Schedule(rs.getString("scheduleId"),rs.getString("flightId"),rs.getString("routeId"),rs.getInt("travelDuration"),
								rs.getString("availableDays"),rs.getString("departureTime"));
		}
	}
	
	//Updating Schedule
	@Override
	public String editSchedule(Schedule schedule) {
		try {
			String sql = "update schedule set flightId=(?), routeId=(?), travelDuration=(?), availableDays=(?), departureTime=(?) where scheduleId LIKE (?)";
			jdbcTemplate.update(sql, new Object[]{schedule.getFlightId(),schedule.getRouteId(),schedule.getTravelDuration(),schedule.getAvailableDays(),
								schedule.getDepartureTime(),schedule.getScheduleId()});
			return "SUCCESS";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
	//Reserve Ticket
	@Override
	public String reserveTicket(Reservation reserve) {
		String subStr = reserve.getScheduleId().substring(0, 4);
		String dummyReservationID = subStr+utilDAO.getNextValForSeq(DBStringConstants.SEQ_FRS_RESERVATIONID);
		reserve.setReservationId(dummyReservationID);
		reserve.setBookingStatus(1);
		
		String sql1 = "select routeId from schedule where scheduleId=(?)";//retrieving routeId from schedule
		String routeId = (String)getJdbcTemplate().queryForObject(sql1, new Object[]{reserve.getScheduleId()}, String.class);
		String sql2 = "select fare from route where routeId=(?)";//retrieve fare from route
		String fare = (String)getJdbcTemplate().queryForObject(sql2, new Object[]{routeId}, String.class);
		reserve.setTotalFare(Double.parseDouble(fare));
		
		String sql3 = "insert into reservation values(?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql3, new Object[]{reserve.getReservationId(),reserve.getUserId(),reserve.getScheduleId(),reserve.getReservationType(),
							reserve.getBookingDate(),reserve.getJourneyDate(),reserve.getNoOfSeats(),reserve.getTotalFare(),reserve.getBookingStatus()});
		
		String sql4 = "select flightId from schedule where scheduleId=(?)";//retrieving flightId from schedule
		String flightId = (String)getJdbcTemplate().queryForObject(sql4, new Object[]{reserve.getScheduleId()}, String.class);
		
		String sql5 = "update flight set reservationCap=reservationCap-1 where flightId=(?)";//updating reservation capacity 
		jdbcTemplate.update(sql5, new Object []{flightId});
		return dummyReservationID;
	}

	//Passenger Details
	@Override
	public String addPassenger(Passenger passenger) {
		
		String sql1 = "select scheduleId from reservation where reservationId=(?)";//retrieving scheduleId from reservation
		String scheduleId = (String)getJdbcTemplate().queryForObject(sql1, new Object[]{passenger.getReservationId()}, String.class);
		
		String sql2 = "select flightId from schedule where scheduleId=(?)";//retrieving flightId from schedule
		String flightId = (String)getJdbcTemplate().queryForObject(sql2, new Object[]{scheduleId}, String.class);
		
		String sql3 = "select seatingCap from flight where flightId=(?)";//retrieving max seat no from flight
		String dummySeat = (String)getJdbcTemplate().queryForObject(sql3, new Object[]{flightId}, String.class);
		String userSeat = Integer.toString(passenger.getSeatNo()); 
		int maxSeat = Integer.parseInt(dummySeat);
		if(passenger.getSeatNo() <= maxSeat)
		{
			System.out.println("MAX SEAT : " + passenger.getSeatNo() + " : " + maxSeat);
			String sql4 = "select seatNo from passenger";
			List<String>data = jdbcTemplate.queryForList(sql4,String.class);
			
			for(String d : data)
			{
				if(userSeat.equals(d))
				{
					return "NOSEAT";
				}
			}
			
			String sql5 = "insert into passenger values (?,?,?,?,?)";
			jdbcTemplate.update(sql5, new Object[]{passenger.getReservationId(),passenger.getName(),passenger.getGender(),passenger.getAge(),passenger.getSeatNo()});
			return "SUCCESS";
		}
		return "FAIL";
	}
	
	//Retrieving Reservation Data
	@Override
	public List<Reservation> getReservationData(String selUserId) {
		String bookingSts = "1";
		String sql = "select reservationId, userId, scheduleId, reservationType, bookingDate, journeyDate, noOfSeats, totalFare, bookingStatus from reservation "
						+ "where userId=(?) AND bookingStatus=(?)";
		List<Reservation> reservationData = jdbcTemplate.query(sql, new Object[]{selUserId,bookingSts}, new ReservationDataMapper());
		return reservationData;
	}
	class ReservationDataMapper implements RowMapper<Reservation>
	{

		@Override
		public Reservation mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return new Reservation(rs.getString("reservationId"),rs.getString("userId"),rs.getString("scheduleId"),rs.getString("reservationType"),
								rs.getDate("bookingDate"),rs.getDate("journeyDate"),rs.getInt("noOfSeats"),rs.getDouble("totalFare"),rs.getInt("bookingStatus"));
		}
	}
	
	//Retrieving Passenger Details
	@Override
	public List<Passenger> getPassengerData() {
		
		String sql2 = "select reservationId, name, gender, age, seatNo from passenger";//retrieving passenger details
		List<Passenger> passengerData = jdbcTemplate.query(sql2, new Object[]{}, new PassengerDataMapper());
		return passengerData;
	}
	class PassengerDataMapper implements RowMapper<Passenger>
	{

		@Override
		public Passenger mapRow(ResultSet rs, int arg1) throws SQLException {
			
			return new Passenger(rs.getString("reservationId"),rs.getString("name"),rs.getString("gender"),rs.getInt("age"),rs.getInt("seatNo"));
		}
	}
	
	//Cancel Ticket
	@Override
	public String cancelTicket(String reservationId) {
		int bookingSts = 0;
		try {
			String sql = "update reservation set bookingStatus=(?) where reservationId=(?)"; 
			jdbcTemplate.update(sql, new Object[]{bookingSts,reservationId});
			return "SUCCESS";
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
	
}
