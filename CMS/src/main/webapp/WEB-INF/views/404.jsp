<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page Not Found</title>
</head>
<body>
	<h2>Sorry, the page doesn't exist, or an error has occurred.</h2>
	<h2> ${status} </h2>
	<c:forEach items="${e.stackTrace}" var="element"> ${element} </c:forEach>
</body>
</html>