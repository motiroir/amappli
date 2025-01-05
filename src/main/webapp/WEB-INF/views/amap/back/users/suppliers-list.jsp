<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%
String currentMainMenu = "users"; // Détermine la rubrique active
String currentPage = "suppliers"; // Détermine la sous-rubrique active
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liste des Producteurs</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/bootstrap/bootstrap-icons.css' />"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
</head>

<body class="row ${cssStyle} light ${font}-title ${font}-button">
	<header class="fc-main bg-main">
	<!-- Inclusion du header -->
			<jsp:include page="../common/headerAdmin.jsp" />
	</header>
	<!-- Inclusion de la sidebar -->
		<jsp:include page="../common/sidebarAdmin.jsp" />

<div id="map"></div>

	<!-- Contenu principal -->
	<div class="content col">
		<div class="container-fluid mt-2">
			<div class="row justify-content-center">
				<div class="col-12">
					<div class="search-bar d-flex align-items-center mb-3">
						<!-- Nombre total de fournisseurs -->
						<div class="me-4" style="font-size: 22px; font-weight: 400;">
							<span>${suppliers.size()} éléments</span> <a
								href="<c:url value='/${tenancyAlias}/backoffice/users/generateFakes' />">ajouter 20 users</a>
						</div>
						<!-- Dropdown pour trier -->
						<div class="d-flex align-items-center me-4">
							<label for="sortBy" class="me-2"
								style="font-size: 22px; font-weight: 400;">Trié par</label> <select
								id="sortBy" class="form-select custom-select"
								style="width: auto;">
								<option value="name">Nom du domaine</option>
								<option value="producer">Nom du producteur</option>
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
						<h2 style="font-weight: bold;">Liste des fournisseurs</h2>
						<a href="<c:url value='/${tenancyAlias}/backoffice/suppliers/form'/>"
							class="btn-create"> <span class="icon">+ </span>Ajouter un
							fournisseur
						</a>
					</div>
					<!-- Mode tableau -->
					<table class="table">
						<thead>
							<tr>
								<th>Domaine</th>
								<th>Nom</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="supplier" items="${suppliers}">
								<tr>
									<td>${supplier.companyDetails.companyName}</td>
									<td>${supplier.contactInfo.firstName}
										${supplier.contactInfo.name}</td>
									<td>
										<div class='d-flex justify-content-start align-items-center'>
											<a
												href="<c:url value='/${tenancyAlias}/backoffice/suppliers/details/${supplier.userId}' />"
												class="btn-view"> <i class="bi bi-eye"></i>
											</a>
											<a href="<c:url value='/${tenancyAlias}/backoffice/suppliers/delete/${supplier.userId}' />"
												class="btn btn-delete"	onclick="return confirm('Voulez-vous vraiment supprimer le fournisseur ${supplier.contactInfo.firstName} ${supplier.contactInfo.name} ?');">
												<i class="bi bi-trash"></i>
											</a>
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
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />" type="text/javascript"></script>
			<script>
		var styleMapboxLight = "${mapStyleLight}"
		var styleMapboxDark = "${mapStyleDark}"

		/* 		REMPLACER par les coordinates -> à mettre en place dans la database du tenancy
		 const tenancyCity = "${tenancy.getAddress().getCity()}"
		 const tenancyPostCode = "${tenancy.getAddress().getPostCode()}" 
		 */
	</script>
	<script src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />" type="text/javascript"></script>
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