<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ajouter un Contrat</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<style>
body {
	background-color: #f9f9f9;
	font-family: Arial, sans-serif;
}

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
</style>
</head>
<body>
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
									<label for="contractName" class="form-label">Nom du
										contrat</label> <input type="text" class="form-control"
										id="contractName" name="contractName"
										placeholder="Nom du contrat">
								</div>
								<div class="mb-3">
									<label for="contractType" class="form-label">Types de
										produit</label> <select class="form-select form-control"
										id="contractType" name="contractType">
										<option selected disabled>Choisir...</option>
										<option value="FRUITS_CONTRACT">Panier de fruits</option>
										<option value="VEGETABLES_CONTRACT">Panier de légumes</option>
										<option value="MIX_CONTRACT">Panier mixte</option>
									</select>
								</div>
								<div class="mb-3">
									<label for="producer" class="form-label">Producteur</label> <select
										class="form-select form-control" id="producer" name="producer">
										<option selected disabled>Choisir un producteur...</option>
									</select>
								</div>
								<div class="mb-3">
									<label for="startDate" class="form-label">Date de début</label>
									<input type="date" class="form-control" id="startDate"
										name="startDate" min="${currentDate}">
								</div>
								<div class="mb-3">
									<label for="endDate" class="form-label">Date de fin</label> <input
										type="date" class="form-control" id="endDate" name="endDate">
								</div>
							</div>

							<!-- Deuxième colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label for="contractDescription" class="form-label">Description
										des produits</label>
									<textarea class="form-control" id="contractDescription"
										name="contractDescription" rows="3"
										placeholder="Description des produits"></textarea>
								</div>
								<div class="mb-3">
									<label for="contractWeight" class="form-label">Taille</label> <select
										class="form-select form-control" id="contractWeight"
										name="contractWeight">
										<option selected disabled>Choisir...</option>
										<option value="SMALL">Petit</option>
										<option value="AVERAGE">Moyen</option>
										<option value="BIG">Grand</option>
									</select>
								</div>
								<div class="mb-3">
									<label for="contractPrice" class="form-label">Prix</label>
									<div class="input-group">
										<input type="number" class="form-control" id="contractPrice"
											name="contractPrice" step="0.01" placeholder="Prix">
										<span class="input-group-text">€</span>
									</div>
								</div>
								<div class="mb-3">
									<label for="quantity" class="form-label">Quantité</label> <input
										type="number" class="form-control" id="quantity"
										name="quantity" placeholder="Quantité">
								</div>
							</div>

							<!-- Troisième colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label for="imageUrl" class="form-label">Image du
										produit</label>
									<div class="d-flex align-items-center">
										<input type="text" class="form-control me-2" id="imageUrl"
											name="imageUrl" placeholder="URL de l'image">
										<button type="button" class="btn btn-secondary">Ajouter</button>
									</div>
								</div>
								<div class="mb-3 text-center">
									<img src="https://via.placeholder.com/150"
										alt="Aperçu du produit" class="image-preview">
								</div>
								<div class="text-end">
									<button type="submit" class="btn btn-custom btn-lg"
										style="width: 210px; height: 60px;">Ajouter</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='/resources/js/contract-form.js' />"></script>
</body>
</html>


<%-- <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<!-- <html> -->
<!-- <head> -->
<!-- <title>Ajouter un Contrat</title> -->
<!-- </head> -->
<!-- <body> -->
<!-- 	<h2>Ajouter un Contrat</h2> -->
<%-- 	<form:form method="POST" action="/Amappli/amap/contracts/add" --%>
<%-- 		modelAttribute="contract"> --%>
<%--         Nom du contrat : <form:input path="contractName" /> --%>
<!-- 		<br> -->
<!--         Type de Contrat : -->
<%--         <form:select path="contractType"> --%>
<%-- 			<c:forEach var="type" items="${contractTypes}"> --%>
<%-- 				<form:option value="${type}" label="${type.displayName}" /> --%>
<%-- 			</c:forEach> --%>
<%-- 		</form:select> --%>
<!-- 		<br> -->
<%--         Description : <form:textarea path="contractDescription" /> --%>
<!-- 		<br> -->
<!--         Poids : -->
<%--         <form:select path="contractWeight"> --%>
<%-- 			<c:forEach var="weight" items="${contractWeights}"> --%>
<%-- 				<form:option value="${weight}" label="${weight.displayName}" /> --%>
<%-- 			</c:forEach> --%>
<%-- 		</form:select> --%>
<!-- 		<br> -->
<%--         Prix : <form:input path="contractPrice" type="number" --%>
<%-- 			step="0.01" /> --%>
<!-- 		<br> -->
<!--         Date de début :  -->
<%--         <form:input path="startDate" type="date" id="startDate" --%>
<%-- 			min="${currentDate}" /> --%>
<!-- 		<br> -->
<!--         Date de fin :  -->
<%--         <form:input path="endDate" type="date" id="endDate" /> --%>
<!-- 		<br> -->
<%--         Image URL : <form:input path="imageUrl" /> --%>
<!-- 		<br> -->
<!-- 		<input type="submit" value="Ajouter Contrat" /> -->
<!-- 		<a href="/Amappli/amap/contracts/list">Voir la liste des contrats</a> -->
<%-- 	</form:form> --%>
<%-- 	<script src="<c:url value='/resources/js/contract-form.js' />"></script> --%>
<!-- </body> -->
<!-- </html> -->
