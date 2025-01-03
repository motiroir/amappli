<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String currentMainMenu = "products"; // Détermine la rubrique active
String currentPage = "workshops"; // Détermine la sous-rubrique active
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ajouter un Atelier</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
.header-container {
	display: flex;
	align-items: center;
	gap: 10px;
}
</style>
</head>
<body class="row theme-1 light">
	<header class="fc-main bg-main">
		<!-- Inclusion du header -->
		<jsp:include page="../common/headerAdmin.jsp" />
	</header>
	<jsp:include page="../common/sidebarAdmin.jsp" />
	<div class="content col">
		<div class="container-fluid mt-5">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="form-container">
						<div class="header-container">
							<a
								href="<c:url value='${tenancyAlias}/backoffice/workshops/list' />"
								class="btn-back"> <i class="bi bi-arrow-left-circle"></i>
							</a>
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Ajouter
								un atelier</h2>
						</div>
						<form:form method="POST"
							action="${pageContext.request.contextPath}/${tenancyAlias}/backoffice/workshops/add"
							enctype="multipart/form-data">
							<input type="hidden" id="tenancyAlias" name="tenancyAlias"
								value="${tenancyAlias}">
							<div class="row">
								<!-- Première colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="workshopName" class="form-label">Nom de
											l'atelier</label> <input type="text" class="form-control"
											id="workshopName" name="workshopName"
											placeholder="Exemple : Atelier cuisine">
									</div>
									<div class="mb-3">
										<label for="userId" class="form-label">Sélectionnez un
											fournisseur :</label> <select id="userId" name="userId"
											class="form-select">
											<option value="" selected>Choisir un intervenant</option>
											<c:forEach var="user" items="${users}">
												<option value="${user.userId}">
													${user.companyDetails.companyName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="mb-3">
										<label for="workshopDescription" class="form-label">Description
											de l'atelier</label>
										<textarea class="form-control" id="workshopDescription"
											name="workshopDescription" rows="10"
											placeholder="Décrivez les objectifs, le contenu, les intervenants, etc..."></textarea>
									</div>
								</div>

								<!-- Deuxième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="workshopDateTime" class="form-label">Date
											et heure de l'atelier</label> <input type="datetime-local"
											class="form-control" id="workshopDateTime"
											name="workshopDateTime">
									</div>

									<div class="mb-3">
										<label for="workshopDuration" class="form-label">Durée
											de l'atelier (en minutes)</label> <input type="number"
											class="form-control" id="workshopDuration"
											name="workshopDuration" placeholder="Durée en minutes">
									</div>
									<div class="mb-3">
										<label for="minimumParticipants" class="form-label">Participants
											minimum</label> <input type="number" class="form-control"
											id="minimumParticipants" name="minimumParticipants"
											placeholder="Nombre minimum">
									</div>
									<div class="mb-3">
										<label for="maximumParticipants" class="form-label">Participants
											maximum</label> <input type="number" class="form-control"
											id="maximumParticipants" name="maximumParticipants"
											placeholder="Nombre maximum">
									</div>

									<!-- Troisième colonne -->
									<div class="col-md-4">
										<div class="mb-3">
											<label for="workshopPrice" class="form-label">Prix de
												l'atelier par personne</label>
											<div class="input-group">
												<input type="number" class="form-control" id="workshopPrice"
													name="workshopPrice" step="0.01" placeholder="Prix">
												<span class="input-group-text">€</span>
											</div>
										</div>
										<div class="mb-3">
											<label for="image" class="form-label">Photo de
												l'atelier</label> <input type="file" class="form-control" id="image"
												name="image" accept="image/png,image/jpeg,image/svg">
										</div>
										<div class="mb-3 text-center">
											<img src="https://via.placeholder.com/150"
												alt="Aperçu de l'atelier" class="image-preview">
										</div>
										<div class="text-center">
											<button type="submit" class="btn btn-custom btn-lg me-2"
												style="width: 50%; height: 60px;">Ajouter</button>
										</div>
									</div>
								</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/user-list.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />"
		type="text/javascript"></script>
	<script>
		document
				.addEventListener(
						"DOMContentLoaded",
						function() {
							const fileInput = document.getElementById("image");
							const previewImage = document
									.querySelector(".image-preview");
							const workshopDateTime = document
									.getElementById("workshopDateTime");
							const minParticipantsInput = document
									.getElementById("minimumParticipants");
							const maxParticipantsInput = document
									.getElementById("maximumParticipants");

							// Initialiser la date et heure minimale pour le champ "datetime-local"
							function initializeMinDateTime() {
								const now = new Date();
								now.setDate(now.getDate() + 1); // Ajouter 1 jour pour définir le lendemain
								now.setHours(0, 0, 0, 0); // Réinitialiser les heures, minutes, secondes et millisecondes

								const year = now.getFullYear();
								const month = String(now.getMonth() + 1)
										.padStart(2, "0");
								const day = String(now.getDate()).padStart(2,
										"0");
								const hours = "09"; // Heure par défaut à 09h00
								const minutes = "00";

								const defaultDateTime = `${year}-${month}-${day}T${hours}:${minutes}`;
								workshopDateTime.value = defaultDateTime;
								workshopDateTime.setAttribute("min",
										defaultDateTime);
							}

							// Prévisualisation de l'image
							if (fileInput && previewImage) {
								fileInput.addEventListener("change", function(
										event) {
									const file = event.target.files[0];
									if (file) {
										const reader = new FileReader();

										reader.onload = function(e) {
											previewImage.src = e.target.result; // Met à jour l'URL de l'image
										};

										reader.readAsDataURL(file); // Lit le fichier comme une URL de données
									}
								});
							}

							// Validation des participants
							document
									.querySelector("form")
									.addEventListener(
											"submit",
											function(e) {
												const minParticipants = parseInt(
														minParticipantsInput.value,
														10);
												const maxParticipants = parseInt(
														maxParticipantsInput.value,
														10);

												if (maxParticipants < minParticipants) {
													e.preventDefault();
													alert("Le nombre de participants maximum doit être supérieur ou égal au nombre de participants minimum.");
												}
											});

							// Initialiser la date minimale pour le champ datetime-local
							if (workshopDateTime) {
								initializeMinDateTime();
							}
						});
	</script>
</body>
</html>
