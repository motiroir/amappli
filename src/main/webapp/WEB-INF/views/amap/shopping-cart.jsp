<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Shopping Cart</title>

<link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css'/>" />
<link href="https://api.mapbox.com/mapbox-gl-js/v2.15.0/mapbox-gl.css" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value='/resources/css/amap/common/header.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/amap/common/footer.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/amap/homePage.css' />">
</head>
<body>

 <jsp:include page="common/header.jsp" />
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
					
					<td>
						<form method="post"
							action="${pageContext.request.contextPath}/cart/${cart.shoppingCartId}/updateQuantity/${item.shoppingItemId}">
							<button type="submit" name="action" value="decrease">-</button>
							<span>${item.getQuantity()}</span>
							<button type="submit" name="action" value="increase">+</button>
						</form>
					</td>
					
					<td>${item.totalPrice}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h2>Total: ${total}</h2>

	<!-- Formulaire de test pour ajouter un article -> A SUPPRIMER -->
<form method="post" action="${pageContext.request.contextPath}/cart/${cart.shoppingCartId}/add">
    <label for="shoppableId">Product ID:</label>
    <input type="text" name="shoppableId" id="shoppableId" required>
    <input type="hidden" name="shoppableType" value="ProductMock">
    <label for="quantity">Quantity:</label>
    <input type="number" name="quantity" id="quantity" required>
    <button type="submit">Add to Cart</button>
</form>


 <jsp:include page="common/footer.jsp" />
</body>
</html>