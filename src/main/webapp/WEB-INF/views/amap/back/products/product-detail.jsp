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
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Détails
								du produit</h2>
								<a href="/Amappli/amap/products/edit/${product.id}" class="btn btn-primary">Modifier le produit</a>
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
										<label class="form-label">Description</label>
										<textarea class="form-control" rows="3" readonly>${product.productDescription}</textarea>
									</div>
									<div class="mb-3">
										<label class="form-label">Prix</label> <input type="text"
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
								</div>

								<!-- Troisième colonne -->
								<div class="col-md-4">
									<div class="mb-3 text-center">
										<c:if test="${not empty product.imageData}">
											<img
												src="data:${product.imageType};base64,${product.imageData}"
												alt="Image du produit"
												style="max-width: 100%; border-radius: 8px; object-fit: cover;">
										</c:if>
									</div>
									<div class="mb-3">
										<label class="form-label">Date de création le
											${product.dateCreation}</label>
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
</body>
</html>
