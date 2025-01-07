<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Modifier un Atelier</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />"
	rel="stylesheet">
<style>
.form-container {
	background-color: #fff;
}

.form-control {
	border-radius: 20px;
	border: 1px solid #000000;
	color: #A1A1A1;
}

.form-control::placeholder {
	color: #A1A1A1;
}

.btn-custom {
	background-color: #FFA570;
	color: #000000;
	border: none;
	border-radius: 100px;
}

.btn-custom:hover {
	background-color: #e69500;
}

.image-preview {
	max-width: 100%;
	border-radius: 20px;
}

.btn-secondary {
	background-color: #ccc;
	border-radius: 20px;
}

.image-preview {
	width: 150px; /* Taille fixe */
	height: 150px; /* Taille fixe */
	object-fit: cover;
	/* Recadre l'image pour remplir la zone tout en respectant le ratio d'aspect */
	border-radius: 8px; /* Facultatif : pour un effet arrondi */
	border: 1px solid #ddd; /* Facultatif : pour une bordure légère */
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	/* Facultatif : pour un effet ombré */
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
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Modifier
								un produit</h2>
							<form:form id="workshopForm" name="workshopForm" method="POST"
								action="${pageContext.request.contextPath}/${tenancyAlias}/admin/workshops/update"
								modelAttribute="workshop" enctype="multipart/form-data">
								<form:hidden path="id" />
								<div class="row">
									<!-- Première colonne -->
									<div class="col-md-4">
										<div class="mb-3">
											<label for="workshopName" class="form-label">Nom de
												l'atelier</label>
											<form:input path="workshopName" class="form-control"
												placeholder="Nom de l'atelier" />
										</div>
										<div class="mb-3">
											<label for="userId" class="form-label">Intervenant :</label>
											<select id="userId" name="userId" class="form-select">
												<option value=""
													<c:if test="${workshop.user == null}">selected</c:if>>Choisir
													un intervenant</option>
												<c:forEach var="user" items="${users}">
													<option value="${user.userId}"
														<c:if test="${workshop.user != null && workshop.user.userId == user.userId}">selected</c:if>>
														${user.companyDetails.companyName}</option>
												</c:forEach>
											</select>
											<div class="mb-3">
												<label for="address" class="form-label">Lieu de
													l'atelier :</label>
												<p>
													<c:if test="${not empty address}">
            ${address.line1}, ${address.line2}, ${address.city} (${address.postCode})
        </c:if>
													<c:if test="${empty address}">
														<em>Aucune adresse associée à cet utilisateur.</em>
													</c:if>
												</p>
											</div>

											<div class="mb-3">
												<label for="workshopDescription" class="form-label">Description</label>
												<form:textarea path="workshopDescription" rows="3"
													class="form-control" placeholder="Description de l'atelier"></form:textarea>
											</div>
										</div>

										<!-- Deuxième colonne -->
										<div class="col-md-4">
											<div class="mb-3">
												<label for="workshopDateTime" class="form-label">Date
													et Heure</label> <input id="workshopDateTime"
													name="workshopDateTime" type="datetime-local"
													class="form-control" value="${workshopDateTime}" />
											</div>

											<div class="mb-3">
												<label for="workshopDuration" class="form-label">Durée
													(en minutes)</label>
												<form:input path="workshopDuration" type="number"
													class="form-control" placeholder="Durée" />
											</div>
											<div class="mb-3">
												<label for="minimumParticipants" class="form-label">Participants
													minimum</label>
												<form:input path="minimumParticipants" type="number"
													class="form-control" placeholder="Nombre minimum" />
											</div>
											<div class="mb-3">
												<label for="maximumParticipants" class="form-label">Participants
													maximum</label>
												<form:input path="maximumParticipants" type="number"
													class="form-control" placeholder="Nombre maximum" />
											</div>
										</div>

										<!-- Troisième colonne -->
										<div class="col-md-4">
											<div class="mb-3">
												<label for="workshopPrice" class="form-label">Prix
													par personne</label>
												<div class="input-group">
													<form:input path="workshopPrice" type="number" step="0.01"
														class="form-control" placeholder="Prix" />
													<span class="input-group-text">€</span>
												</div>
											</div>
											<div class="mb-3 text center">
												<label for="image" class="form-label">Photo du
													produit</label> <input type="file" class="form-control"
													id="imageInput" name="image"
													accept="image/png,image/jpeg,image/svg">
											</div>
											<div class="mb-3 text-center">
												<c:if test="${not empty workshop.imageData}">
													<img id="imagePreview"
														src="data:${workshop.imageType};base64,${workshop.imageData}"
														alt="Aperçu de l'atelier" class="image-preview">
												</c:if>
											</div>
											<div class="text-end">
												<button type="submit" class="btn btn-custom btn-lg"
													style="width: 250px; height: 60px; margin-bottom: 15px;">Valider
													les modifications</button>
											</div>
										</div>
									</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/amap/admin/contract-edit.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/user-list.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />"
		type="text/javascript"></script>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			const imageInput = document.getElementById('imageInput');
			const imagePreview = document.getElementById('imagePreview');

			if (imageInput && imagePreview) {
				imageInput.addEventListener('change', function() {
					const file = imageInput.files[0];
					if (file) {
						const reader = new FileReader();

						// Charger l'image et mettre à jour la preview
						reader.onload = function(e) {
							imagePreview.src = e.target.result;
						};

						// Lire le contenu du fichier
						reader.readAsDataURL(file);
					}
				});
			}
		});
	</script>
</body>
</html>
