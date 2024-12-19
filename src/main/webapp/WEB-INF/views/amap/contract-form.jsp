<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%
String currentMainMenu = "products"; // Détermine la rubrique active
String currentPage = "contracts"; // Détermine la sous-rubrique active
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ajouter un Contrat</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />"
	rel="stylesheet">
<style>
.form-container {
	background-color: #fff;
	border-radius: 8px;
}

.form-control {
	border-radius: 20px;
	border: 1px solid #000000;
	color: #000000;
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

</style>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/views/amap/common/sidebarAdmin.jsp"%>
	</div>
	<div class="content" style="margin-left: 150px;">
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="form-container">
						<h2 class="mb-4" style="font-weight: bold; text-align: left;">Ajouter
							un contrat</h2>
						<form method="POST" action="/Amappli/amap/contracts/add">
							<div class="row">
								<!-- Première colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<input type="text" class="form-control" id="contractName"
											name="contractName" placeholder="Nom du contrat">
									</div>
									<div class="mb-3">
										<select class="form-select form-control" id="contractType"
											name="contractType">
											<option selected disabled>Type de panier</option>
											<option value="FRUITS_CONTRACT">Panier de fruits</option>
											<option value="VEGETABLES_CONTRACT">Panier de
												légumes</option>
											<option value="MIX_CONTRACT">Panier mixte</option>
										</select>
									</div>
									<div class="mb-3">
										<select class="form-select form-control" id="producer"
											name="producer">
											<option selected disabled>Producteur</option>
										</select>
									</div>
									<div class="mb-3">
										<input type="date" class="form-control" id="startDate"
											name="startDate" min="${currentDate}">
									</div>
									<div class="mb-3">
										<input type="date" class="form-control" id="endDate"
											name="endDate">
									</div>
								</div>

								<!-- Deuxième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<textarea class="form-control" id="contractDescription"
											name="contractDescription" rows="3"
											placeholder="Description des produits"></textarea>
									</div>
									<div class="mb-3">
										<select class="form-select form-control" id="contractWeight"
											name="contractWeight">
											<option selected disabled>Taille du panier</option>
											<option value="SMALL">Petit</option>
											<option value="AVERAGE">Moyen</option>
											<option value="BIG">Grand</option>
										</select>
									</div>
									<div class="mb-3">
										<div class="input-group">
											<input type="number" class="form-control" id="contractPrice"
												name="contractPrice" step="0.01" placeholder="Prix">
											<span class="input-group-text">€</span>
										</div>
									</div>
									<div class="mb-3">
										<input type="number" class="form-control" id="quantity"
											name="quantity" placeholder="Quantité">
									</div>
								</div>

								<!-- Troisième colonne -->
								<div class="col-md-4">
									<div class="mb-3 d-flex align-items-center">
										<input type="text" class="form-control me-2" id="imageUrl"
											name="imageUrl" placeholder="URL de l'image">
										<button type="button" class="btn btn-secondary">Ajouter</button>
									</div>

									<div class="mb-3 text-center">
										<img src="https://via.placeholder.com/150"
											alt="Aperçu du produit" class="image-preview">
									</div>
									<div class="text-center">
										<button type="submit" class="btn btn-custom btn-lg me-2"
											style="width: 50%; height: 60px;">Ajouter</button>
										<a href="/Amappli/amap/contracts/list"
											class="btn btn-secondary btn-lg"
											style="width: 40%; height: 60px; color: black; background-color: white;">Annuler</a>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='/resources/js/contract-form.js' />"></script>
</body>
</html>
