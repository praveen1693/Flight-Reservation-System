<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<div align="center">
	<c:if test="${param.SUCCESS != null}">
		<c:out value="${param.SUCCESS}"/>
	</c:if>
	<c:if test="${param.USERID != null}">
		<p>Your UserID : <c:out value="${param.USERID}"/></p>
	</c:if>
	
	<form:form action="register.html" method="post">
		<%-- <label>UserID</label>
		<form:input path="userId"/><br/> --%>
		<label>First name</label>
		<form:input path="firstName"/><br/>
		<label>Last name</label>
		<form:input path="lastName"/><br/>
		<label>User Type</label>
			<form:radiobutton path="userType" value="C"/>User
			<form:radiobutton path="userType" value="A" />Admin<br/>
		<label>DOB</label>
		<form:input type="date" path="DOB"/><br/>
		<label>Gender</label>
			<form:radiobutton path="gender" value="Male"/>Male
			<form:radiobutton path="gender" value="Female"/>Female<br/>
		<label>Street</label>
		<form:input path="street"/><br/>
		<label>Location</label>
		<form:input path="location"/><br/>
		<label>City</label>
		<form:input path="city"/><br/>
		<label>State</label>
		<form:input path="state"/><br/>
		<label>Pincode</label>
		<form:input path="pincode"/><br/>
		<label>Mobile Number</label>
		<form:input path="phNo"/><br/>
		<label>Email Address</label>
		<form:input path="emailId"/><br/>
		<label>Password</label>
		<form:input type="password" path="password"/><br/>
		<input type="submit" value="Create"/>
		<input type="reset" value="Reset"/><br/>
		Already have an account? <a href="login.html">Login</a>
	</form:form> 
	</div>
	
</body>
</html>