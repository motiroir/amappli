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
	<link href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />" rel="stylesheet">
<style>
.form-container {
	background-color: #fff;
	border-radius: 8px;
	padding: 30px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
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
</style>
</head>
<body>
    <div>
        <%@ include file="/WEB-INF/views/amap/back/common/sidebarAdmin.jsp"%>
    </div>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-lg-10">
				<div class="form-container">
					<h2 class="mb-4" style="font-weight: bold; text-align: left;">Modifier
						un atelier</h2>
					<form:form name="workshopForm" method="POST"
						action="/Amappli/amap/workshops/update" modelAttribute="workshop"
						enctype="multipart/form-data">
						<form:hidden path="id" />
						<div class="row">
							<!-- Première colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label for="workshopName" class="form-label">Nom de l'atelier</label>
									<form:input path="workshopName" class="form-control" placeholder="Nom de l'atelier" />
								</div>
								<div class="mb-3">
									<label for="workshopDescription" class="form-label">Description</label>
									<form:textarea path="workshopDescription" rows="3" class="form-control" placeholder="Description de l'atelier"></form:textarea>
								</div>
								<div class="mb-3">
									<label for="workshopDateTime" class="form-label">Date et Heure</label>
									<form:input path="workshopDateTime" type="datetime-local" class="form-control" />
								</div>
							</div>

							<!-- Deuxième colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label for="workshopPrice" class="form-label">Prix</label>
									<div class="input-group">
										<form:input path="workshopPrice" type="number" step="0.01" class="form-control" placeholder="Prix" />
										<span class="input-group-text">€</span>
									</div>
								</div>
								<div class="mb-3">
									<label for="workshopDuration" class="form-label">Durée (en minutes)</label>
									<form:input path="workshopDuration" type="number" class="form-control" placeholder="Durée" />
								</div>
								<div class="mb-3">
									<label for="location" class="form-label">Lieu</label>
									<form:input path="location" class="form-control" placeholder="Lieu de l'atelier" />
								</div>
							</div>

							<!-- Troisième colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label for="minimumParticipants" class="form-label">Participants minimum</label>
									<form:input path="minimumParticipants" type="number" class="form-control" placeholder="Nombre minimum" />
								</div>
								<div class="mb-3">
									<label for="maximumParticipants" class="form-label">Participants maximum</label>
									<form:input path="maximumParticipants" type="number" class="form-control" placeholder="Nombre maximum" />
								</div>
								<div class="mb-3">
									<label for="image" class="form-label">Photo de l'atelier</label>
									<input type="file" class="form-control" id="image" name="image" accept="image/png,image/jpeg,image/svg">
								</div>
								<div class="mb-3 text-center">
									<c:if test="${not empty workshop.imageData}">
										<img src="data:${workshop.imageType};base64,${workshop.imageData}" alt="Aperçu de l'atelier" class="image-preview" style="max-width: 100%; border-radius: 8px;">
									</c:if>
								</div>
								<div class="text-end">
									<button type="submit" class="btn btn-custom btn-lg" style="width: 250px; height: 60px; margin-bottom: 15px;">Valider les modifications</button>
									<a href="/Amappli/amap/workshops/list" style="width: 150px; height: 40px; margin-left: 10px;">Annuler</a>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
</body>
</html>
