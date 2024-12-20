<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
</head>
<style>
    body {
        background-color: #ffffff;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100vw;
        height: 100vh;
        margin: 0;
    }

    .container {
        max-width: 600px;
        padding: 30px;
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    h1 {
        font-size: 1.25rem;
        font-weight: bold;
        font-family: 'Futura', sans-serif;
        color: #333;
        padding: 10px;
    }

    .btn-close {
        background-color: transparent;
        border: none;
        font-size: 1.25rem;
        color: #333;
        cursor: pointer;
    }

    input[type="text"],
    input[type="email"],
    input[type="tel"],
    input[type="password"] {
        border-radius: 25px;
        border: 1px solid #ccc;
        padding: 10px 20px;
        font-size: 1rem;
    }

    input[type="text"]:focus,
    input[type="email"]:focus,
    input[type="tel"]:focus,
    input[type="password"]:focus {
        border-color: #007bff;
        outline: none;
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }

    button[type="submit"] {
        background-color: #344238;
        color: #fff;
        border-radius: 25px;
        padding: 10px 30px;
        border: none;
        font-size: 1rem;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    button[type="submit"]:hover {
        background-color: #455449;
    }
</style>
<body>
    <div class="container py-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1>Formulaire d'inscription</h1>
            <a href="#" class="btn-close" aria-label="Retour">
</a>
        </div>

        <form action="${pageContext.request.contextPath}/tenancies/${tenancyId}/amap/amaplogin/signup" method="post">
            
            <!-- Affichage des erreurs générales -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    <strong>Erreur :</strong> ${error}
                </div>
            </c:if>

            <!-- Champs pour le formulaire -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <input type="text" class="form-control" id="nom" name="contactInfo.name" placeholder="Votre nom" required value="${userDTO.contactInfo.name}">
                    <c:if test="${not empty userDTO.contactInfo.name}">
                        <div class="text-danger">${error}</div>
                    </c:if>
                </div>
                <div class="col-md-6">
                    <input type="text" class="form-control" id="prenom" name="contactInfo.firstName" placeholder="Votre prénom" required value="${userDTO.contactInfo.firstName}">
                </div>
            </div>

            <div class="mb-3">
                <input type="tel" class="form-control" id="telephone" name="contactInfo.phoneNumber" placeholder="Votre numéro de téléphone" value="${userDTO.contactInfo.phoneNumber}">
            </div>

            <div class="mb-3">
                <input type="text" class="form-control" id="adresse" name="address.line1" placeholder="Votre adresse" required value="${userDTO.address.line1}">
            </div>

            <div class="mb-3">
                <input type="text" class="form-control" id="complement" name="address.line2" placeholder="Complément d'adresse" value="${userDTO.address.line2}">
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <input type="text" class="form-control" id="codePostal" name="address.postCode" placeholder="Code postal" required value="${userDTO.address.postCode}">
                </div>
                <div class="col-md-6">
                    <input type="text" class="form-control" id="ville" name="address.city" placeholder="Ville" required value="${userDTO.address.city}">
                </div>
            </div>

            <div class="mb-3">
                <input type="email" class="form-control" id="email" name="email" placeholder="Votre adresse mail" required value="${userDTO.email}">
            </div>

            <div class="mb-3">
                <input type="password" class="form-control" id="motDePasse" name="password" placeholder="Votre mot de passe" required>
            </div>

            <div class="mb-3">
                <input type="password" class="form-control" id="confirmMotDePasse" name="confirmPassword" placeholder="Confirmez votre mot de passe" required>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-dark">S'inscrire</button>
            </div>
        </form>
    </div>
</body>
</html>
