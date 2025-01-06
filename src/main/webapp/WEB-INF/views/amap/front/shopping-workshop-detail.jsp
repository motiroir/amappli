<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String currentMainMenu = "shop";
String currentPage = "workshops";
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Détails de l'atelier</title>
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
				<c:if test="${not empty workshop.imageData}">
					<img src="data:${workshop.imageType};base64,${workshop.imageData}"
						alt="Image de l'atelier">
				</c:if>
			</div>
			<!-- Colonne de droite : informations -->
			<div class="contract-info">
				<h2 style="text-transform: uppercase; margin: 0;">${workshop.workshopName}</h2>
				<p class="text-muted">Durée de l'atelier :
					${workshop.workshopDuration} minutes
				<p>
					<strong>Intervenant :</strong>
					${workshop.user.contactInfo.firstName}
					${workshop.user.contactInfo.name}
					(${workshop.user.companyDetails.companyName})
				</p>
				<p>
					<strong>Détails de l'atelier :<br></strong>
					${workshop.workshopDescription}
				</p>
				<p>
					<i class="bi bi-geo-alt"></i>
					<c:if test="${not empty workshop.address}">
            ${workshop.user.address.line1}, ${workshop.user.address.line2}, ${workshop.user.address.city} (${workshop.user.address.postCode})
        </c:if>
					<c:if test="${empty workshop.address}">
            Aucune adresse associée.
        </c:if>
				</p>
				<p>
					<i class="bi bi-clock"></i> ${formattedWorkshopDateTime}

				</p>
				<h2 class="text-end mt-4">
					<strong>${workshop.workshopPrice}&euro;</strong>
				</h2>
			</div>
		</div>
		<div class="text-end mt-4">
			<form:form method="post"
				action="${pageContext.request.contextPath}/${tenancyAlias}/cart/${cartId}/add">
				<input type="hidden" name="shoppableId" value="${workshop.id}" />
				<input type="hidden" name="shoppableType" value="WORKSHOP" />
				<input type="hidden" name="quantity" value="1" />
				<button type="submit" class="btn-add-to-cart">Je m'inscris
					à cet atelier</button>
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
