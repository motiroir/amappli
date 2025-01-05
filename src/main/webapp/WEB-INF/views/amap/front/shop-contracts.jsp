<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String currentMainMenu = "products"; // Détermine la rubrique active
String currentPage = "contracts"; // Détermine la sous-rubrique active
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
<link rel="stylesheet"
	href="<c:url value='/resources/css/amap/homePage.css' />">
<style>
body {
	background-color: #FAF8F6;

.contract-card {
	background-color: #FFF;
	border: 1px solid #FFA570;
	border-radius: 24px;
	padding: 16px;
	text-align: center;
	transition: transform 0.2s;
	height: 100%;
	/* S'assure que toutes les cartes aient la même hauteur */
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.contract-card:hover {
	transform: scale(1.05);
}

.contract-card img {
	width: 100%;
	height: 200px; /* Uniformise la hauteur des images */
	object-fit: cover;
	/* Conserve les proportions tout en remplissant l'espace */
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
<body class="row theme-1 light">
<header class="fc-main bg-main">
		<!-- Inclusion du header -->
<jsp:include page="common/header.jsp" />
</header>
<jsp:include page="../front/common/sidebarUser.jsp"/>
<div class="container-fluid mt-5">
			<div class="row justify-content-center">
				<div class="col-lg-10">
										<div class="header-container">
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Paniers</h2>
						</div>
    <div class="row">

        <!-- Main Content -->
        <div class="col-12 col-md-9">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <span>${contracts.size()} Items</span>
                <div class="d-flex align-items-center">
                    <label for="sortBy" class="me-2">Trier par</label>
                    <select id="sortBy" class="form-select me-3" style="width: auto;">
                        <option value="name">Nom</option>
                        <option value="priceAsc">Prix croissant</option>
                        <option value="priceDesc">Prix décroissant</option>
                    </select>
                    <input type="text" id="searchBar" class="form-control" placeholder="Rechercher..."
                        style="width: 200px;">
                </div>
            </div>

            <div class="row">
                <c:if test="${not empty contracts}">
                    <c:forEach var="contract" items="${contracts}">
                        <div class="col-12 col-sm-6 col-lg-4 mb-4">
                            <div class="contract-card" data-contract-type="${contract.contractType.name()}">
                                <c:if test="${not empty contract.imageData}">
                                    <img src="data:${contract.imageType};base64,${contract.imageData}"
                                        alt="Image du contrat">
                                </c:if>
                                <h3 class="mt-3">${contract.contractName}</h3>
                                <p>${contract.contractType.displayName}</p>
                                <p>${contract.contractWeight.displayName}</p>
                                <p>${contract.contractPrice}&euro;</p>
                                <a href="<c:url value='/${tenancyAlias}/shop/contracts/${contract.id}' />"
                                    class="btn">Voir les détails</a>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty contracts}">
                    <div class="col-12">
                        <p class="text-center">Aucun contrat disponible pour cette AMAP.</p>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</div>
</div>



	<!-- Inclure le footer -->
	<footer>
		<jsp:include page="common/footer.jsp" />
	</footer>
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
</body>
</html>
