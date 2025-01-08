<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String currentMainMenu = "workshops"; // Détermine la rubrique active
String currentPage = "workshops"; // Détermine la sous-rubrique active
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ateliers</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/common/utils.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/amap/shop.css' />"
	rel="stylesheet">
</head>
<body class="row ${cssStyle} light ${font}-title ${font}-button">
	<!-- Header -->
	<header class="fc-main bg-main border-1 border-alt">
		<jsp:include page="common/header-amap.jsp" />
	</header>
	<jsp:include page="../front/common/sidebarUser.jsp" />
	<div id="map" class="p-0"></div>

	<div class="content col fc-main">
		<div class="container-fluid mt-4">
			<div class="search-bar row align-items-center mx-auto"
				items="${contracts}" style="max-width: 95%;">
				<!-- Première colonne -->
				<div class="col text-center">
					<div class="d-flex justify-content-center align-items-center">
						<label for="sortByWorkshops" class="me-2 fw-400 fs-3 text-nowrap fc-main">Trier
							par</label> <select id="sortByWorkshops"
							class="form-select custom-select border-main">
							<option value="name">Nom</option>
							<option value="workshopDateTime" class="d-none d-md-block">Date</option>
							<option value="priceDesc" class="d-none d-md-block">Prix
								décroissant</option>
							<option value="priceAsc" class="d-none d-md-block">Prix
								croissant</option>
						</select>
					</div>
				</div>

				<!-- Deuxième colonne (vide ou avec du contenu si nécessaire) -->
				<div class="col">
					<!-- Vous pouvez ajouter du contenu ici si nécessaire -->
				</div>

				<!-- Troisième colonne -->
				<div class="col text-center">
					<div class="d-flex justify-content-center">
						<input type="text" id="searchBar"
							class="form-control custom-input border-300"
							placeholder="Rechercher...">
					</div>
				</div>
			</div>
			<br>
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 g-4 mx-auto"
					items="${workshops}" style="max-width: 95%;">
					<c:forEach var="workshop" items="${workshops}">
						<div class="col" style="width: 30%; max-width: 30%;">
							<div class="card contract-card rounded-4 border-main bg-100">
								<c:if test="${not empty workshop.imageData}">
									<img class="card-img-top"
										src="data:${workshop.imageType};base64,${workshop.imageData}"
										alt="Image de l'atelier">
								</c:if>
								<div class="card-body">
									<h3 class="card-title fw-bold"
										style="text-transform: uppercase;">${workshop.workshopName}</h3>
									<p class="card-text text-muted">${workshop.workshopDateTime}</p>
									<p class="card-text">${workshop.workshopDescription}</p>
									<p class="card-text text-end">
										<em>${workshop.workshopDuration} minutes</em><br> <b>${workshop.workshopPrice}&euro;/personne</b>
									</p>
									<a
										href="<c:url value='/amap/${tenancyAlias}/shop/workshops/${workshop.id}' />"
										class="btn btn-main rounded-pill bg-main">Voir les détails</a>
								</div>
							</div>
						</div>
					</c:forEach>
					<c:if test="${empty workshops}">
						<div class="col-12">
							<p class="text-center">Aucun atelier disponible pour cette
								AMAP.</p>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>



	<!-- Footer -->
	<footer>
		<jsp:include page="common/footer-amap.jsp" />
	</footer>
	<script>
		var styleMapboxLight = "${mapStyleLight}"
		var styleMapboxDark = "${mapStyleDark}"
		var latitude = "${latitude}"
		var longitude = "${longitude}"
	</script>
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/shop-filter-workshop.js' />"
		type="text/javascript"></script>
</body>
</html>
