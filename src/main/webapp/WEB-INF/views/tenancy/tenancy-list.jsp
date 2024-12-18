<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>Liste des Amaps </h1>
    <ul>
        <c:forEach var="tenancy" items="${tenancies}">
            <li>${tenancy.tenancyName} - ${tenancy.address}</li>
        </c:forEach>
    </ul>
    
</body>
</html>