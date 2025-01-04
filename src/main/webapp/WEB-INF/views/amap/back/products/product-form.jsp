<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
.image-preview {
    width: 150px; /* Taille fixe */
    height: 150px; /* Taille fixe */
    object-fit: cover; /* Recadre l'image pour remplir la zone tout en respectant le ratio d'aspect */
    border-radius: 8px; /* Facultatif : pour un effet arrondi */
    border: 1px solid #ddd; /* Facultatif : pour une bordure légère */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Facultatif : pour un effet ombré */
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
								href="<c:url value='/${tenancyAlias}/backoffice/products/list' />"
								class="btn-back"> <i class="bi bi-arrow-left-circle"></i>
							</a>
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Ajouter
								un produit</h2>
						</div>
						<form:form method="POST"
							action="${pageContext.request.contextPath}/${tenancyAlias}/backoffice/products/add"
							enctype="multipart/form-data">
							<input type="hidden" id="tenancyAlias" name="tenancyAlias"
								value="${tenancyAlias}">
							<div class="row">
								<!-- Première colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="productName" class="form-label">Nom du
											produit</label> <input type="text" class="form-control"
											id="productName" name="productName" required
											placeholder="Exemple : Miel d'acacia">
											<div class="invalid-feedback"></div>
									</div>
									<div class="mb-3">
										<label for="userId" class="form-label">Fournisseur :</label> <select
											id="userId" name="userId" class="form-select" required>
											<option value="" selected>Choisir un fournisseur</option>
											<c:forEach var="user" items="${users}">
												<option value="${user.userId}">
													${user.companyDetails.companyName}</option>
											</c:forEach>
										</select>
										<div class="invalid-feedback"></div>
									</div>
									<div class="mb-3">
										<label for="productDescription" class="form-label">Description
											du produit</label>
										<textarea class="form-control" id="productDescription"
											name="productDescription" rows="8" required
											placeholder="Pour donner envie aux adhérents, indiquez les ingrédients, la méthode de préparation etc..."></textarea>
										<div class="invalid-feedback"></div>
									</div>
									<div class="mb-3">
										<label for="productPrice" class="form-label">Prix de
											vente</label>
										<div class="input-group">
											<input type="number" class="form-control" id="productPrice"
												name="productPrice" step="0.10" placeholder="0.00" required>
											<span class="input-group-text">€</span>
										</div>
										<div class="invalid-feedback"></div>
									</div>
								</div>

								<!-- Deuxième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="productStock" class="form-label">Stock</label> <input
											type="number" class="form-control" id="productStock"
											name="productStock" required placeholder="Stock disponible">
										<div class="invalid-feedback"></div>
									</div>
									<div class="mb-3">
										<label for="fabricationDate" class="form-label">Date
											de fabrication</label> <input type="date" class="form-control"
											id="fabricationDate" name="fabricationDate" required>
										<div class="invalid-feedback"></div>
									</div>
									<div class="mb-3">
										<label for="expirationDate" class="form-label">Date
											d'expiration</label> <input type="date" class="form-control"
											id="expirationDate" name="expirationDate" required>
										<div class="invalid-feedback"></div>
									</div>
									<div class="mb-3">
										<label for="deliveryRecurrence" class="form-label">Fréquence
											de livraison au point de collecte</label> <select
											class="form-select form-control" id="deliveryRecurrence"
											name="deliveryRecurrence" required>
											<option selected disabled></option>
											<option value="WEEKLY">Hebdomadaire</option>
											<option value="BIMONTHLY">Bimensuel</option>
											<option value="MONTHLY">Mensuel</option>
										</select>
										<div class="invalid-feedback"></div>
									</div>
									<div class="mb-3">
										<label for="deliveryDay" class="form-label">Jour de
											livraison au point de collecte</label> <select
											class="form-select form-control" id="deliveryDay"
											name="deliveryDay" required>
											<option selected disabled></option>
											<option value="MONDAY">Lundi</option>
											<option value="TUESDAY">Mardi</option>
											<option value="WEDNESDAY">Mercredi</option>
											<option value="THURSDAY">Jeudi</option>
											<option value="FRIDAY">Vendredi</option>
											<option value="SATURDAY">Samedi</option>
											<option value="SUNDAY">Dimanche</option>
										</select>
										<div class="invalid-feedback"></div>
									</div>
								</div>

								<!-- Troisième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="image" class="form-label">Photo du produit</label>
										<input type="file" class="form-control" id="image"
											name="image" accept="image/png,image/jpeg,image/svg" required>
										<div class="invalid-feedback"></div>
									</div>
									<div class="mb-3 text-center">
										<img src="https://via.placeholder.com/150"
											alt="Aperçu du produit" class="image-preview">
									</div>
									<div class="text-center">
										<button type="submit" class="btn btn-custom btn-lg me-2"
											style="width: 50%; height: 60px;">Ajouter</button>
									</div>
									<div class="mb-3">
										<p>
											<strong>La livraison au point de collecte se fera
												obligatoirement à l'adresse suivante :</strong><br>
											<c:if test="${not empty address}">
            ${address.line1} ${address.line2}, ${address.city} (${address.postCode})
        </c:if>
										</p>
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
		document.addEventListener("DOMContentLoaded", function() {
			const fileInput = document.getElementById("image");
			const previewImage = document.querySelector(".image-preview");

			if (fileInput && previewImage) {
				fileInput.addEventListener("change", function(event) {
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
		});
	</script>
	<script>document.addEventListener("DOMContentLoaded", function () {
	    const form = document.querySelector("form");
	    const fabricationDateInput = document.getElementById("fabricationDate");
	    const expirationDateInput = document.getElementById("expirationDate");

	    form.addEventListener("submit", function (event) {
	        const fabricationDate = new Date(fabricationDateInput.value);
	        const expirationDate = new Date(expirationDateInput.value);

	        // Vérifier si expirationDate est bien après fabricationDate
	        if (expirationDate <= fabricationDate) {
	            event.preventDefault(); // Empêche la soumission
	            expirationDateInput.classList.add("is-invalid");
	            const errorMessage = expirationDateInput.nextElementSibling;
	            if (errorMessage && errorMessage.classList.contains("invalid-feedback")) {
	                errorMessage.textContent = "La date d'expiration doit être après la date de fabrication.";
	            }
	        } else {
	            expirationDateInput.classList.remove("is-invalid");
	            const errorMessage = expirationDateInput.nextElementSibling;
	            if (errorMessage && errorMessage.classList.contains("invalid-feedback")) {
	                errorMessage.textContent = "";
	            }
	        }
	    });
	});
		</script>

</body>
</html>
