<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String currentMainMenu = "shop";
String currentPage = "contracts";
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Détails du contrat</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/common/utils.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/bootstrap/bootstrap-icons.min.css' />"
	rel="stylesheet">
</head>
<style>
.contract-detail-container {
	display: flex;
	gap: 40px; /* Espace entre les colonnes */
	margin-top: 20px;
}

.contract-image {
	flex: 2; /* 20% de la largeur totale */
	text-align: center;
}

.contract-image img {
	width: 100%; /* Image responsive */
	max-height: 300px;
	object-fit: cover;
	border-radius: 10px; /* Coins arrondis */
}

.contract-info {
	flex: 7; /* 70% de la largeur totale */
}

.quantity-selector {
	display: inline-flex;
	align-items: center;
	gap: 10px;
	margin-top: 20px;
}

.quantity-selector input {
	width: 50px;
	text-align: center;
}

.btn-add-to-cart {
	background-color: #FF8C42;
	border: none;
	padding: 10px 20px;
	color: white;
	border-radius: 5px;
	cursor: pointer;
}

.btn-add-to-cart:hover {
	background-color: #FF6A00;
}
</style>
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
				<c:if test="${not empty contract.imageData}">
					<img src="data:${contract.imageType};base64,${contract.imageData}"
						alt="Image du contrat">
				</c:if>
			</div>

			<!-- Colonne de droite : informations -->
			<div class="contract-info">
				<div style="display: flex; align-items: center; gap: 10px;">
					<h2 style="text-transform: uppercase; margin: 0;">${contract.contractName}</h2>
					<p class="text-danger" style="margin: 0;">${contract.deliveryRecurrence.displayName}</p>
				</div>
				<p class="text-muted">${contract.contractWeight.displayName}
					${contract.contractType.displayName}</p>
				<p class="text-warning"></p>
				<p>
					<strong>Producteur :</strong>
					${contract.user.companyDetails.companyName}
					(${contract.user.contactInfo.firstName}
					${contract.user.contactInfo.name})
				</p>
				<p>
					<strong>Composition du panier :<br></strong>
					${contract.contractDescription}
				</p>
				<p>
					<i class="bi bi-bag"></i> commandez aujourd'hui et récupèrez votre
					premier panier le ${formattedNextDeliveryDate}
				</p>
				<p>
					<i class="bi bi-info-circle"></i> L'abonnement au panier se termine
					le ${formattedEndDate}
				</p>
				<h2 class="text-end mt-4">
					<strong>${contract.contractPrice}&euro; / livraison</strong>
				</h2>
			</div>
		</div>

		<!-- Section pour la quantité et le bouton d'ajout au panier -->
		<div class="text-end mt-4">
			<form:form method="post"
				action="${pageContext.request.contextPath}/amap/${tenancyAlias}/cart/${cartId}/add">
				<div class="quantity-selector">
					<label for="quantity">Quantité :</label> <input type="number"
						id="quantity" name="quantity" value="1" min="1">
				</div>
				<input type="hidden" name="shoppableId" value="${contract.id}">
				<input type="hidden" name="shoppableType" value="CONTRACT">
				<button type="submit" class="btn-add-to-cart">Ajouter au
					panier</button>
			</form:form>
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
