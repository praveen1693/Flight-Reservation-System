<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		<a href="viewTicket.html" >View Flight Schedule</a>
		<div class="dropdown">
    <button class="dropbtn">Ticket Booking 
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="bookTicket.html">Book Ticket</a>
      <a href="viewTicket.html">Cancel Ticket</a>
    </div>
 	</div>
		<a href="changePwd.html" >Change Password</a>
	</div>
	
	<div align="center">
	<h3>Enter Passenger Details</h3><hr/>
	<c:if test="${param.ERROR != null}">
		<p><c:out value="${param.ERROR}"/></p>
	</c:if>
	<c:if test="${param.NOSEAT != null}">
		<p><c:out value="${param.NOSEAT}"/></p>
	</c:if>
	<c:if test="${param.SUCCESS != null}">
		<p><c:out value="${param.SUCCESS}"/></p>
	</c:if>
	<c:if test="${param.FAIL != null}">
		<p><c:out value="${param.FAIL}"/></p>
	</c:if>
	<form:form action="passenger.html" method="post">
		<label>Name</label>
		<form:input path="name"/><br/>
		<label>Gender</label>
			<form:radiobutton path="gender" value="Male"/>Male
			<form:radiobutton path="gender" value="Female"/>Female<br/>
		<label>Age</label>
		<form:input path="age"/><br/>
		<label>Select Seat Number</label>
		<form:input path="seatNo"/><br/>
		<input type="submit" value="Add Details"/>
	</form:form>
	</div>
	
</body>
</html>