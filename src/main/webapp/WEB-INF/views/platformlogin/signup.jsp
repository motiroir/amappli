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
<form:form action="${pageContext.request.contextPath}/signup" method="post" modelAttribute="user">
    <div class="mb-3">
      <form:label for="InputEmail1" class="form-label" path="email">Email</form:label>
      <form:input type="email" class="form-control" id="InputEmail1" aria-describedby="emailHelp" path="email" />
      <div id="emailHelp" class="form-text">Cet email sera votre identifiant de connexion. Il ne pourra pas être changé.</div>
    </div>
    <div class="mb-3">
      <form:label for="InputPassword1" class="form-label" path="password">Mot de Passe</form:label>
      <form:input type="password" class="form-control" id="InputPassword1" path="password" />
    </div>
    <h3>Renseignez votre adresse</h3>
    <div class="mb-3">
        <form:label for="AddressLine1" class="form-label" path="address.line1">Ligne 1</form:label>
        <form:input type="text" class="form-control" id="AddressLine1" path="address.line1" />
    </div>
    <div class="mb-3">
        <form:label for="AddressLine2" class="form-label" path="address.line2">Ligne 2</form:label>
        <form:input type="text" class="form-control" id="AddressLine2" path="address.line2" />
    </div>
    <div class="mb-3">
        <form:label for="AddressPostCode" class="form-label" path="address.postCode">Code Postal</form:label>
        <form:input type="text" class="form-control" id="AddressPostCode" path="address.postCode" />
    </div>
    <div class="mb-3">
        <form:label for="AddressCity" class="form-label" path="address.City">Ville</form:label>
        <form:input type="text" class="form-control" id="AddressCity" path="address.City" />
    </div>
    <h3>Renseignez vos informations de contact</h3>
    <div class="mb-3">
        <form:label for="ContactInfoName" class="form-label" path="contactInfo.name">Nom</form:label>
        <form:input type="text" class="form-control" id="ContactInfoName" path="contactInfo.name" />
    </div>
    <div class="mb-3">
        <form:label for="ContactInfoName" class="form-label" path="contactInfo.firstName">Prénom</form:label>
        <form:input type="text" class="form-control" id="ContactInfoName" path="contactInfo.firstName" />
    </div>
    <div class="mb-3">
        <form:label for="ContactInfoName" class="form-label" path="contactInfo.phoneNumber">Téléphone</form:label>
        <form:input type="text" class="form-control" id="ContactInfoName" path="contactInfo.phoneNumber" />
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form:form>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>