<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>SafeGuard Crisis Management System</title>
	
	<c:url value="css/style.css" var="cssURL" />
	<link rel="stylesheet" type="text/css" media="screen" href="${cssURL}" />
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<security:authorize access="isAuthenticated()">
    	<% response.sendRedirect("incident"); %>
</security:authorize>


<security:authorize access="!isAuthenticated()">
<div id="wrapper">

	<!--SLIDE-IN ICONS-->
    <div class="user-icon"></div>
    <div class="pass-icon"></div>
    <!--END SLIDE-IN ICONS-->

	<!--LOGIN FORM-->
	<form name="login-form" class="login-form" action="j_spring_security_check" method="post">
	
		<!--HEADER-->
	    <div class="header">
		    <h1>SafeGuard: Login</h1>
		    <span>
		    	<c:choose>
			      <c:when test="${loginFailed}">
			      	<div style="color: red">Login failed. Please check your username and password.</div>
			      	<!-- ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} -->
			      </c:when>
			      <c:otherwise>
			      	<div>Welcome to Call Center of SafeGuard Crisis Management System.</div>
			      </c:otherwise>
				</c:choose>
	  		</span>
	    </div>
	    <!--END HEADER-->
		
		<!--CONTENT-->
	    <div class="content">
	       <input id="username" name="j_username" type="text" class="input username" placeholder="Username" onfocus="this.value=''"/>
		   <input id="password" name="j_password" type="password" class="input password" placeholder="Password" onfocus="this.value=''"/>
	    </div>
	    <!--END CONTENT-->
	    
	    <!--FOOTER-->
	    <div class="footer">
	    <!--LOGIN BUTTON--><input type="submit" name="submit" value="Login" class="button" /><!--END LOGIN BUTTON-->
	    </div>
	    <!--END FOOTER-->
	</form>
	<!--END LOGIN FORM-->
</div>
</security:authorize>


<!--Slider-in icons-->
<script type="text/javascript">
$(document).ready(function() {
	$(".username").focus(function() {
		$(".user-icon").css("left","-48px");
	});
	$(".username").blur(function() {
		$(".user-icon").css("left","0px");
	});
	
	$(".password").focus(function() {
		$(".pass-icon").css("left","-48px");
	});
	$(".password").blur(function() {
		$(".pass-icon").css("left","0px");
	});
});
</script>


</body>
</html>