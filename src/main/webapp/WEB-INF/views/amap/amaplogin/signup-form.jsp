<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/amap/signup-form.css' />">
</head>

<body>
    <div class="container py-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1>Formulaire d'inscription</h1>
            <a href="login" class="btn-close" aria-label="Retour"></a>
        </div>

        <form:form 
            modelAttribute="userDTO" 
            method="post" 
            action="${pageContext.request.contextPath}/tenancies/${tenancyId}/amap/amaplogin/signup">

            <!-- Ajout du token CSRF -->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <!-- Affichage des erreurs générales -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    <strong>Erreur :</strong> ${error}
                </div>
            </c:if>

            <!-- Champs pour le formulaire -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <form:input path="contactInfo.name" cssClass="form-control" id="nom" placeholder="Votre nom" required="true" />
                    <form:errors path="contactInfo.name" cssClass="text-danger" />
                </div>
                <div class="col-md-6">
                    <form:input path="contactInfo.firstName" cssClass="form-control" id="prenom" placeholder="Votre prénom" required="true" />
                    <form:errors path="contactInfo.firstName" cssClass="text-danger" />
                </div>
            </div>

            <div class="mb-3">
                <form:input path="contactInfo.phoneNumber" cssClass="form-control" id="telephone" placeholder="Votre numéro de téléphone" />
                <form:errors path="contactInfo.phoneNumber" cssClass="text-danger" />
            </div>

            <div class="mb-3">
                <form:input path="address.line1" cssClass="form-control" id="adresse" placeholder="Votre adresse" required="true" />
                <form:errors path="address.line1" cssClass="text-danger" />
            </div>

            <div class="mb-3">
                <form:input path="address.line2" cssClass="form-control" id="complement" placeholder="Complément d'adresse" />
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <form:input path="address.postCode" cssClass="form-control" id="codePostal" placeholder="Code postal" required="true" />
                    <form:errors path="address.postCode" cssClass="text-danger" />
                </div>
                <div class="col-md-6">
                    <form:input path="address.city" cssClass="form-control" id="ville" placeholder="Ville" required="true" />
                    <form:errors path="address.city" cssClass="text-danger" />
                </div>
            </div>

            <div class="mb-3">
                <form:input path="email" cssClass="form-control" id="email" placeholder="Votre adresse mail" required="true" />
                <form:errors path="email" cssClass="text-danger" />
            </div>

            <div class="mb-3">
                <form:password path="password" cssClass="form-control" id="motDePasse" placeholder="Votre mot de passe" required="true" />
                <form:errors path="password" cssClass="text-danger" />
            </div>

            <div class="mb-3">
                <form:password path="confirmPassword" cssClass="form-control" id="confirmMotDePasse" placeholder="Confirmez votre mot de passe" required="true" />
                <form:errors path="confirmPassword" cssClass="text-danger" />
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-dark">S'inscrire</button>
            </div>
        </form:form>
    </div>
</body>
</html>
