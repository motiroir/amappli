<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Changer le mot de passe</title>
</head>
<body>
    <h1>Changer le mot de passe</h1>

    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div style="color: green;">${success}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/tenancies/${tenancyAlias}/amap/amaplogin/${userId}/change-password" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
       <div>
        <label for="currentPassword">Mot de passe actuel :</label>
        <input type="password" id="currentPassword" name="currentPassword" placeholder="Mot de passe actuel" required />
        <c:if test="${not empty errorCurrentPassword}">
            <div style="color: red;">${errorCurrentPassword}</div>
        </c:if>
    </div>

    <div>
        <label for="newPassword">Nouveau mot de passe :</label>
        <input type="password" id="newPassword" name="newPassword" placeholder="Nouveau mot de passe" required />
        <c:if test="${not empty errorNewPassword}">
            <div style="color: red;">${errorNewPassword}</div>
        </c:if>
    </div>

    <div>
        <label for="confirmNewPassword">Confirmer le nouveau mot de passe :</label>
        <input type="password" id="confirmNewPassword" name="confirmNewPassword" placeholder="Confirmer le mot de passe" required />
        <c:if test="${not empty errorConfirmNewPassword}">
            <div style="color: red;">${errorConfirmNewPassword}</div>
        </c:if>
    </div>

    <button type="submit">Mettre à jour le mot de passe</button>
    </form>
</body>
</html>