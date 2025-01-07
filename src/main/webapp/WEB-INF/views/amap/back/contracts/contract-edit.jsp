<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>Modifier un Contrat</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
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
							<a
								href="<c:url value='/amap/${tenancyAlias}/admin/contracts/list' />"
								class="btn-back"> <i class="bi bi-arrow-left-circle"></i>
							</a>
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Modifier
								un contrat</h2>
						</div>
						<form:form id="contractForm" name="contractForm" method="POST"
							action="${pageContext.request.contextPath}/amap/${tenancyAlias}/admin/contracts/update"
							modelAttribute="contract" enctype="multipart/form-data">

							<form:hidden path="id" />
							<div class="row">
								<!-- Première colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="contractName" class="form-label">Nom du
											panier</label>
										<form:input path="contractName" class="form-control" />
									</div>
									<div class="mb-3">
										<label for="contractType" class="form-label">Type de
											panier</label>
										<form:select path="contractType"
											class="form-select form-control">
											<c:forEach var="type" items="${contractTypes}">
												<form:option value="${type}" label="${type.displayName}" />
											</c:forEach>
										</form:select>
									</div>
									<div class="mb-3">
										<label for="contractWeight" class="form-label">Taille
											du panier</label>
										<form:select path="contractWeight"
											class="form-select form-control">
											<c:forEach var="weight" items="${contractWeights}">
												<form:option value="${weight}" label="${weight.displayName}" />
											</c:forEach>
										</form:select>
									</div>
									<div class="mb-3">
										<label for="userId" class="form-label">Fournisseur :</label> <select
											id="userId" name="userId" class="form-select" required>
											<option value=""
												<c:if test="${contract.user == null}">selected</c:if>>Choisir
												un fournisseur</option>
											<c:forEach var="user" items="${users}">
												<option value="${user.userId}"
													<c:if test="${contract.user != null && contract.user.userId == user.userId}">selected</c:if>>
													${user.companyDetails.companyName}</option>
											</c:forEach>
										</select>
										<div class="invalid-feedback"></div>
									</div>

									<div class="mb-3">
										<label for="startDate" class="form-label">Date de
											début du contrat</label>
										<form:input path="startDate" type="date" class="form-control" />
									</div>
									<div class="mb-3">
										<label for="endDate" class="form-label">Date de fin du
											contrat</label>
										<form:input path="endDate" type="date" class="form-control" />
									</div>
								</div>

								<!-- Deuxième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="contractDescription" class="form-label">Produits
											composant le panier</label>
										<form:textarea path="contractDescription" rows="3"
											class="form-control" placeholder="Description des produits"></form:textarea>
									</div>
									<div class="mb-3">
										<label for="contractPrice" class="form-label">Prix de
											vente</label>
										<div class="input-group">
											<form:input path="contractPrice" type="number" step="0.01"
												class="form-control" placeholder="Prix" />
											<span class="input-group-text">€</span>
										</div>
									</div>
									<div class="mb-3">
										<label for="deliveryRecurrence" class="form-label">Fréquence
											de livraison</label>
										<form:select path="deliveryRecurrence"
											class="form-select form-control">
											<c:forEach var="recurrence" items="${deliveryRecurrence}">
												<form:option value="${recurrence}"
													label="${recurrence.displayName}" />
											</c:forEach>
										</form:select>
									</div>
									<div class="mb-3">
										<label for="deliveryDay" class="form-label">Jour de
											livraison</label>
										<form:select path="deliveryDay"
											class="form-select form-control">
											<c:forEach var="day" items="${deliveryDay}">
												<form:option value="${day}" label="${day.displayName}" />
											</c:forEach>
										</form:select>
									</div>
									<div class="mb-3">
										<label for="quantity" class="form-label">Quantité</label>
										<div class="input-group">
											<form:input path="quantity" type="number" step="1"
												class="form-control" placeholder="Quantité" />
											<span class="input-group-text">paniers</span>
										</div>
									</div>
								</div>

								<!-- Troisième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label for="image" class="form-label">Photo du panier</label>
										<input type="file" class="form-control" id="imageInput"
											name="image" accept="image/png,image/jpeg,image/svg">
									</div>
									<div class="mb-3 text-center">
										<c:if test="${not empty contract.imageData}">
											<img id="imagePreview"
												src="data:${contract.imageType};base64,${contract.imageData}"
												alt="Aperçu du contrat" class="image-preview">
										</c:if>
									</div>
									<div>Adresse du point de collecte : ${address.line1}
										${address.line2}, ${address.city} (${address.postCode})</div>
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
		document.addEventListener("DOMContentLoaded", function () {
    const imageInput = document.getElementById('imageInput');
    const imagePreview = document.getElementById('imagePreview');

    if (imageInput && imagePreview) {
        imageInput.addEventListener('change', function () {
            const file = imageInput.files[0];
            if (file) {
                const reader = new FileReader();

                // Charger l'image et mettre à jour la preview
                reader.onload = function (e) {
                    imagePreview.src = e.target.result;
                };

                // Lire le contenu du fichier
                reader.readAsDataURL(file);
            }
        });
    }
});</script>
		
</body>
</html>
