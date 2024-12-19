<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>



<body>
    <div class="container mt-5">
        <h2 class="text-center">Inscription réussie !</h2>
        <p class="text-center">Votre compte a été créé avec succès. Vous pouvez maintenant vous connecter.</p>
        <div class="text-center">
            <a href="<c:url value='/tenancies/${tenancyId}/amap/amaplogin' />" class="btn btn-primary">Se connecter</a>
        </div>
    </div>


</body>
</html>