<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
</head>
<body>
    <div class="container py-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1>Formulaire d'inscription</h1>
            <a href="page-de-connexion.html" class="btn-close" aria-label="Retour"></a>
        </div>
        <form>
            <div class="row mb-3">
                <div class="col-md-6">
                
                    <input type="text" class="form-control" id="nom" placeholder="Votre nom" required>
                </div>
                <div class="col-md-6">
                  
                    <input type="text" class="form-control" id="prenom" placeholder="Votre prénom" required>
                </div>
            </div>
            <div class="mb-3">
                
                <input type="tel" class="form-control" id="telephone" placeholder="Votre numéro de téléphone">
            </div>
            <div class="mb-3">
               
                <input type="text" class="form-control" id="adresse" placeholder="Votre adresse" required>
            </div>
            <div class="mb-3">
                
                <input type="text" class="form-control" id="complement" placeholder="Complément d'adresse">
            </div>
            <div class="row mb-3">
                <div class="col-md-6">
                   
                    <input type="text" class="form-control" id="codePostal" placeholder="Code postal" required>
                </div>
                <div class="col-md-6">
                 
                    <input type="text" class="form-control" id="ville" placeholder="Ville" required>
                </div>
            </div>
            <div class="mb-3">
               
                <input type="email" class="form-control" id="email" placeholder="Votre adresse mail" required>
            </div>
            <div class="mb-3">
               
                <input type="password" class="form-control" id="motDePasse" placeholder="Votre mot de passe" required>
            </div>
            <div class="mb-3">
              
                <input type="password" class="form-control" id="confirmMotDePasse" placeholder="Confirmez votre mot de passe" required>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-dark">S'inscrire</button>
            </div>
        </form>
    </div>
</body>
</html>
