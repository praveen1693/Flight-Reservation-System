<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
 
 </head>
<body>
	
  	
  	<% String ut = (String) session.getAttribute("userType"); 
  	System.out.println("changepwd ut " + ut);
  	String route = null;	
  	if(ut.equals("A"))
  		route="adminMenu.html";
  	else if(ut.equals("C"))
  		route="customerMenu.html";
  	System.out.println("changepwd route " + route);
  		
  	%>
  	<form action="<%=route%>" method = "get">
			
			<button class="btn btn-info" type="submit">HOME</button>
	</form>
  	<br/><br/><br/>
  	<hr/>
  	<div align="center">
  	<h3>Change Password</h3>
  	<c:if test="${param.INVALID != null}">
		<p><c:out value="${param.INVALID}"/></p>
	</c:if>
	<c:if test="${param.SUCCESS != null}">
		<p><c:out value="${param.SUCCESS}"/></p>
	</c:if>
	<c:if test="${param.FAIL != null}">
		<p><c:out value="${param.FAIL}"/></p>
	</c:if>
  	<form:form>
  		<label>Current Password</label>
  		<form:input type="text" path="password"/><br/>
  		<label>New Password</label>
  		<input type="text" name="newPass"/><br/>
  		<label>Confirm Password</label>
  		<input type="text" name="confirmPass"/><br/>
  		<input type="submit" value="Change Passsword"/>
  	</form:form>
  	</div>
	
</body>
</html>