<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<h1>This is the login page</h1>
 <!-- Check if there is an authentication error -->
 <c:if test="${param.error != null}">
    <p style="color:red;">Invalid username or password. Please try again.</p>
</c:if>

<form:form action="<c:url value='/sectest/login' />" method="post">
        <label for="username">Username:</label>
        <form:input path="username" type="text" id="username" name="username" required="true" /><br><br>
        <label for="password">Password:</label>
        <form:input path="password" type="password" id="password" name="password" required="true" /><br><br>        
        <input type="submit" value="Login" />
    </form:form>
</body>
</html>