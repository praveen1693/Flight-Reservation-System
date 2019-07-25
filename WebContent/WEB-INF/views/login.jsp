<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<script type="text/javascript">
  	function loginValidate()
  	{
  		var userName = document.forms["loginForm"]["userName"];
  		var password = document.forms["loginForm"]["password"];
  		
  		if (userName.value == "")                                  
  	    { 
  	        window.alert("Please enter your name."); 
  	        userName.focus(); 
  	        return false; 
  	    } 
  		
  		if (password.value == "")                        
  	    { 
  	        window.alert("Please enter your password"); 
  	        password.focus(); 
  	        return false; 
  	    } 
  	}
  </script>
</head>
<body>
	<div align="center">
	<h2>Login</h2>
	<c:if test="${param.ERROR != null}">
		<p><c:out value="${param.ERROR}"/></p>
	</c:if>
	
	<form action="login.html" method="post" name="loginForm" onsubmit="return loginValidate()">
		<label>Username</label>
		<input type="text" name="userId"/> <br/>
		<label>Password</label>
		<input type="password" name="password"/><br/>
		<input type="submit" value="login"/>
	</form>
	</div>
</body>
</html>