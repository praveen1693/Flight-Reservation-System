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

th, td {
  padding: 10px;
}
  
  </style>
  <script type="text/javascript">
		
		function submitForm()
		{
			document.viewForm.ACTION.value = "EDIT";
			document.viewForm.submit();
		}
	
	</script>
<title>View, Edit, Delete Flight </title>
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
  	<form:form name="viewForm">
  	<div align="center">
  	<h3>Flight Details</h3><hr/>
  	<c:if test="${param.ERROR != null}">
		<p><c:out value="${param.ERROR}"/></p>
	</c:if>
	<c:if test="${param.SUCCESS != null}">
		<p><c:out value="${param.SUCCESS}"/></p>
	</c:if>
	<c:if test="${param.FAIL != null}">
		<p><c:out value="${param.FAIL}"/></p>
	</c:if>
  	<table>
		<thead>
			<tr>
				<td>Flight ID</td>
				<td>Flight Name</td>
				<td>Seating Capacity</td>
				<td>Available Capacity</td>
			</tr>
		</thead>
		
		<c:forEach items="${FLIGHT_LIST}" var="flight">
			<tr>
				<td><c:out value="${flight.flightId}"/></td>				
				<td><c:out value="${flight.flightName}"/></td>
				<td><c:out value="${flight.seatingCap}"/></td>
				<td><c:out value="${flight.reserveCap}"/></td>
			
				<td><input type="radio" name="delFlightId" value="${flight.flightId}"/></td>				
			</tr>
		</c:forEach>
	</table>

	<br/><hr/>
	
		<input type="hidden" name="ACTION" value="DELETE"/>
		<input type="button" onclick="submitForm()" value="EDIT"/>
		<input type="submit" value="Delete"/>
  	</div>
	</form:form>
	
</body>
</html>