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
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Epicerie</title>
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
				style="max-width: 95%;">
				<!-- Première colonne -->
				<div class="col text-center">
					<div class="d-flex justify-content-center align-items-center">
						<label for="sortByProducts" class="me-2 fw-400 fs-3 text-nowrap fc-main">Trier
							par</label> <select id="sortByProducts"
							class="form-select custom-select border-main">
							<option value="name">Nom</option>
							    <option value="expirationDate" class="d-none d-md-block">DLC</option>
							<option value="creditDesc" class="d-none d-md-block">Prix
								décroissant</option>
							<option value="creditAsc" class="d-none d-md-block">Prix
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
				<div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 g-4 mx-auto" items="${products}"
					style="max-width: 95%;">
					<c:if test="${not empty products}">
						<c:forEach var="product" items="${products}">
							<div class="col" style="width: 30%; max-width: 30%;">
								<div class="card contract-card rounded-4 border-main bg-100">
									<c:if test="${not empty product.imageData}">
										<img class="card-img-top"
											src="data:${product.imageType};base64,${product.imageData}"
											alt="Image du produit">
									</c:if>
									<div class="card-body">
										<h1 class="card-title fw-bold"
											style="text-transform: uppercase;">${product.productName}</h1>
										<p class="card-text text-muted">DLC:
											${product.expirationDate}</p>
										<p class="card-text">${product.productDescription}</p>
										<p class="card-text text-end">
											<b>${product.productPrice}&euro;</b> <br> <em>En
												stock</em>
										</p>
										<a
											href="<c:url value='/amap/${tenancyAlias}/shop/products/${product.id}' />"
											class="btn btn-main rounded-pill bg-main">Voir les
											détails</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${empty products}">
						<div class="col-12">
							<p class="text-center">Aucun produit disponible pour cette
								AMAP.</p>
						</div>
					</c:if>
				</div>
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
	<script src="<c:url value='/resources/js/amap/shop-filter.js' />"
		type="text/javascript"></script>
</body>
</html>
