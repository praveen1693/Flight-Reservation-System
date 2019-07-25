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
  <script type="text/javascript">
		
		function submitForm()
		{
			document.viewForm.ACTION.value = "DETAILS";
			document.viewForm.submit();
		}
	
	</script>
<title>View/Edit Ticket</title>
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
	
	<form:form name="viewForm">
	<div align="center">
	<h3>Ticket Details</h3>
	<c:if test="${param.SUCCESS != null}">
		<p><c:out value="${param.SUCCESS}"/></p>
	</c:if>
	<c:if test="${param.FAIL != null}">
		<p><c:out value="${param.FAIL}"/></p>
	</c:if>
	
	<table>
		<thead>
			<tr>
				<td>Reservation ID</td>
				<td>Reservation Type</td>
				<td>Journey Date</td>
				<td>Total Fare</td>
			</tr>
		</thead>
		
		<c:forEach items="${TICKET_LIST}" var="tk">
			<tr>
				<td><c:out value="${tk.reservationId}"/></td>				
				<td><c:out value="${tk.reservationType}"/></td>
				<td><c:out value="${tk.journeyDate}"/></td>
				<td><c:out value="${tk.totalFare}"/></td>
			
				<td><input type="radio" name="delReservationId" value="${tk.reservationId}"/></td>				
			</tr>
		</c:forEach>
	</table>
	
		<br/><hr/>
		<label>Enter 'CONFIRM' to cancel the ticket</label>
		<input type="text" name="cancel"/><br/>
		<input type="hidden" name="ACTION" value="CANCEL"/>
		<!-- <input type="button" onclick="submitForm()" value="DETAILS"/> -->
		<input type="submit" value="CANCEL"/>
		
	
	</div>
	</form:form>
</body>
</html>