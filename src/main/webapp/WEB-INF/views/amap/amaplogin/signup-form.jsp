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
	href="<c:url value='/resources/css/amap/signup-form.css' />">
</head>

<body>
	<div class="container py-5">
		<div class="d-flex justify-content-between align-items-center mb-4">
			<h1>Formulaire d'inscription</h1>
			<a href="login" class="btn-close" aria-label="Retour"> </a>
		</div>

		<form
			action="${pageContext.request.contextPath}/tenancies/${tenancyId}/amap/amaplogin/signup"
			method="post">

			<!-- Affichage des erreurs générales -->
			<c:if test="${not empty error}">
				<div class="alert alert-danger">
					<strong>Erreur :</strong> ${error}
				</div>
			</c:if>

			<!-- Champs pour le formulaire -->
			<div class="row mb-3">
				<div class="col-md-6">
					<input type="text" class="form-control" id="nom"
						name="contactInfo.name" placeholder="Votre nom" required
						value="${userDTO.contactInfo.name}">
					<c:if test="${not empty errors['contactInfo.name']}">
						<div class="text-danger">${errors['contactInfo.name']}</div>
					</c:if>
				</div>
				<div class="col-md-6">
					<input type="text" class="form-control" id="prenom"
						name="contactInfo.firstName" placeholder="Votre prénom" required
						value="${userDTO.contactInfo.firstName}">
					<c:if test="${not empty errors['contactInfo.firstName']}">
						<div class="text-danger">${errors['contactInfo.firstName']}</div>
					</c:if>
				</div>
			</div>

			<div class="mb-3">
				<input type="tel" class="form-control" id="telephone"
					name="contactInfo.phoneNumber"
					placeholder="Votre numéro de téléphone"
					value="${userDTO.contactInfo.phoneNumber}">
				<c:if test="${not empty errors['contactInfo.phoneNumber']}">
					<div class="text-danger">${errors['contactInfo.phoneNumber']}</div>
				</c:if>
			</div>

			<div class="mb-3">
				<input type="text" class="form-control" id="adresse"
					name="address.line1" placeholder="Votre adresse" required
					value="${userDTO.address.line1}">
				<c:if test="${not empty errors['address.line1']}">
					<div class="text-danger">${errors['address.line1']}</div>
				</c:if>
			</div>

			<div class="mb-3">
				<input type="text" class="form-control" id="complement"
					name="address.line2" placeholder="Complément d'adresse"
					value="${userDTO.address.line2}">
			</div>

			<div class="row mb-3">
				<div class="col-md-6">

					<input type="text" class="form-control" id="codePostal"
						name="address.postCode" placeholder="Code postal" required
						value="${userDTO.address.postCode}">
					<c:if test="${not empty errors['address.postCode']}">
						<div class="text-danger">${errors['address.postCode']}</div>
					</c:if>
				</div>
				<div class="col-md-6">
					<input type="text" class="form-control" id="ville"
						name="address.city" placeholder="Ville" required
						value="${userDTO.address.city}">
					<c:if test="${not empty errors['address.city']}">
						<div class="text-danger">${errors['address.city']}</div>
					</c:if>
				</div>
			</div>

			<div class="mb-3">
				<input type="email" class="form-control" id="email" name="email"
					placeholder="Votre adresse mail" required value="${userDTO.email}">
				<c:if test="${not empty errors['email']}">
					<div class="text-danger">${errors['email']}</div>
				</c:if>
			</div>

			<div class="mb-3">
				<input type="password" class="form-control" id="motDePasse"
					name="password" placeholder="Votre mot de passe" required>
				<c:if test="${not empty errors['password']}">
					<div class="text-danger">${errors['password']}</div>
				</c:if>
			</div>

			<div class="mb-3">
				<input type="password" class="form-control" id="confirmMotDePasse"
					name="confirmPassword" placeholder="Confirmez votre mot de passe"
					required>
				<c:if test="${not empty errors['confirmPassword']}">
					<div class="text-danger">${errors['confirmPassword']}</div>
				</c:if>
			</div>

			<div class="text-center">
				<button type="submit" class="btn btn-dark">S'inscrire</button>
			</div>
		</form>
	</div>
</body>
</html>
