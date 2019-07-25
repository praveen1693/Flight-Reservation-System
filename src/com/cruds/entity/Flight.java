package com.cruds.entity;

public class Flight {
	
	private String flightId;
	private String flightName;
	private int seatingCap;
	private int reserveCap;
	
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public int getSeatingCap() {
		return seatingCap;
	}
	public void setSeatingCap(int seatingCap) {
		this.seatingCap = seatingCap;
	}
	public int getReserveCap() {
		return reserveCap;
	}
	public void setReserveCap(int reserveCap) {
		this.reserveCap = reserveCap;
	}
	public Flight(String flightId, String flightName, int seatingCap, int reserveCap) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.seatingCap = seatingCap;
		this.reserveCap = reserveCap;
	}
	public Flight() {
		super();
	}
	
	
}
