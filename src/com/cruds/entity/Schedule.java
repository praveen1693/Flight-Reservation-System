package com.cruds.entity;

public class Schedule {

	private String scheduleId;
	private String flightId;
	private String routeId;
	private int travelDuration;
	private String availableDays;
	private String departureTime;
	
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public int getTravelDuration() {
		return travelDuration;
	}
	public void setTravelDuration(int travelDuration) {
		this.travelDuration = travelDuration;
	}
	public String getAvailableDays() {
		return availableDays;
	}
	public void setAvailableDays(String availableDays) {
		this.availableDays = availableDays;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	public Schedule(String scheduleId, String flightId, String routeId, int travelDuration, String availableDays,
			String departureTime) {
		super();
		this.scheduleId = scheduleId;
		this.flightId = flightId;
		this.routeId = routeId;
		this.travelDuration = travelDuration;
		this.availableDays = availableDays;
		this.departureTime = departureTime;
	}
	public Schedule() {
		super();
	}
	
	
}
