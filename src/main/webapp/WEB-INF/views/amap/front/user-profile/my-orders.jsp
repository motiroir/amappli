<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mes commandes</title>

<link href="https://api.mapbox.com/mapbox-gl-js/v2.15.0/mapbox-gl.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value='/resources/bootstrap/bootstrap.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/amap/user-profile/my-orders.css' />">
</head>


<body class="${cssStyle} light ${font}-title ${font}-button">

	<!-- the bootstrap classes on this div are used to have the footer correctly positioned at the bottom when the page is not full -->
	<div class="d-flex flex-column min-vh-100">

		<header class="fc-main bg-main">
			<jsp:include page="../common/header-amap.jsp" />
		</header>
		<div class="fc-main content flex-grow-1">
			<div id="map"></div>
			<div class="main-div">

				<table class="table">
					<thead>
						<tr>
							<th>Numéro de commande</th>
							<th>Date</th>
							<th>Montant</th>
							<th>Paiement</th>
							<th>Status</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orders}">
							<tr>
								<td>${order.orderId}</td>
								<td>${order.orderDate}</td>
								<td>${order.totalAmount}€</td>
								<td><c:if test="${order.orderPaid}">
										Paiement en ligne
									</c:if> <c:if test="${!order.orderPaid}">
										Paiement sur place
									</c:if></td>
								<td>${order.orderStatus}</td>
								<td><a class="btn btn-100">Détails </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
		<footer class="fc-main bg-main">
			<jsp:include page="../common/footer-amap.jsp" />
		</footer>
	</div>

	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>

	<script>
		var styleMapboxLight = "${mapStyleLight}"
		var styleMapboxDark = "${mapStyleDark}"

		/* 		REMPLACER par les coordinates -> à mettre en place dans la database du tenancy
		 const tenancyCity = "${tenancy.getAddress().getCity()}"
		 const tenancyPostCode = "${tenancy.getAddress().getPostCode()}" 
		 */
	</script>

	<%-- 	<script src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>  --%>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"></script>
</body>
</html>