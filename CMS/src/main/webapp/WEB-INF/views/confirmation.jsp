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
	<title>New Incident</title>
	
	<c:url value="css/style.css" var="cssURL" />
	<link rel="stylesheet" type="text/css" media="screen" href="${cssURL}" />
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>

<body>
<div id="myWrapper">
	<security:authorize access="hasRole('OPERATOR')">
	
	<form:form method="post" action="incident" modelAttribute="incidentForm" commandname="incidentForm" class="login-form" style="width:400px;">
		<div class="header">
			<h6>Welcome, ${username}. &nbsp 
				<a class="link" href="#" onclick="javascript:window.location.href='<c:url value="j_spring_security_logout" />'">Logout</a>. </h6> 
			<h6 style="color: red; margin-bottom:20px;"> ${message}</h6>
			<h1 style="text-align:center;">New Incident</h1>
			<span><p style="text-align:center;">Please fill in the form.</p></span>
		</div>
		<div class="content">
			<table>
				<tr>
					<td>
						<label>Caller
						</label> 
					</td>
					<td>
						<form:input value="${incidentForm.callerName}" path="callerName" type="text" name="callerName" id="callerName" class="myField" readonly="true" />
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td>
						<label>Phone
						</label> 
					</td>
					<td>
						<form:input value="${incidentForm.callerPhone}" path="callerPhone" type="text" name="callerPhone" id="callerPhone" class="myField" readonly="true" />
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td>
						<label>Address
						</label> 
					</td>
					<td>
						<form:textarea value="${incidentForm.address}" rows="2" path="address" type="text" name="address" id="address" class="myField" readonly="true"/>
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td>
						<label>Postal
						</label> 
					</td>
					<td>
						<form:input value="${incidentForm.postal}" path="postal" type="text" name="postal" id="postal" class="myField" readonly="true"/>
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td>
						<label>Description
						</label> 
					</td>
					<td>
						<form:textarea value="${incidentForm.description}" rows="4" path="description" type="text" name="description" id="description" class="myField" readonly="true"/>
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td><label>Type</label></td>
					<td>
						<form:input value="${incidentForm.type}" path="type" type="text" name="type" id="type" class="myField" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td><label>Emergency</label></td>
					<td>
						<form:input value="${incidentForm.level}" path="level" type="text" name="level" id="level" class="myField" readonly="true"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="footer">
			<button type="submit" name="_create" id="_create" class="button">CONFIRM</button>
			<button type="submit" name="_back" id="_back" class="reset">BACK</button>
		</div>
	</form:form>
	</security:authorize>
	
	
</div>

</body>
</html>