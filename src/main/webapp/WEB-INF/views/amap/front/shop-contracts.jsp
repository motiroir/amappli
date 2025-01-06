<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Boutique des Paniers</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/common/utils.css' />"
	rel="stylesheet">
</head>
<style>
.contract-card .card-img-top {
	width: 100%; /* Largeur de l'image */
	height: 150px; /* Hauteur de l'image */
	padding: 5px; /* Espace autour de l'image */
	object-fit: cover; /* Garde le ratio tout en remplissant la zone */
	margin: 0 auto; /* Centrer l'image horizontalement */
}
.card-title {
    width: 100%; /* Prend 80% de la largeur de la carte */
    font-size: clamp(1rem, 2vw, 1.5rem); /* Ajuste la taille du texte dynamiquement */
    white-space: nowrap; /* Empêche le texte de passer à la ligne */
    overflow: hidden; /* Masque le texte qui dépasse */
    text-overflow: ellipsis; /* Ajoute des points de suspension si le texte est trop long */
}


.contract-card {
	position: relative;
	height: 100%; /* Prend toute la hauteur disponible de la colonne */
	display: flex;
	flex-direction: column; /* Aligne le contenu verticalement */
	justify-content: space-between;
	/* Équilibre l'espace entre les sections */
	/* Permet de positionner des éléments enfants relativement à la carte */
}

.contract-card .btn {
	position: absolute; /* Positionnement absolu du bouton */
	bottom: -15px; /* Décale le bouton de 15px en dehors de la carte */
	left: 50%; /* Centre horizontalement le bouton */
	transform: translateX(-50%); /* Ajuste le centrage parfait */
	z-index: 1;
	/* Fait en sorte que le bouton soit au-dessus de la bordure */
}

.row {
	gap: 20px; /* Espace entre les cartes (horizontale et verticale) */
}

.contract-card {
	width: 100%; /* Prend toute la largeur de la colonne (28%) */
	margin: auto; /* Centrage */
}

.card-text {
	display: -webkit-box;
	-webkit-line-clamp: 2; /* Limite à 2 lignes */
	-webkit-box-orient: vertical;
	overflow: hidden; /* Masque le texte qui dépasse */
	text-overflow: ellipsis; /* Ajoute des points de suspension */
	min-height: 2.5em;
	/* Hauteur minimum pour 2 lignes de texte (1.25em par ligne en général) */
	line-height: 1.25em; /* Hauteur de ligne */
}

.row .col {
	display: flex;
	flex-grow: 1; /* Permet aux colonnes de s'adapter uniformément */
}

.card {
	height: 350px; /* Fixe une hauteur uniforme pour toutes les cartes */
	max-width: 100%; /* Largeur automatique */
	padding: 15px;
}

.card-body {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	height: 100%; /* Remplit l'espace restant */
}
</style>
<body class="row ${cssStyle} light ${font}-title ${font}-button">
	<!-- Header -->
	<header class="fc-main bg-main border-1 border-alt">
		<jsp:include page="common/header.jsp" />
	</header>
	<jsp:include page="../front/common/sidebarUser.jsp" />
	<div id="map" class="p-0"></div>

	<div class="content col fc-main">
		<div class="container-fluid mt-4">
			<div class="header-container mb-4">
				<h2 class="fw-bold">Paniers</h2>
			</div>
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 g-4 mx-auto"
					style="max-width: 95%;">
					<c:forEach var="contract" items="${contracts}">
						<div class="col" style="width: 30%; max-width: 30%;">
							<div class="card contract-card rounded-3 border-main">
								<c:if test="${not empty contract.imageData}">
									<img class="card-img-top"
										src="data:${contract.imageType};base64,${contract.imageData}"
										alt="Image du contrat">
								</c:if>
								<div class="card-body">
									<h3 class="card-title fw-bold">${contract.contractName}</h3>
									<p class="card-text">
										<u>${contract.contractType.displayName}</u>
									</p>
									<p class="card-text">${contract.contractDescription}</p>
									<p class="card-text fw-bold text-end">${contract.contractPrice}
										€ <br> <em>${contract.deliveryRecurrence.displayName}</em>
									</p>
									<a
										href="<c:url value='/${tenancyAlias}/shop/contracts/${contract.id}' />"
										class="btn btn-main rounded-pill bg-main">Voir les détails</a>
								</div>
							</div>
						</div>
					</c:forEach>
					<c:if test="${empty contracts}">
						<div class="col-12">
							<p class="text-center">Aucun contrat disponible pour cette
								AMAP.</p>
						</div>
					</c:if>
				</div>
			</div>
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
