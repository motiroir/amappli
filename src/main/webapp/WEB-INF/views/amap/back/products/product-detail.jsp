<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Détails du Produit</title>
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
	<div class="content col">
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="form-container">
						<div class="header-container">
							<a
								href="<c:url value='/${tenancyAlias}/backoffice/products/list' />"
								class="btn-back"> <i class="bi bi-arrow-left-circle"></i>
							</a>
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Détails
								du produit</h2>
							<a
								href="<c:url value='/${tenancyAlias}/backoffice/products/edit/${product.id}'/>"
								class="btn btn-primary"> Modifier le contrat </a>
						</div>
						<form>
							<div class="row">
								<!-- Première colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label class="form-label">Nom du produit</label> <input
											type="text" class="form-control"
											value="${product.productName}" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Fournisseur</label> <input
											type="text" class="form-control"
											value="${product.user.companyDetails.companyName}" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Description</label>
										<textarea class="form-control" rows="3" readonly>${product.productDescription}</textarea>
									</div>
									<div class="mb-3">
										<label class="form-label">Prix de vente</label> <input type="text"
											class="form-control" value="${product.productPrice}€"
											readonly>
									</div>
								</div>

								<!-- Deuxième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label class="form-label">Quantité en stock</label> <input
											type="text" class="form-control"
											value="${product.productStock}" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Date de fabrication</label> <input
											type="date" class="form-control"
											value="${product.fabricationDate}" readonly>
									</div>
									<div class="mb-3">
										<label class="form-label">Date d'expiration</label> <input
											type="date" class="form-control"
											value="${product.expirationDate}" readonly>
									</div>
																		<div class="mb-3">
										<label class="form-label">Fréquence de livraison</label> <input
											type="text" class="form-control"
											value="${product.deliveryRecurrence.displayName}" readonly>
									</div>
																		<div class="mb-3">
										<label class="form-label">Jour de livraison</label> <input
											type="text" class="form-control"
											value="${product.deliveryDay.displayName}" readonly>
									</div>
								</div>

								<!-- Troisième colonne -->
								<div class="col-md-4">
									<div class="mb-3 text-center">
										<c:if test="${not empty product.imageData}">
											<img
												src="data:${product.imageType};base64,${product.imageData}"
												alt="Image du produit"
												style="width: 150px; height: 150px; border-radius: 8px; object-fit: cover;">
										</c:if>
									</div>
									<div class="mb-3">
										<label class="form-label">Date de création le
											${formattedDate}</label>
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
	<script src="<c:url value='/resources/js/amap/admin/user-list.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />"
		type="text/javascript"></script>
</body>
</html>
