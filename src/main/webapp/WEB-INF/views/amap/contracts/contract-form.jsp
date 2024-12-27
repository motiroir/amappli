<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<body>
	<div>
		<%@ include file="/WEB-INF/views/amap/common/sidebarAdmin.jsp"%>
	</div>
	<div class="content" style="margin-left: 150px;">
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="form-container">
						<div class="header-container">
							<a href="<c:url value='/amap/contracts/list' />" class="btn-back">
								<i class="bi bi-arrow-left-circle"></i>
							</a>
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Ajouter
								un contrat</h2>
						</div>
						<form:form method="POST" action="/Amappli/amap/contracts/add"
							enctype="multipart/form-data">
							<div class="row">
								<!-- Première colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="contractName" class="form-label">Nom du
											panier</label> <input type="text" class="form-control"
											id="contractName" name="contractName"
											placeholder="Exemple : Panier d'hiver">
									</div>
									<div class="mb-3">
										<label for="contractType" class="form-label">Type de
											panier</label> <select class="form-select form-control"
											id="contractType" name="contractType">
											<option selected disabled>Fruits, légumes, mixte ?</option>
											<option value="FRUITS_CONTRACT">Panier de fruits</option>
											<option value="VEGETABLES_CONTRACT">Panier de
												légumes</option>
											<option value="MIX_CONTRACT">Panier mixte</option>
										</select>
									</div>
									<div class="mb-3">
										<label for="contractWeight" class="form-label">Taille
											du panier</label> <select class="form-select form-control"
											id="contractWeight" name="contractWeight">
											<option selected disabled>Petit, moyen, grand ?</option>
											<option value="SMALL">Petit</option>
											<option value="AVERAGE">Moyen</option>
											<option value="BIG">Grand</option>
										</select>
									</div>
									<div class="mb-3">
										<label for="producer" class="form-label">Producteur</label> <select
											class="form-select form-control" id="producer"
											name="producer">
											<option selected disabled></option>
										</select>
									</div>
									<div class="mb-3">
										<label for="startDate" class="form-label">Date de début du contrat</label> <input type="date" class="form-control"
											id="startDate" name="startDate" min="${currentDate}">
									</div>
									<div class="mb-3">
										<label for="endDate" class="form-label">Date de fin du
											contrat</label> <input type="date" class="form-control" id="endDate"
											name="endDate">
									</div>
								</div>

								<!-- Deuxième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="contractDescription" class="form-label">Produits
											composant le panier</label>
										<textarea class="form-control" id="contractDescription"
											name="contractDescription" rows="5"
											placeholder="Pommes, carottes, oignons..."></textarea>
									</div>
									<div class="mb-3">
										<label for="contractPrice" class="form-label">Prix de
											vente</label>
										<div class="input-group">
											<input type="number" class="form-control" id="contractPrice"
												name="contractPrice" step="0.01" placeholder="Prix">
											<span class="input-group-text">€</span>
										</div>
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
									<div class="mb-3">
										<label for="quantity" class="form-label">Quantité
											disponible entre chaque livraison</label>
										<div class="input-group">
											<input type="number" class="form-control" id="quantity"
												name="quantity" step="1.00" placeholder="0"> <span
												class="input-group-text">paniers</span>
										</div>
									</div>
								</div>

								<!-- Troisième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="image" class="form-label">Photo du panier</label>
										<input type="file" class="form-control" id="image"
											name="image" accept="image/png,image/jpeg,image/svg">
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
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='/resources/js/contract-form.js' />"></script>
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
