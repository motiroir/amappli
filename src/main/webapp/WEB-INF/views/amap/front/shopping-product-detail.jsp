<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String currentMainMenu = "shop";
String currentPage = "products";
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Détails du produit</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/common/utils.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/amap/shopping-detail.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/bootstrap/bootstrap-icons.min.css' />"
	rel="stylesheet">
</head>
<body class="row ${cssStyle} light ${font}-title ${font}-button">
	<!-- Header -->
	<header class="fc-main bg-main border-1 border-alt">
		<jsp:include page="common/header.jsp" />
	</header>
	<jsp:include page="../front/common/sidebarUser.jsp" />
	<div id="map" class="p-0"></div>

	<div class="content col fc-main bg-100 border-main"
		style="margin: 40px;">
		<div class="contract-detail-container">
			<!-- Colonne de gauche : image -->
			<div class="contract-image">
				<c:if test="${not empty product.imageData}">
					<img src="data:${product.imageType};base64,${product.imageData}"
						alt="Image du produit">
				</c:if>
			</div>

			<!-- Colonne de droite : informations -->
			<div class="contract-info">
				<h2 style="text-transform: uppercase; margin: 0;">${product.productName}</h2>
				<p class="text-muted"> Fabriqué le ${formattedFabricationDate}
				<p>
					<strong>Producteur :</strong>
					${product.user.companyDetails.companyName}
					(${product.user.contactInfo.firstName}
					${product.user.contactInfo.name})
				</p>
				<p>
					<strong>Détails du produit :<br></strong>
					${product.productDescription}
				</p>
				<p>
					<i class="bi bi-exclamation-octagon"></i> Le producteur vous informe que le produit ne sera plus consommable après le
					${formattedExpirationDate}
				</p>
				<h2 class="text-end mt-4">
					<strong>${product.productPrice}&euro;</strong>
				</h2>
			</div>
		</div>

		<!-- Section pour la quantité et le bouton d'ajout au panier -->
		<div class="text-end mt-4">
			<form:form method="post"
				action="${pageContext.request.contextPath}/${tenancyAlias}/cart/${cartId}/add">
				<div class="quantity-selector">
					<label for="quantity">Quantité :</label> <input type="number"
						id="quantity" name="quantity" value="1" min="1">
				</div>
				<input type="hidden" name="shoppableId" value="${contract.id}">
				<input type="hidden" name="shoppableType" value="PRODUCT">
				<button type="submit" class="btn-add-to-cart">Ajouter au
					panier</button>
			</form:form>
		<a
			href="${pageContext.request.contextPath}/${tenancyAlias}/cart/${cartId}"
			class="btn-view-cart">Voir le panier</a>
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
