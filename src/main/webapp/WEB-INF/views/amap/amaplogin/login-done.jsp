<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription réussie</title>
    <link rel="stylesheet"
	href="<c:url value='/resources/css/amap/login-done.css' />">
  
</head>
<body>
    <div class="container">
        <h2>Inscription réussie !</h2>
        <p>Votre compte a été créé avec succès. Vous pouvez maintenant vous connecter.</p>
        <div>
            <a href="login" class="btn">Se connecter</a>
        </div>
    </div>
</body>
</html>
