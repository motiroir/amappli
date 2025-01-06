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
<title>Boutique des Paniers</title>
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
		<jsp:include page="common/header.jsp" />
	</header>
	<jsp:include page="../front/common/sidebarUser.jsp" />
	<div id="map" class="p-0"></div>

	<div class="content col fc-main">
		<div class="container-fluid mt-4">
			<div class="row justify-content-center">
				<div class="header-container mb-4">
					<h2 class="fw-bold">Epicerie</h2>
				</div>
				<div class="container">
					<div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 g-4 mx-auto"
						style="max-width: 95%;">
						<c:if test="${not empty products}">
							<c:forEach var="product" items="${products}">
								<div class="col" style="width:30%; max-width: 30%;">
									<div class="card contract-card rounded-4 border-main">
										<c:if test="${not empty product.imageData}">
											<img class="card-img-top"
												src="data:${product.imageType};base64,${product.imageData}"
												alt="Image du produit">
										</c:if>
										<div class="card-body">
											<h3 class="card-title fw-bold">${product.productName}</h3>
											<p class="card-text">
												<em>D L C : ${product.expirationDate}</em>
											</p>
											<p class="card-text">${product.productDescription}</p>
											<p class="card-text text-end"><b>${product.productPrice}&euro;</b>
												<br>
												<em>En stock</em>
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
		<jsp:include page="common/footer.jsp" />
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
</body>
</html>
