<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<title>Modifier un Produit</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
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
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-lg-10">
				<div class="form-container">
					<h2 class="mb-4" style="font-weight: bold; text-align: left;">Modifier
						un produit</h2>
					<form:form name="productForm" method="POST"
						action="/Amappli/amap/products/update" modelAttribute="product"
						enctype="multipart/form-data">
						<form:hidden path="id" />
						<div class="row">
							<!-- Première colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label for="productName" class="form-label">Nom du produit</label>
									<form:input path="productName" class="form-control" placeholder="Nom du produit" />
								</div>
								<div class="mb-3">
									<label for="productDescription" class="form-label">Description</label>
									<form:textarea path="productDescription" rows="3" class="form-control" placeholder="Description du produit"></form:textarea>
								</div>
								<div class="mb-3">
									<label for="productPrice" class="form-label">Prix</label>
									<div class="input-group">
										<form:input path="productPrice" type="number" step="0.01" class="form-control" placeholder="Prix" />
										<span class="input-group-text">€</span>
									</div>
								</div>
							</div>

							<!-- Deuxième colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label for="productStock" class="form-label">Quantité en stock</label>
									<form:input path="productStock" type="number" class="form-control" placeholder="Stock disponible" />
								</div>
								<div class="mb-3">
									<label for="fabricationDate" class="form-label">Date de fabrication</label>
									<form:input path="fabricationDate" type="date" class="form-control" />
								</div>
								<div class="mb-3">
									<label for="expirationDate" class="form-label">Date d'expiration</label>
									<form:input path="expirationDate" type="date" class="form-control" />
								</div>
							</div>

							<!-- Troisième colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label for="deliveryDay" class="form-label">Jour de livraison</label>
									<form:select path="deliveryDay" class="form-select form-control">
										<c:forEach var="day" items="${deliveryDay}">
											<form:option value="${day}" label="${day.displayName}" />
										</c:forEach>
									</form:select>
								</div>
								<div class="mb-3">
									<label for="deliveryRecurrence" class="form-label">Fréquence de livraison</label>
									<form:select path="deliveryRecurrence" class="form-select form-control">
										<c:forEach var="recurrence" items="${deliveryRecurrence}">
											<form:option value="${recurrence}" label="${recurrence.displayName}" />
										</c:forEach>
									</form:select>
								</div>
								<div class="mb-3">
									<label for="image" class="form-label">Photo du produit</label>
									<input type="file" class="form-control" id="image" name="image" accept="image/png,image/jpeg,image/svg">
								</div>
								<div class="mb-3 text-center">
									<c:if test="${not empty product.imageData}">
										<img src="data:${product.imageType};base64,${product.imageData}" alt="Aperçu du produit" class="image-preview" style="max-width: 100%; border-radius: 8px;">
									</c:if>
								</div>
								<div class="text-end">
									<button type="submit" class="btn btn-custom btn-lg" style="width: 250px; height: 60px; margin-bottom: 15px;">Valider les modifications</button>
									<a href="/Amappli/amap/products/list" style="width: 150px; height: 40px; margin-left: 10px;">Annuler</a>
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