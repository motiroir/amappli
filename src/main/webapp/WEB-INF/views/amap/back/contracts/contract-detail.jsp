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
<title>Détails du Contrat</title>
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

.form-control:read-only {
	background-color: #f8f9fa;
	color: #6c757d;
	border: none;
	cursor: not-allowed;
}
</style>
</head>
<body class="row theme-1 light">
	<header class="fc-main bg-main">
		<!-- Inclusion du header -->
		<jsp:include page="../common/headerAdmin.jsp" />
	</header>
	<jsp:include page="../common/sidebarAdmin.jsp" />
	<div class="content" style="margin-left: 150px;">
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="form-container">
						<div class="header-container">
							<a href="<c:url value='/${tenancyAlias}/backoffice/contracts/list' />" class="btn-back">
								<i class="bi bi-arrow-left-circle"></i>
							</a>
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Détails
								du contrat</h2>
							<a
								href="<c:url value='/${tenancyAlias}/backoffice/contracts/edit/${contract.id}'/>"
								class="btn btn-primary"> Modifier le contrat </a>
						</div>
						<form>
							<div class="row">
								<!-- Première colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label class="form-label">Nom du panier</label> <input
											type="text" class="form-control"
											value="${contract.contractName}" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Type de panier</label> <input
											type="text" class="form-control"
											value="${contract.contractType.displayName}" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Taille du panier</label> <input
											type="text" class="form-control"
											value="${contract.contractWeight.displayName}" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Producteur</label> <input
											type="text" class="form-control" value="Producteur exemple"
											readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Date de début du contrat</label> <input
											type="date" class="form-control"
											value="${contract.startDate}" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Date de fin du contrat</label> <input
											type="date" class="form-control" value="${contract.endDate}"
											readonly>
									</div>
								</div>

								<!-- Deuxième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label class="form-label">Produits composant le panier</label>
										<textarea class="form-control" rows="3" readonly>${contract.contractDescription}</textarea>
									</div>
									<div class="mb-3">
										<label class="form-label">Prix de vente</label> <input
											type="text" class="form-control"
											value="${contract.contractPrice}€" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Fréquence de livraison</label> <input
											type="text" class="form-control"
											value="${contract.deliveryRecurrence.displayName}" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Jour de livraison</label> <input
											type="text" class="form-control"
											value="${contract.deliveryDay.displayName}" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Quantité disponible</label> <input
											type="text" class="form-control"
											value="${contract.quantity} paniers" readonly>
									</div>
								</div>
								<!-- Troisième colonne -->
								<div class="col-md-4">
									<div class="mb-3 text-center">
										<c:if test="${not empty contract.imageData}">
											<img
												src="data:${contract.imageType};base64,${contract.imageData}"
												alt="Image du contrat"
												style="max-width: 100%; border-radius: 8px; object-fit: cover;">
										</c:if>
										<div class="mb-3">
											<label class="form-label">Date de création le
												${contract.dateCreation}</label>
										</div>
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
	<script src="<d:url value='/resources/js/amap/admin/user-list.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />"
		type="text/javascript"></script>
</body>
</html>
