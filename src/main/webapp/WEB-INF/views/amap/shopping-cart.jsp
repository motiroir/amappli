<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Votre panier</title>

<link href="https://api.mapbox.com/mapbox-gl-js/v2.15.0/mapbox-gl.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value='/resources/bootstrap/bootstrap.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/resources/css/amap/shopping-cart.css' />">
</head>


<body class="${cssStyle} light">

	<header class="fc-main bg-main">
		<jsp:include page="common/header-amap.jsp" />
	</header>
	<div id="map"></div>
	<h1>Shopping Cart</h1>

	<table border="1">
		<thead>
			<tr>
				<th>Product Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Total</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${cart.shoppingCartItems}">
				<tr>
					<td>${item.shoppable.getInfo()}</td>
					<td>${item.shoppable.getPrice()}</td>

					<td><form:form method="post"
							action="${pageContext.request.contextPath}/${tenancyId}/cart/${cart.shoppingCartId}/updateQuantity/${item.shoppingItemId}">
							<button type="submit" name="action" value="decrease">-</button>
							<span>${item.getQuantity()}</span>
							<button type="submit" name="action" value="increase">+</button>
						</form:form></td>

					<td>${item.totalPrice}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h2>Total: ${total}</h2>

	<!-- Formulaire de test pour ajouter un article -> A SUPPRIMER -->
	<form:form method="post"
		action="${pageContext.request.contextPath}/${tenancyId}/cart/${cart.shoppingCartId}/add">
		<label for="shoppableId">Product ID:</label>
		<input type="text" name="shoppableId" id="shoppableId" required>
		<input type="hidden" name="shoppableType" value="ProductMock">
		<label for="quantity">Quantity:</label>
		<input type="number" name="quantity" id="quantity" required>
		<button type="submit">Add to Cart</button>
	</form:form>

	<footer class="container-fluid fc-main bg-main">
		<jsp:include page="common/footer-amap.jsp" />
	</footer>

	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>

	<script>
		const styleMapboxLight = "${mapStyleLight}"
		const styleMapboxDark = "${mapStyleDark}"
			
/* 		REMPLACER par les coordinates -> à mettre en place dans la database du tenancy
 		const tenancyCity = "${tenancy.getAddress().getCity()}"
		const tenancyPostCode = "${tenancy.getAddress().getPostCode()}" 
			*/
	</script>

	<script src="<c:url value='/resources/js/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/mapbox/map.js' />"></script>
	<script	src="<c:url value='/resources/js/theme-swap-for-amaps.js' />"></script>
</body>
</html>