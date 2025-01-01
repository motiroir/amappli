<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>Liste des Contrats</title>
<link href="<d:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link
	href="<d:url value='/resources/css/amap/common/sidebarAdmin.css' />"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
.table td, .table th {
	vertical-align: middle; /* Centre verticalement le contenu */
}
</style>
</head>

<body class="row theme-1 light">

	<header class="fc-main bg-main">
		<!-- Inclusion du header -->
		<jsp:include page="../common/headerAdmin.jsp" />
	</header>
	<!-- Inclusion de la sidebar -->
	<jsp:include page="../common/sidebarAdmin.jsp" />

	<!-- Contenu principal -->
	<div class="content col">
		<div class="container-fluid mt-2">
			<div class="row justify-content-center">
				<div class="col-12">
					<div class="search-bar d-flex align-items-center mb-3">
						<!-- Nombre total de contrats -->
						<div class="me-4" style="font-size: 22px; font-weight: 400;">
							<span>${contracts.size()} éléments</span>
						</div>

						<!-- Dropdown pour trier -->
						<div class="d-flex align-items-center me-4">
							<label for="sortBy" class="me-2"
								style="font-size: 22px; font-weight: 400;">Trié par</label> <select
								id="sortBy" class="form-select custom-select"
								style="width: auto;">
								<option value="name">Nom</option>
								<option value="producer">Producteur</option>
								<option value="priceAsc">Prix croissant</option>
								<option value="priceDesc">Prix décroissant</option>
							</select>
						</div>

						<!-- Barre de recherche -->
						<div>
							<input type="text" id="searchBar"
								class="form-control custom-input" placeholder="Rechercher..."
								style="width: 200px;">
						</div>
					</div>
					<div
						class="table-container d-flex justify-content-between align-items-center">
						<h2 style="font-weight: bold;">Liste des contrats</h2>
						<a
							href="<d:url value='/${tenancyAlias}/backoffice/contracts/form' />"
							class="btn-create"> <span class="icon">+</span>Créer un
							contrat
						</a>
					</div>
					<!-- Mode tableau -->
					<table class="table">
						<thead>
							<tr>
								<th>Image</th>
								<th>Nom</th>
								<th>Type</th>
								<th>Producteur</th>
								<th>Prix</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<d:forEach var="contract" items="${contracts}">
								<tr>
									<td><d:if test="${not empty contract.imageData}">
											<img
												src="data:${contract.imageType};base64,${contract.imageData}"
												alt="Image du contrat"
												style="width: 50px; height: 50px; border-radius: 8px; object-fit: cover;">
										</d:if></td>
									<td>${contract.contractName}</td>
									<td>${contract.contractType.displayName}</td>
									<td class="d-none d-lg-table-cell">${contract.user.email}</td>
									<td>${contract.contractPrice}€</td>
									<td>
										<div class='d-flex justify-content-start align-items-center'>
											<a
												href="<d:url value='/${tenancyAlias}/backoffice/contracts/detail/${contract.id}' />"
												class="btn-view"> <i class="bi bi-eye"></i>
											</a>

											<form:form method="POST"
												action="${pageContext.request.contextPath}/${tenancyAlias}/backoffice/contracts/delete/${contract.id}"
												style="display: inline;">
												<button type="submit" class="btn-delete"
													onclick="return confirm('Voulez-vous vraiment supprimer le contrat ${contract.contractName} ?');">
													<i class="bi bi-trash"></i>
												</button>
											</form:form>
										</div>
									</td>
								</tr>
							</d:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<d:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"
		type="text/javascript"></script>
	<script src="<d:url value='/resources/js/amap/admin/user-list.js' />"
		type="text/javascript"></script>
	<script src="<d:url value='/resources/js/common/theme-swap.js' />"
		type="text/javascript"></script>
	<script src="<d:url value='/resources/js/common/palette-swap.js' />"
		type="text/javascript"></script>
</body>
</html>
