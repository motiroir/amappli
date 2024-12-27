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
	
	<link rel="stylesheet"
	href="<c:url value='/resources/css/amap/login-amap.css' />">
</head>
<style>

</style>
<body>
	<div class="container py-5">
		<div class="d-flex justify-content-between align-items-center mb-4">
			<h1>Se connecter</h1>
			<a href="/Amappli/tenancies/${tenancyId}/home" class="btn-close" aria-label="Retour"></a>
		</div>

		<form
			action="${pageContext.request.contextPath}/tenancies/${tenancyId}/amap/amaplogin/login"
			method="post">

			<!-- Affichage des erreurs générales -->
			<c:if test="${not empty error}">
				<div class="alert alert-danger">
					<strong>Erreur :</strong> ${error}
				</div>
			</c:if>

			<!-- Champs pour le formulaire -->
			<div class="mb-3">
				<input type="email" class="form-control" id="email" name="email"
					placeholder="Votre adresse mail" required value="${loginDTO.email}">
			</div>

			<div class="mb-3">
				<input type="password" class="form-control" id="motDePasse"
					name="password" placeholder="Votre mot de passe" required>
			</div>

			<div class="text-center">
				<button type="submit" class="btn btn-dark">Se connecter</button>
			</div>
		</form>
		<br>
		<div class="text-center mt-4">
			<p>Pas encore inscrit.e ?</p>
			<hr>
			<a
				href="${pageContext.request.contextPath}/tenancies/${tenancyId}/amap/amaplogin/signup"
				class="btn btn-dark">S'inscrire</a>
		</div>

	</div>
</body>
</html>
