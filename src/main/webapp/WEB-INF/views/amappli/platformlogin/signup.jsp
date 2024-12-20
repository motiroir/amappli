<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription sur Amappli</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
<style>
    body {
        background-image: url("<c:url value='/resources/img/peach_lines.svg'/>");
    }
</style>
</head>
<body>
    <div class="container w-75 p-5 pt-100 pb-100">

        <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
            <div class="progress-bar" style="width: 0%" id="the-progress-bar"></div>
        </div>

        <div class="first-title d-flex">
            <h1 class="display-4 w-100">S'inscrire
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg flex-shrink-1" viewBox="0 0 16 16">
                    <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8z"/>
                </svg>
            </h1>
        </div>
        <hr>

        <form:form action="${pageContext.request.contextPath}/start/signup" method="post" modelAttribute="newUserDTO">
            <div id="form-part-1">

                <div class="mb-3">
                    <form:label for="input-email" class="form-label" path="email">Email</form:label>
                    <form:input type="email" class="form-control" id="input-email" aria-describedby="emailHelp" path="email" required="true" aria-required="true"/>
                    <form:errors path="email" class="invalid-feedback d-block" />
                    <c:if test="${not empty emailError}">
                            <div class="invalid-feedback d-block">${emailError}</div>
                        </c:if>
                    <div id="email-help" class="form-text">Cet email sera votre identifiant de connexion. Il ne pourra pas être changé.</div>
                    </div>

                <div class="mb-3">
                    <form:label for="input-password-1" class="form-label" path="password">Mot de Passe</form:label>
                    <form:input type="password" class="form-control" id="input-password-1" path="password" required="true" aria-required="true"/>
                    <form:errors path="password" class="invalid-feedback d-block" />
                </div>

                <div class="mb-3">
                    <form:label for="input-password-3" class="form-label" path="confirmPassword">Confirmez le mot de Passe</form:label>
                    <form:input type="password" class="form-control" id="input-password-2" path="confirmPassword" required="true" aria-required="true"/>
                    <form:errors path="confirmPassword" class="invalid-feedback d-block" />
                </div>

                <button type="button" class="rounded-pill bg-primary" id="sign-up-button">
                    S'inscrire
                </button>
            </div>

            <div id="connect">
                <h1 class="display-5">Déjà inscrit.e ?</h1>
                <hr>
                <a class="rounded-pill bg-primary" href="${pageContext.request.contextPath}/">Se connecter</a>
            </div>

            <div id="form-part-2">

                <button type="button" class="rounded-pill bg-primary" id="sign-up-button">
                    Retour
                </button>

                <h1 class="display-6">Renseignez vos informations de contact</h1>

                <div class="mb-3">
                    <form:label for="contactinfo-name" class="form-label" path="contactInfo.name">Nom</form:label>
                    <form:input type="text" class="form-control" id="contactinfo-name" path="contactInfo.name" required="true" aria-required="true"/>
                    <form:errors path="contactInfo.name" class="invalid-feedback d-block" />
                </div>

                <div class="mb-3">
                    <form:label for="contactinfo-firstname" class="form-label" path="contactInfo.firstName">Prénom</form:label>
                    <form:input type="text" class="form-control" id="contactinfo-firstname" path="contactInfo.firstName" required="true" aria-required="true"/>
                    <form:errors path="contactInfo.firstName" class="invalid-feedback d-block" />
                </div>

                <div class="mb-3">
                    <form:label for="contactinfo-phone" class="form-label" path="contactInfo.phoneNumber">Téléphone</form:label>
                    <form:input type="text" class="form-control" id="contactinfo-phone" path="contactInfo.phoneNumber" required="true" aria-required="true"/>
                    <form:errors path="contactInfo.phoneNumber" class="invalid-feedback d-block" />
                </div>

                <h1 class="display-6">Renseignez votre adresse</h1>

                <div class="mb-3">
                    <form:label for="address-line1" class="form-label" path="address.line1">Ligne 1</form:label>
                    <form:input type="text" class="form-control" id="address-line1" path="address.line1"/>
                    <form:errors path="address.line1" class="invalid-feedback d-block" />
                </div>

                <div class="mb-3">
                    <form:label for="address-line2" class="form-label" path="address.line2">Ligne 2</form:label>
                    <form:input type="text" class="form-control" id="address-line2" path="address.line2" required="true" aria-required="true"/>
                    <form:errors path="address.line2" class="invalid-feedback d-block" />
                </div>

                <div class="mb-3">
                    <form:label for="address-postcode" class="form-label" path="address.postCode">Code Postal</form:label>
                    <form:input type="text" class="form-control" id="address-postcode" path="address.postCode" required="true" aria-required="true"/>
                    <form:errors path="address.postCode" class="invalid-feedback d-block" />
                </div>

                <div class="mb-3">
                    <form:label for="address-city" class="form-label" path="address.city">Ville</form:label>
                    <form:input type="text" class="form-control" id="address-city" path="address.city" required="true" aria-required="true"/>
                    <form:errors path="address.city" class="invalid-feedback d-block" />
                </div>

                <button type="submit" class="btn btn-primary">Valider</button>
            </div>

        </form:form>

    </div>
    </script src="<c:url value='/resources/js/platformsignup.js'/>"></script>
    <script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js'/>"></script>
</body>
</html>