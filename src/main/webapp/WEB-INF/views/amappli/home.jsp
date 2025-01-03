<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>AMAPPLI</title>
	<link rel="icon" type="image/png" href="<c:url value='/resources/img/icons/favicon-96x96.png'/>" sizes="96x96" />
	<link rel="icon" type="image/svg+xml" href="<c:url value='/resources/img/icons/favicon.svg'/>" />
	<link rel="shortcut icon" href="<c:url value='/resources/img/icons/favicon.ico'/>" />
	<link rel="apple-touch-icon" sizes="180x180" href="<c:url value='/resources/img/icons/apple-touch-icon.png'/>" />
	<link rel="manifest" href="<c:url value='/resources/img/icons/site.webmanifest'/>" />
	<link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css'/>" />
	<link rel="stylesheet" href="<c:url value='/resources/css/amappli/homepage.css'/>" />
	<!-- Mapbox CSS -->
    <link href="https://api.mapbox.com/mapbox-gl-js/v2.15.0/mapbox-gl.css" rel="stylesheet" />
</head>

<body class="theme-1 dark bg-main">

	<header class="fc-main bg-main">
		<jsp:include page="common/header.jsp" />
	</header>

    <!-- Conteneur pour la carte -->
    <%-- <div id="map"></div> --%>

	<section>
		<img id="main-picture" class="container-fluid p-0"
			src="<c:url value='/resources/img/ImageAccueil_WIP.png' />"/>
	</section>

	<section id="call-to-action" class="">
		<div class="container-fluid py-3 justify-content-center">
			<div class="col-12 row justify-content-around align-items-center text-center">
				<div id="catchphrase" class="col-12 col-md-4 text-center">
					<h1 class="col-12">
						amappli
					</h1>
					<span class="underline col-10 mx-auto my-2 d-block"> </span>
					<h2 class="col-12">
						vous avez l'amap<br>nous avons l'appli
					</h2>
				</div>
				<div class="col-12 col-md-7 mx-auto mt-2 p-3 text-center bg-700 rounded-3">
					<p class="col-12 text-center">
						<b>AMAPPLI</b> vous aide à mieux gérer votre AMAP pour vous
						concentrer sur l'essentiel. Grâce à son interface intuitive et
						conviviale, vous pouvez facilement créer, gérer et personnaliser
						votre site web.
					</p>
					<div class="col-12 d-flex justify-content-evenly">
						<a href="#" class="btn btn-100 rounded-pill px-4">Créer mon site</a>
						<a href="#" class="btn btn-900 rounded-pill px-4">Planifier une
							démonstration</a>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section id="advantages" class="container-fluid mt-3">
		<div class="title-holder text-center mx-auto mb-3">
			<h1 class="title mb-2">Tout pour vous aider</h1>
			<span class="underline col-8 mx-auto d-block"></span>
		</div>
		<div class="col-12 row mx-0 justify-content-center">
			<div class="col-12 col-md-3 col-xl-2 rounded-2 m-3 mx-xl-5 p-2 cardboard bg-100 container">
				<div class="col-12 d-inline-flex justify-content-between align-items-center px-1 mb-2">
					<h5 class="my-auto">Accompagnement</h5>
				</div>
				<p class="text-justify">AMAPPLI est intuitif et facile
					d'utilisation, cependant nos experts sont à votre disposition pour
					vous aider lors de la création de votre site et son paramétrage si
					vous en avez besoin</p>
			</div>
			<div class="col-12 col-md-3 col-xl-2 rounded-2 m-3 mx-xl-5 p-2 cardboard bg-100 container">
				<div class="col-12 d-inline-flex justify-content-between align-items-center px-1 mb-2">
					<h5 class="my-auto">Automatisation des tâches</h5>
				</div>
				<p class="text-justify">Tous vos processus deviennent
					automatisés : gestion des inscriptions, renouvellements,
					cotisations, newletters, etc.</p>
			</div>
			<div class="col-12 col-md-3 col-xl-2 rounded-2 m-3 mx-xl-5 p-2 cardboard bg-100 container">
				<div
					class="col-12 d-inline-flex justify-content-between align-items-center px-1 mb-2">
					<h5 class="my-auto">Expérience utilisateur boostée</h5>
				</div>
				<p class="text-justify">Avec votre site AMAPPLI vos utilisateurs
					profitent d'un site internet professionnel, fiable, efficace et
					moderne</p>
			</div>
			<div class="col-12 col-md-3 col-xl-2 rounded-2 m-3 mx-xl-5 p-2 cardboard bg-100 container">
				<div
					class="col-12 d-inline-flex justify-content-between align-items-center px-1 mb-2">
					<h5 class="my-auto">Personnalisation</h5>
				</div>
				<p class="text-justify">Parce que c'est votre AMAP,
					personnalisez votre logo, votre texte, vos couleurs, vos points de
					collecte, vos moyens de paiement, vos rôles...</p>
			</div>
			<div class="col-12 col-md-3 col-xl-2 rounded-2 m-3 mx-xl-5 p-2 cardboard bg-100 container">
				<div
					class="col-12 d-inline-flex justify-content-between align-items-center px-1 mb-2">
					<h5 class="my-auto">Suivi centralisé</h5>
				</div>
				<p class="text-justify">Toutes les informations des adhérents,
					des paniers, et des contrats sont regroupées en un seul endroit,
					accessible à tous les administrateurs de l'AMAP.</p>
			</div>
			<div class="col-12 col-md-3 col-xl-2 rounded-2 m-3 mx-xl-5 p-2 cardboard bg-100 container">
				<div
					class="col-12 d-inline-flex justify-content-between align-items-center px-1 mb-2">
					<h5 class="my-auto">Référencement et visibilité</h5>
				</div>
				<p class="text-justify">Les sites AMAPPLI sont réalisés de sorte
					que votre AMAP ressorte dans les premiers résultats des moteurs de
					recherche</p>
			</div>
			<div class="col-12 col-md-3 col-xl-2 rounded-2 m-3 mx-xl-5 p-2 cardboard bg-100 container">
				<div
					class="col-12 d-inline-flex justify-content-between align-items-center px-1 mb-2">
					<h5 class="my-auto">Données sécurisées</h5>
				</div>
				<p class="text-justify">La sécurité est une de nos priorités.
					Les données personnelles et bancaires de votre AMAP et celles de
					vos utilisateurs sont protégées afin qu'il n'y est aucune fuite</p>
			</div>
			<div class="col-12 col-md-3 col-xl-2 rounded-2 m-3 mx-xl-5 p-2 cardboard bg-100 container">
				<div
					class="col-12 d-inline-flex justify-content-between align-items-center px-1 mb-2">
					<h5 class="my-auto">Catalogue de produits</h5>
				</div>
				<p class="text-justify">Chaque producteur via son espace
					personnel, ajoute, modifie, supprime ses produits. Son tableau de
					bord lui permet de savoir où il est en dans la gestion de ses
					productions</p>
			</div>
		</div>
	</section>

	<section id="subscriptions" class="container-fluid mt-3 mx-auto">
		<div class="title-holder text-center mx-auto mb-3">
			<h1 class="title mb-2">Les abonnements</h1>
			<span class="underline col-8 mx-auto d-block"></span>
		</div>
		<div class="col-12 row mx-0 rounded-4 p-4 justify-content-evenly">
			<div id="pricing-1"
				class="pricing col-12 col-md-3 my-2 my-md-0 row text-center py-3 px-2 rounded-5 align-content-between bg-300">
				<h2 class="h4 fw-bold">Potager</h2>
				<p classe="my-2">Site opérationnel avec les fonctionnalités de base</p>
				<h3 class="h5 fw-bold">Gratuit</h3>
			</div>
			<div id="pricing-2"
				class="pricing col-12 col-md-3 my-2 my-md-0 row text-center py-3 px-2 rounded-5 align-content-between bg-400">
				<h2 class="h4 fw-bold">Verger</h2>
				<p classe="my-2">Plus d'outils avancés pour personnaliser et enrichir votre
					site</p>
				<h3 class="h5 fw-bold">50 € /an</h3>
			</div>
			<div id="pricing-3"
				class="pricing col-12 col-md-3 my-2 my-md-0 row text-center py-3 px-2 rounded-5 align-content-between bg-500">
				<h2 class="h4 fw-bold">Ferme</h2>
				<p classe="my-2">Toutes les fonctionnalités pour une gestion complète et
					professionnelle de votre AMAP</p>
				<h3 class="h5 fw-bold">100 € /an</h3>
			</div>
			<div class="mt-5 col-12 text-center">
				<a href="#"
					class="btn rounded-pill btn-100">Comparer les offres</a>
			</div>
		</div>
	</section>

	<section id="partners" class="container-fluid mt-3">
		<div class="title-holder text-center mx-auto mb-3">
			<h1 class="title mb-2">Ils utilisent AMAPPLI</h1>
			<span class="underline col-8 mx-auto d-block"></span>
		</div>
		<div class="row mx-auto p-4 justify-content-evenly">
			<div id="partner-1"	class="partner col-6 my-2 my-md-0 col-md-3 row text-center">
				<div
					class="border border-1 bg-100 border-secondary-subtle rounded-4 py-1 mx-1 align-content-center">
					<img class="col-12" src="<c:url value='/resources/img/Partner1.png' />" />
				</div>
			</div>
			<div id="partner-2"	class="partner col-6 my-2 my-md-0 col-md-3 row text-center">
				<div class="border border-1 bg-100 border-secondary-subtle rounded-4 py-1 mx-1 align-content-center">
					<img class="col-12" src="<c:url value='/resources/img/Partner2.png' />" />
				</div>
			</div>
			<div id="partner-3"	class="partner col-6 my-2 my-md-0 col-md-3 row text-center">
				<div class="border border-1 bg-100 border-secondary-subtle rounded-4 py-1 mx-1 align-content-center">
					<img class="col-12" src="<c:url value='/resources/img/Partner3.png' />" />
				</div>
			</div>
			<div id="partner-4"	class="partner col-6 my-2 my-md-0 col-md-3 row text-center">
				<div class="border border-1 bg-100 border-secondary-subtle rounded-4 py-1 mx-1 align-content-center">
					<img class="col-12" src="<c:url value='/resources/img/Partner4.png' />" />
				</div>
			</div>
		</div>
	</section>

	<footer class="container-fluid fc-main bg-main">
		<jsp:include page="common/footer.jsp" />
	</footer>

	<script	src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />" type="text/javascript"></script>
	<script	src="<c:url value='/resources/js/amappli/loading-when-visible.js' />" type="text/javascript"></script>
	<script	src="<c:url value='/resources/js/common/theme-swap.js' />" type="text/javascript"></script>
	<script	src="<c:url value='/resources/js/common/palette-swap.js' />" type="text/javascript"></script>
	<%-- <script	src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />" type="text/javascript"></script>
	<script	src="<c:url value='/resources/js/common/mapbox/map.js' />" type="text/javascript"></script> --%>
</body>

</html>