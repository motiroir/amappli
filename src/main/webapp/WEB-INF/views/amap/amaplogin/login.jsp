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
    input[type="password"] {
        border-radius: 25px;
        border: 1px solid #ccc;
        padding: 10px 20px;
        font-size: 1rem;
    }

    input[type="text"]:focus,
    input[type="email"]:focus,
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
            <h1>Se connecter</h1>
            <a href="#" class="btn-close" aria-label="Retour"></a>
        </div>

        <form action="${pageContext.request.contextPath}/tenancies/${tenancyId}/amap/amaplogin/login" method="post">
            
            <!-- Affichage des erreurs générales -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    <strong>Erreur :</strong> ${error}
                </div>
            </c:if>

            <!-- Champs pour le formulaire -->
            <div class="mb-3">
                <input type="email" class="form-control" id="email" name="email" placeholder="Votre adresse mail" required value="${loginDTO.email}">
            </div>

            <div class="mb-3">
                <input type="password" class="form-control" id="motDePasse" name="password" placeholder="Votre mot de passe" required>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-dark">Se connecter</button>
            </div>
        </form>
    </div>
</body>
</html>
