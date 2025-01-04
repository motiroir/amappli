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
<html lang="fr">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liste des Producteurs</title>
	<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/bootstrap/bootstrap-icons.min.css' />" rel="stylesheet">
</head>

<body class="row ${cssStyle} light ${font}-title ${font}-button">
	<header class="fc-main bg-main border-1 border-alt">
	<!-- Inclusion du header -->
			<jsp:include page="../common/headerAdmin.jsp" />
	</header>
	<!-- Inclusion de la sidebar -->
		<jsp:include page="../common/sidebarAdmin.jsp" />

<div id="map" class="p-0"></div>

	<!-- Contenu principal -->
	<div class="content col">
		<div class="container-fluid mt-2">
			<div class="row justify-content-center">
				<div class="col-12">
					<div class="search-bar d-flex align-items-center mb-3">
						<!-- Nombre total de fournisseurs -->
						<div class="me-4 fs-5 fc-main d-none d-md-block">
							<span>${suppliers.size()} éléments</span> <a
								href="<c:url value='/${tenancyAlias}/backoffice/users/generateFakes' />">ajouter 20 users</a>
						</div>
						<!-- Dropdown pour trier -->
						<div class="d-flex align-items-center me-4">
							<label for="sortBy" class="me-2 fw-400 fs-3 text-nowrap fc-main">Trier par</label>
							<select	id="sortBy" class="form-select custom-select">
								<option value="name">Nom du domaine</option>
								<option value="producer">Nom du producteur</option>
							</select>
						</div>
						<!-- Barre de recherche -->
						<div>
							<input type="text" id="searchBar" class="form-control custom-input border-main" placeholder="Rechercher...">
						</div>
						<div class="mx-3 d-none d-md-block">
							<label class="fc-main">Opacité du tableau</label>
    						<input type="range" class="form-range" min="0" max="100" value="100" id="bg-range">
						</div>
					</div>
					<div
						class="table-container d-flex justify-content-between align-items-center my-2">
						<h2 class="fw-bold fc-main my-auto">Liste des fournisseurs</h2>
						<a href="<c:url value='/${tenancyAlias}/backoffice/suppliers/form'/>" class="btn btn-outline-300 rounded-pill fch-main fw-bold border-2">
						<span class="icon">+ </span><span class=" d-none d-md-inline">Ajouter un fournisseur</span>
						</a>
					</div>
					<!-- Mode tableau -->
					<table class="table table-hover table-responsive fc-main" style="--bs-table-bg: color-mix(in srgb, #ffffff, transparent 100%);">
						<thead>
							<tr>
								<th class="bg-700">Domaine</th>
								<th class="bg-700">Nom</th>
								<th class="bg-700">Actions</th>
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
											<a href="<c:url value='/${tenancyAlias}/backoffice/suppliers/details/${supplier.userId}' />" class="btn rounded-circle border-2 border-300 fc-main px-1 py-0 mx-1">
												<i class="bi bi-eye"></i>
											</a>
											<form:form action="delete/${supplier.userId}" class="d-inline" onsubmit="return confirm('Voulez-vous vraiment supprimer l\'adhérent ${supplier.contactInfo.firstName} ${supplier.contactInfo.name} ?');">
												<button type="submit" class="btn rounded-circle border-2 border-300 fc-main px-1 py-0 mx-1">
													<i class="bi bi-trash"></i>
												</button>
											</form:form>
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
	<script>
		var styleMapboxLight = "${mapStyleLight}"
		var styleMapboxDark = "${mapStyleDark}"

		/* 		REMPLACER par les coordinates -> à mettre en place dans la database du tenancy
		const tenancyCity = "${tenancy.getAddress().getCity()}"
		const tenancyPostCode = "${tenancy.getAddress().getPostCode()}" 
		 */
	</script>
	<script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>
	<script src="<c:url value='/resources/js/amap/admin/supplier-list.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/bg-table.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/sidebar.js' />" type="text/javascript"></script>
</body>

</html>