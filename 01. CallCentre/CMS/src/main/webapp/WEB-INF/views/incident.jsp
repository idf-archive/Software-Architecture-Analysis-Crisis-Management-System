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
	
	<form:form name="incidentForm" method="post" action="incident" modelAttribute="incidentForm" commandname="incidentForm" class="login-form" style="width:400px;">
		<div class="header">
			<h6>Welcome, ${username}. &nbsp 
				<a class="link" href="#" onclick="javascript:window.location.href='<c:url value="j_spring_security_logout" />'">Logout</a>. </h6> 
			<h6 style="color: red; margin-bottom:20px; "> ${message}</h6>
			<h1 style="text-align:center;">New Incident</h1>
			<span style="text-align:center;"><p style="text-align:center;">Please fill in the form.</p></span>
		</div>
		<div class="content">
			<table>
				<tr>
					<td>
						<label>Caller
						</label> 
					</td>
					<td>
						<form:input value="${incidentForm.callerName}" placeholder="required" path="callerName" type="text" name="callerName" id="callerName" class="myField" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<form:errors path="callerName" class="error"/>
					</td>
				</tr>
				
				<tr>
					<td>
						<label>Phone
						</label> 
					</td>
					<td>
						<form:input value="${incidentForm.callerPhone}" placeholder="required, 8 digits" path="callerPhone" type="text" name="callerPhone" id="callerPhone" class="myField" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<form:errors path="callerPhone" class="error" />					
					</td>
				</tr>
				
				<tr>
					<td>
						<label>Address
						</label> 
					</td>
					<td>
						<form:textarea value="${incidentForm.address}" placeholder="required" rows="2" path="address" type="text" name="address" id="address" class="myField" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<form:errors path="address" class="error" />					
					</td>
				</tr>
	
				<tr>
					<td>
						<label>Postal
						</label> 
					</td>
					<td colspan=3>
						<form:input value="${incidentForm.postal}" placeholder="optional, 6 digits" path="postal" type="text" name="postal" id="postal" class="myField" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<form:errors path="postal" class="error" />					
					</td>
				</tr>
	
				<tr>
					<td>
						<label>Description
						</label> 
					</td>
					<td>
						<form:textarea value="${incidentForm.description}" placeholder="required" rows="4" path="description" type="text" name="description" id="description" class="myField" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<form:errors path="description" class="error" />
					</td>
				</tr>
				
				<tr>
					<td><label>Type</label></td>
					<td colspan=2>
						<form:select value="${incidentForm.type}" placeholder="required" path="type" type="text" name="type" id="type" class="myField" 
						onchange="setOptions();">>
							<form:option value="" />
							<form:option value="Emergency Ambulance" />
							<form:option value="Rescue and Evacuation" />
							<form:option value="Fire Fighting" />
							<form:option value="Gas Leak Control" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<form:errors path="type" class="error" />
					</td>
				</tr>
				
				<tr>
					<td><label>Emergency</label></td>
					<td colspan=2>
						<form:select value="${incidentForm.level}" placeholder="required" path="level" type="text" name="level" id="level" class="myField">
							<form:option id="default" value="" />
							<form:option id="1" value="1" />
							<form:option id="2" value="2" />
							<form:option id="3" value="3" />
							<form:option id="4" value="4" />
							<form:option id="5" value="5" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<form:errors path="level" class="error" />
					</td>
				</tr>				
				
			</table>
		</div>
		<div class="footer">
			<input type="submit" name="_go" id="_go" class="button" value="GO"></input>
			<input type="submit" name="_reset" id="_reset" class="reset" value="RESET"></input>
		</div>
	</form:form>
	</security:authorize>
	
	
	<security:authorize access="hasRole('AGENCY')">
	<form:form method="post" action="resolve" modelAttribute="resolvedForm" commandname="resolvedForm" class="login-form" style="width:400px;">
		<div class="header">
			<h6>Welcome, ${username}. &nbsp 
				<a class="link" href="#" onclick="javascript:window.location.href='<c:url value="j_spring_security_logout" />'">Logout</a>. </h6> 
			<h6 style="color: red; margin-bottom:20px;"> ${message}</h6>
			<h1 style="text-align:center;">Resolve Incident</h1>
			<span><p style="text-align:center;">Please fill in the form.</p></span>
		</div>
		<div class="content">
			<table>
				<tr>
					<td>
						<label>Incident ID
						</label> 
					</td>
					<td>
						<form:input value="${resolvedForm.incidentId}" path="incidentId" type="text" name="incidentId" id="incidentId" class="myField"  />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<form:errors path="incidentId" class="error" />					
					</td>
				</tr>
			</table>
		</div>
		<div class="footer">
			<button type="submit" path="_go" name="_go" id="_go" class="button">GO</button>
			<button type="submit" path="_reset" name="_reset" id="_reset" class="reset">RESET</button>
		</div>
	</form:form>
	</security:authorize>	
	
	
</div>

<script type="text/javascript">
function setOptions() {
	var type = document.getElementById("type");
	var chosen = type.options[type.selectedIndex].value;
	if (chosen == "Emergency Ambulance") {
		document.getElementById("2").selected=true;
	} else if (chosen == "Rescue and Evacuation") {
		document.getElementById("3").selected=true;
	} else if (chosen == "Fire Fighting") {
		document.getElementById("1").selected=true;
	} else if (chosen == "Gas Leak Control") {
		document.getElementById("4").selected=true;
	} else if (chosen == "Dengue") {
		document.getElementById("5").selected=true;
	} else { 
		document.getElementById("default").selected=true;
	}
}
</script>

</body>
</html>