<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Passenger Details</title>
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
	
	<div align="center">
		<h3>Passenger Ticket Details</h3><hr/>
	<table>
		<thead>
			<tr>
				<td>Reservation ID</td>
				<td>Name</td>
				<td>Gender</td>
				<td>Age</td>
				<td>Seat No</td>
			</tr>
		</thead>
		
		<c:forEach items="${PASSENGER_LIST}" var="ps">
			<tr>
				<td><c:out value="${ps.reservationId}"/></td>
				<td><c:out value="${ps.name}"/></td>				
				<td><c:out value="${ps.gender}"/></td>
				<td><c:out value="${ps.age}"/></td>
				<td><c:out value="${ps.seatNo}"/></td>				
			</tr>
		</c:forEach>
	</table>
	</div>
	
</body>
</html>