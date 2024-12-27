<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String currentMainMenu = "products"; // Détermine la rubrique active
String currentPage = "products"; // Détermine la sous-rubrique active
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ajouter un Produit</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<style>
.header-container {
	display: flex;
	align-items: center;
	gap: 10px;
}
</style>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/views/amap/back/common/sidebarAdmin.jsp"%>
	</div>
	<div class="content" style="margin-left: 150px;">
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="form-container">
						<div class="header-container">
							<a href="<c:url value='/amap/products/list' />" class="btn-back">
								<i class="bi bi-arrow-left-circle"></i>
							</a>
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Ajouter un produit</h2>
						</div>
						<form:form method="POST" action="/Amappli/amap/products/add" enctype="multipart/form-data">
							<div class="row">
								<!-- Première colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="productName" class="form-label">Nom du produit</label>
										<input type="text" class="form-control" id="productName" name="productName" placeholder="Exemple : Miel d'acacia">
									</div>
																		<div class="mb-3">
										<label for="productDescription" class="form-label">Description du produit</label>
										<textarea class="form-control" id="productDescription"
											name="productDescription" rows="8"
											placeholder="Pour donner envie aux adhérents, indiquez les ingrédients, la recette, la méthode de préparation etc..."></textarea>
									</div>
									<div class="mb-3">
										<label for="productPrice" class="form-label">Prix de vente</label>
										<div class="input-group">
											<input type="number" class="form-control" id="productPrice" name="productPrice" step="0.01" placeholder="Prix">
											<span class="input-group-text">€</span>
										</div>
									</div>
								</div>

								<!-- Deuxième colonne -->
								<div class="col-md-4">
																	<div class="mb-3">
										<label for="productStock" class="form-label">Quantité en stock</label>
										<input type="number" class="form-control" id="productStock" name="productStock" placeholder="Stock disponible">
									</div>
									<div class="mb-3">
										<label for="fabricationDate" class="form-label">Date de fabrication</label>
										<input type="date" class="form-control" id="fabricationDate" name="fabricationDate">
									</div>
									<div class="mb-3">
										<label for="expirationDate" class="form-label">Date d'expiration</label>
										<input type="date" class="form-control" id="expirationDate" name="expirationDate">
									</div>
																		<div class="mb-3">
										<label for="deliveryRecurrence" class="form-label">Fréquence
											de livraison au point de collecte</label> <select
											class="form-select form-control" id="deliveryRecurrence"
											name="deliveryRecurrence">
											<option selected disabled></option>
											<option value="WEEKLY">Hebdomadaire</option>
											<option value="BIMONTHLY">Bimensuel</option>
											<option value="MONTHLY">Mensuel</option>
										</select>
									</div>
									<div class="mb-3">
										<label for="deliveryDay" class="form-label">Jour de
											livraison au point de collecte</label> <select
											class="form-select form-control" id="deliveryDay"
											name="deliveryDay">
											<option selected disabled></option>
											<option value="MONDAY">Lundi</option>
											<option value="TUESDAY">Mardi</option>
											<option value="WEDNESDAY">Mercredi</option>
											<option value="THURSDAY">Jeudi</option>
											<option value="FRIDAY">Vendredi</option>
											<option value="SATURDAY">Samedi</option>
											<option value="SUNDAY">Dimanche</option>
										</select>
									</div>
								</div>

								<!-- Troisième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="image" class="form-label">Photo du produit</label>
										<input type="file" class="form-control" id="image" name="image" accept="image/png,image/jpeg,image/svg">
									</div>
									<div class="mb-3 text-center">
										<img src="https://via.placeholder.com/150" alt="Aperçu du produit" class="image-preview">
									</div>
									<div class="text-center">
										<button type="submit" class="btn btn-custom btn-lg me-2" style="width: 50%; height: 60px;">Ajouter</button>
										<a href="/Amappli/amap/products/list" class="btn btn-secondary btn-lg" style="width: 40%; height: 60px; color: black; background-color: white;">Annuler</a>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='/resources/js/product-form.js' />"></script>
	<script>document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("image");
    const previewImage = document.querySelector(".image-preview");

    if (fileInput && previewImage) {
        fileInput.addEventListener("change", function (event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();

                reader.onload = function (e) {
                    previewImage.src = e.target.result; // Met à jour l'URL de l'image
                };

                reader.readAsDataURL(file); // Lit le fichier comme une URL de données
            }
        });
    }
});</script>
	
</body>
</html>
