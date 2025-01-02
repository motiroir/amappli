<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Détails du Contrat</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/resources/css/amap/homePage.css' />">
<style>
.contract-details-container {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
}

.contract-image {
	flex: 1;
	max-width: 50%;
}

.contract-image img {
	width: 100%;
	height: auto;
	border-radius: 16px;
}

.contract-info {
	flex: 1;
	max-width: 50%;
}

.contract-info h2 {
	font-size: 28px;
	margin-bottom: 10px;
}

.contract-info p {
	margin-bottom: 10px;
}

.quantity-selector {
	display: flex;
	align-items: center;
	gap: 10px;
}

.quantity-selector input {
	width: 50px;
	text-align: center;
}

.btn-add-to-cart {
	background-color: #FFA570;
	color: white;
	border: none;
	padding: 10px 20px;
	border-radius: 8px;
}

.btn-add-to-cart:hover {
	background-color: #FF8A50;
}
</style>
</head>
<body>
	<!-- Inclure le header -->
	<header>
		<jsp:include page="common/header.jsp" />
	</header>

	<div class="container-fluid mt-4">
		<div class="row">
			<!-- Sidebar -->
			<div class="col-12 col-md-3">
				<div class="sidebar">
					<div class="section-title">Paniers</div>
					<ul class="list-unstyled">
						<li><a href="#"
							class="${currentPage == 'all' ? 'active' : ''}">Tous les
								paniers <span class="badge bg-secondary">${counts.all}</span>
						</a></li>
						<li><a href="#"
							class="${currentPage == 'vegetables' ? 'active' : ''}">Paniers
								légumes <span class="badge bg-secondary">${counts.vegetables}</span>
						</a></li>
						<li><a href="#"
							class="${currentPage == 'fruits' ? 'active' : ''}">Paniers
								fruits <span class="badge bg-secondary">${counts.fruits}</span>
						</a></li>
						<li><a href="#"
							class="${currentPage == 'mixed' ? 'active' : ''}">Paniers
								mixtes <span class="badge bg-secondary">${counts.mixed}</span>
						</a></li>
					</ul>
					<div class="section-title mt-4">Epicerie</div>
					<ul class="list-unstyled">
						<li><a href="#">Produits</a></li>
					</ul>
					<div class="section-title mt-4">Ateliers</div>
					<ul class="list-unstyled">
						<li><a href="#">Workshops</a></li>
					</ul>
				</div>
			</div>
			<div class="container mt-5">
				<div class="contract-details-container">
					<!-- Image du contrat -->
					<div class="contract-image">
						<c:if test="${not empty contract.imageData}">
							<img
								src="data:${contract.imageType};base64,${contract.imageData}"
								alt="${contract.contractName}" />
						</c:if>
					</div>

					<!-- Informations du contrat -->
					<div class="contract-info">
						<h2>${contract.contractName}</h2>
						<p>
							<strong>Ferme :</strong> ${contract.tenancy.tenancyName}
						</p>
						<p>
							<strong>Type :</strong> ${contract.contractType.displayName}
						</p>

						<p>
							<strong>Producteur :</strong>
							${contract.user.companyDetails.companyName}
						</p>
						<p>
							<strong>Prix :</strong> ${contract.contractPrice}€
						</p>
						<p>
							<strong>Description :</strong> ${contract.contractDescription}
						</p>
						<p>
							<strong>Jour de livraison au point de collecte :</strong>
							${contract.deliveryDay.displayName}
						</p>
						<p>
							<strong>Fréquence de livraison : </strong>
							${contract.deliveryRecurrence.displayName}
						</p>
						<p>
							<strong>Date de fin de l'abonnement : </strong>
							${contract.endDate}
						</p>
						<p>
							<strong>Date de première livraison :</strong> ${nextDeliveryDate}
						</p>
						<c:if test="${not empty address}">
							<p>
								<strong>Adresse de récupération de la commande :</strong><br>
								${address.line1} ${address.line2}, ${address.city}
								(${address.postCode})
							</p>
						</c:if>
						<c:if test="${empty address}">
							<p>
								<strong>Adresse de récupération de la commande :</strong><br>
								Adresse indisponible.
							</p>
						</c:if>



						<div class="quantity-selector">
							<label for="quantity">Quantité :</label> <input type="number"
								id="quantity" name="quantity" value="1" min="1" />
						</div>

						<button class="btn-add-to-cart">Ajouter au panier</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<jsp:include page="common/footer.jsp" />
	</footer>
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
</body>
</html>
