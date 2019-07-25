<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
  <style>
  .navbar {
  overflow: hidden;
  background-color: #333;
}

.navbar a {
  float: left;
  font-size: 16px;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}
  .dropdown {
  float: left;
  overflow: hidden;
}

.dropdown .dropbtn {
  font-size: 16px;  
  border: none;
  outline: none;
  color: black;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}
  .dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {
  background-color: #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}
  
  </style>
<title>Add Flight</title>
</head>
<body>
	<div class="navbar">
	<div class="dropdown">
    <button class="dropbtn">Flight Details 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="addFlight.html">Add Flight</a>
      <a href="viewFlight.html">Delete</a>
      <a href="viewFlight.html">View</a>
    </div>
 	</div>
  	<div class="dropdown">
    	<button class="dropbtn">Route Details 
      		<i class="fa fa-caret-down"></i>
    	</button>
    <div class="dropdown-content">
      <a href="addRoute.html">Add Route</a>
      <a href="viewRoute.html">Delete</a>
      <a href="viewRoute.html">View</a>
    </div>
  	</div>
	<div class="dropdown">
    	<button class="dropbtn">Schedule Details 
      		<i class="fa fa-caret-down"></i>
    	</button>
    <div class="dropdown-content">
      <a href="addSchedule.html">Add Schedule</a>
      <a href="viewSchedule.html">Delete</a>
      <a href="viewSchedule.html">View</a>
    </div>
  	</div> 
  	<div class="dropdown">
  		<a href="passengerDetails.html" >Passenger Details</a>
  	</div>
  		<a href="changePwd.html" >Change Password</a>
	</div>
	
	<h2>Add Flight Details</h2>
	<hr/>
	<c:if test="${param.ERROR != null}">
		<p><c:out value="${param.ERROR}"/></p>
	</c:if>
	<c:if test="${param.SUCCESS != null}">
		<p><c:out value="${param.SUCCESS}"/></p>
	</c:if>
	<c:if test="${param.FAIL != null}">
		<p><c:out value="${param.FAIL}"/></p>
	</c:if>
	<c:if test="${param.FLIGHTID != null}">
		<p>Your FlightID : <c:out value="${param.FLIGHTID}"/></p>
	</c:if>
	<form:form action="addFlight.html" method="post" align="center">
		<label>Flight ID</label>
		<form:input path="flightId"/><br/>
		<label>Flight Name</label>
		<form:input path="flightName"/><br/>
		<label>Seating Capacity</label>
		<form:input path="seatingCap"/><br/>
		<label>Reservation Capacity</label>
		<form:input path="reserveCap"/><br/>
		<input type="submit" value="Add Flight"/>
	</form:form>
	
</body>
</html>