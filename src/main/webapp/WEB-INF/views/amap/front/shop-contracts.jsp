<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Boutique des Contrats</title>
    <link rel="stylesheet"
	href="<c:url value='/resources/css/amap/homePage.css' />">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <style>
        .contract-card {
            background-color: #FFF;
            border: 1px solid #FFA570;
            border-radius: 24px;
            padding: 16px;
            text-align: center;
            transition: transform 0.2s;
        }

        .contract-card:hover {
            transform: scale(1.05);
        }

        .contract-card img {
            max-width: 100%;
            height: auto;
            border-radius: 16px;
        }

        .contract-card .btn {
            background-color: #FFA570;
            color: #FFF;
            border: none;
            margin-top: 12px;
        }

        .contract-card .btn:hover {
            background-color: #FF8A50;
        }
    </style>
</head>
<body>
	<header class="fc-main bg-main">
		<jsp:include page="common/header.jsp" />
	</header>

<div class="container my-5">
    <h1 class="text-center mb-5">Boutique des paniers</h1>
    <div class="row">
        <%-- Itération sur la liste des contrats passée depuis le contrôleur --%>
        <c:forEach var="contract" items="${contracts}">
            <div class="col-md-4 mb-4">
                <div class="contract-card">
                    <c:if test="${not empty contract.imageData}">
											<img
												src="data:${contract.imageType};base64,${contract.imageData}"
												alt="Image du contrat"
												style="width: 50px; height: 50px; border-radius: 8px; object-fit: cover;">
										</c:if>
                    <h3 class="mt-3">${contract.contractName}</h3>
                    <p>${contract.contractType.displayName}</p>
                    <p>Taille du panier : ${contract.contractWeight.displayName}</p>
                    <p>Prix : ${contract.contractPrice} €</p>
                    <button class="btn">Voir les détails</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

	<footer class="container-fluid fc-main bg-main">
		<jsp:include page="common/footer.jsp" />
	</footer>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
