package com.cruds.entity;

public class Passenger {

	private String reservationId;
	private String name;
	private String gender;
	private int age;
	private int seatNo;
	public String getReservationId() {
		return reservationId;
	}
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public Passenger(String reservationId, String name, String gender, int age, int seatNo) {
		super();
		this.reservationId = reservationId;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.seatNo = seatNo;
	}
	public Passenger() {
		super();
	}
	
	
}
