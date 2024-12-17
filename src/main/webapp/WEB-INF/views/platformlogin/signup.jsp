<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription sur Amappli</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<h1>Qui êtes vous?</h1>
<form:form action="${pageContext.request.contextPath}/signup" method="post" modelAttribute="newUserDTO">
    <div class="mb-3">
      <form:label for="input-email" class="form-label" path="email">Email</form:label>
      <form:input type="email" class="form-control" id="input-email" aria-describedby="emailHelp" path="email" />
      <form:errors path="email" class="invalid-feedback d-block" />
      <c:if test="${not empty emailError}">
            <div class="invalid-feedback d-block">${emailError}</div>
        </c:if>
      <div id="email-help" class="form-text">Cet email sera votre identifiant de connexion. Il ne pourra pas être changé.</div>
    </div>
    <div class="mb-3">
      <form:label for="input-password-1" class="form-label" path="password">Mot de Passe</form:label>
      <form:input type="password" class="form-control" id="input-password-1" path="password" />
      <form:errors path="password" class="invalid-feedback d-block" />
    </div>
    <div class="mb-3">
        <form:label for="input-password-3" class="form-label" path="confirmPassword">Confirmez le mot de Passe</form:label>
        <form:input type="password" class="form-control" id="input-password-2" path="confirmPassword" />
        <form:errors path="confirmPassword" class="invalid-feedback d-block" />
      </div>
    <h3>Renseignez votre adresse</h3>
    <div class="mb-3">
        <form:label for="address-line1" class="form-label" path="address.line1">Ligne 1</form:label>
        <form:input type="text" class="form-control" id="address-line1" path="address.line1" />
        <form:errors path="address.line1" class="invalid-feedback d-block" />
    </div>
    <div class="mb-3">
        <form:label for="address-line2" class="form-label" path="address.line2">Ligne 2</form:label>
        <form:input type="text" class="form-control" id="address-line2" path="address.line2" />
        <form:errors path="address.line2" class="invalid-feedback d-block" />
    </div>
    <div class="mb-3">
        <form:label for="address-postcode" class="form-label" path="address.postCode">Code Postal</form:label>
        <form:input type="text" class="form-control" id="address-postcode" path="address.postCode" />
        <form:errors path="address.postCode" class="invalid-feedback d-block" />
    </div>
    <div class="mb-3">
        <form:label for="address-city" class="form-label" path="address.city">Ville</form:label>
        <form:input type="text" class="form-control" id="address-city" path="address.city" />
        <form:errors path="address.city" class="invalid-feedback d-block" />
    </div>
    <h3>Renseignez vos informations de contact</h3>
    <div class="mb-3">
        <form:label for="contactinfo-name" class="form-label" path="contactInfo.name">Nom</form:label>
        <form:input type="text" class="form-control" id="contactinfo-name" path="contactInfo.name" />
        <form:errors path="contactInfo.name" class="invalid-feedback d-block" />
    </div>
    <div class="mb-3">
        <form:label for="contactinfo-firstname" class="form-label" path="contactInfo.firstName">Prénom</form:label>
        <form:input type="text" class="form-control" id="contactinfo-firstname" path="contactInfo.firstName" />
        <form:errors path="contactInfo.firstName" class="invalid-feedback d-block" />
    </div>
    <div class="mb-3">
        <form:label for="contactinfo-phone" class="form-label" path="contactInfo.phoneNumber">Téléphone</form:label>
        <form:input type="text" class="form-control" id="contactinfo-phone" path="contactInfo.phoneNumber" />
        <form:errors path="contactInfo.phoneNumber" class="invalid-feedback d-block" />
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form:form>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>