<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
    <h1>Shopping Cart</h1>

    <table border="1">
        <thead>
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${cart.shoppingCartItems}">
                <tr>
                    <td>${item.shoppable.getInfo()}</td>
                    <td>${item.shoppable.getPrice()}</td>
                    <td>${item.getQuantity()}</td>
                    <td>${item.totalPrice}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/cart/${cart.shoppingCartId}/remove/${item.shoppingItemId}">
                            <button type="submit">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Total: ${total}</h2>

    <!-- Formulaire pour ajouter un article -->
    <form method="post" action="${pageContext.request.contextPath}/cart/${cart.shoppingCartId}/add">
        <label for="shoppableId">Product ID:</label>
        <input type="text" name="shoppable.id" id="shoppableId" required>
        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" id="quantity" required>
        <button type="submit">Add to Cart</button>
    </form>
</body>
</html>