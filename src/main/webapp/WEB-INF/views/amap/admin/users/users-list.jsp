<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
	String currentMainMenu="users" ; // Détermine la rubrique active
	String currentPage="users" ; // Détermine la sous-rubrique active
	request.setAttribute("currentMainMenu", currentMainMenu);
	request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Liste des Contrats</title>
	<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/bootstrap/bootstrap-icons.css' />" rel="stylesheet">
</head>

<body>
	<!-- Inclusion de la sidebar -->
	<navbar>
		<%@ include file="/WEB-INF/views/amap/common/sidebarAdmin.jsp" %>
	</navbar>

	<!-- Contenu principal -->
	<div class="content" style="margin-left: 240px;">
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-12">
					<div class="search-bar d-flex align-items-center mb-3">
						<!-- Nombre total de contrats -->
						<div class="me-4" style="font-size: 22px; font-weight: 400;">
							<span>${contracts.size()} éléments</span>
						</div>
						<!-- Dropdown pour trier -->
						<div class="d-flex align-items-center me-4">
							<label for="sortBy" class="me-2" style="font-size: 22px; font-weight: 400;">Trié par</label>
							<select id="sortBy" class="form-select custom-select" style="width: auto;">
								<option value="name">Nom du domaine</option>
								<option value="producer">Nom du producteur</option>
								<option value="DateDesc">Date d'inscription (plus récente d'abord)</option>
								<option value="DateAsc">Date d'inscription (plus lointaine d'abord)</option>
							</select>
						</div>
						<!-- Barre de recherche -->
						<div>
							<input type="text" id="searchBar" class="form-control custom-input"
								placeholder="Rechercher..." style="width: 200px;">
						</div>
					</div>
					<div class="table-container d-flex justify-content-between align-items-center">
						<h2 style="font-weight: bold;">Liste des utilisateurs</h2>
						<a href="<c:url value='/amap/contracts/form' />" class="btn-create">
							<span class="icon">+</span>Créer un utilisateur
						</a>
					</div>
					<!-- Mode tableau -->
					<table class="table">
						<thead>
							<tr>
								<th>Domaine</th>
								<th>Nom</th>
								<th>Rôle</th>
								<th>Date d'inscritpion</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="contract" items="${contracts}">
								<tr>
									<td>${contract.contractName}</td>
									<td>${contract.contractType.displayName}</td>
									<td class="d-none d-lg-table-cell">Producteur exemple</td>
									<td>${contract.contractPrice}€</td>
									<td>
										<div class='d-flex justify-content-start align-items-center'>
											<a href="<c:url value='/amap/contracts/detail/${contract.id}' />"
												class="btn-view"> <i class="bi bi-eye"></i>
											</a>
											<form method="POST"
												action="<c:url value='/amap/contracts/delete/${contract.id}' />"
												style="display: inline;">
												<button type="submit" class="btn-delete"
													onclick="return confirm('Voulez-vous vraiment supprimer le contrat ${contract.contractName} ?');">
													<i class="bi bi-trash"></i>
												</button>
											</form>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
	<script>
		document.addEventListener("DOMContentLoaded", () => {
			const searchBar = document.getElementById("searchBar");
			const sortBy = document.getElementById("sortBy");
			const tableBody = document.querySelector("tbody");

			// Fonction pour trier les lignes
			function sortRows(criteria) {
				const rows = Array.from(tableBody.querySelectorAll("tr"));
				rows.sort((a, b) => {
					const valueA = getValue(a, criteria);
					const valueB = getValue(b, criteria);

					if (criteria === "priceAsc") {
						return parseFloat(valueA) - parseFloat(valueB);
					} else if (criteria === "priceDesc") {
						return parseFloat(valueB) - parseFloat(valueA);
					} else {
						return valueA.localeCompare(valueB);
					}
				});

				rows.forEach(row => tableBody.appendChild(row));
			}

			function getValue(row, criteria) {
				if (criteria === "name") return row.cells[1].innerText.trim();
				if (criteria === "producer") return row.cells[3].innerText.trim();
				if (criteria === "priceAsc" || criteria === "priceDesc") return row.cells[4].innerText.trim().replace("€", "");
				return "";
			}

			// Fonction pour filtrer les lignes
			function filterRows(query) {
				const rows = tableBody.querySelectorAll("tr");
				rows.forEach(row => {
					const name = row.cells[1].innerText.toLowerCase();
					const producer = row.cells[3].innerText.toLowerCase();
					if (name.includes(query) || producer.includes(query)) {
						row.style.display = "";
					} else {
						row.style.display = "none";
					}
				});
			}

			// Événements
			sortBy.addEventListener("change", () => {
				sortRows(sortBy.value);
			});

			searchBar.addEventListener("input", () => {
				const query = searchBar.value.toLowerCase();
				filterRows(query);
			});
		});
	</script>
</body>

</html>