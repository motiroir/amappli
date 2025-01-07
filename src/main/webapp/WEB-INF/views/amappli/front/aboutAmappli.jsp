<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/amappli/aboutAmappli.css' />">

<title>Qui sommes-nous ?</title>


</head>
<body class="theme-1 dark bg-main">


   <header class="fc-main bg-main">
		<jsp:include page="../common/header.jsp" />
	</header>

   <div id="map"></div> 
	 

  <h2 class="title text-center fc-300">Qui sommes-nous ?</h2><hr class="bg-300">

	<div class="container py-2 flex-grow-1">
		<div class="row align-items">
			<div class="col-lg-9 col-md-12 text-column">
				<div class="mission-card">
					<h2 class="value-title">Notre mission</h2>
					<p class="mission-text">
						<strong>Amappli</strong> est une solution numérique innovante
						conçue pour simplifier la gestion des paniers, des contrats, et
						des adhésions au sein des AMAPs. Notre plateforme permet de
						réduire considérablement le temps consacré aux tâches
						administratives tout en offrant une expérience fluide et
						accessible à tous les utilisateurs.
					</p>
					<p class="mission-text">Pensée pour les AMAPs, par des
						passionnés des circuits courts et de l'agriculture biologique,
						notre application transforme vos défis en opportunités. Accessible
						en ligne, intuitive et sécurisée.</p>
					<p class="mission-text">Amappli vous permet de consacrer plus
						de temps à l'essentiel : soutenir une agriculture paysanne et
						durable.</p>
					<p class="footer-text">Avec Amappli, gérez moins, partagez
						plus, vivez mieux votre AMAP !</p>
				</div>
			</div>
			<div class="col-lg-3 col-md-12 image-column">
				<img src="<c:url value='/resources/img/amapplii.png' />"
					alt="Logo Amappli" class="logo">

			</div>
		</div>
	</div>

<div class="container py-2">
    <div class="row align-items mb-0">
        <div class="col-lg-4 col-md-12 image-column2">
            <img src="<c:url value='/resources/img/team1.png' />"
                alt="team" class="team">
        </div>
        <div class="col-lg-8 col-md-12 text-column">
            <div class="value-card">
                <h2 class="value-title">Nos Valeurs</h2>
                <p class="value-text">
                 Chez Amappli, nos valeurs reflètent notre engagement envers une agriculture locale, responsable et durable.
                </p>
                <ul class="value-text">
                    <li><strong>Soutien aux producteurs locaux :</strong> Nous aidons les agriculteurs à valoriser leur travail en favorisant les circuits courts et les produits biologiques.</li>
                    <li><strong>Simplification et accessibilité :</strong> Avec notre plateforme intuitive, nous rendons la gestion des AMAP facile et accessible à tous.</li>
                    <li><strong>Digitalisation accompagnée :</strong> Nous vous accompagnons dans la transition numérique, en simplifiant la gestion de votre AMAP sans compromis sur vos valeurs.</li>
                    <li><strong>Développement et connexion des AMAP :</strong> Nous renforçons le maillage des AMAP à travers le territoire français, créant un réseau solidaire et connecté.</li>
                    <li><strong>Engagement environnemental :</strong> Nous plaçons la préservation de l'environnement au cœur de notre mission, pour soutenir une agriculture plus respectueuse de la planète.</li>
                </ul>
            </div>
        </div>
    </div>
    
  <div class="row mt-0">
    <div class="col-lg-12 col-md-12 text-column">
        <div class="value">
            <p class="value-text">
                <strong> Amappli </strong>,  c’est bien plus qu’une application, c’est une vision : celle d’un avenir où technologie
                et agriculture locale s’unissent pour un impact positif sur nos communautés et notre environnement.
            </p>
        </div>
    </div>
</div>

</div>
 
 
 <div class="container py-0">
    
    <div class="row align-items-center justify-content-center team-card">
    <h2 class="value-title">Notre Équipe</h2>
        <!-- Jean Dupont -->
        <div class="col-md-4 text-center">
            <div class="team">
                <img src="<c:url value='/resources/img/jeanDupont.png' />" alt="Jean Dupont" class="team">
                <p class="team-name"><strong>Jean Dupont</strong></p>
                <p class="team-role">Fondateur et CEO</p>
            </div>
        </div>
        <!-- Sophie Martin -->
        <div class="col-md-4 text-center">
            <div class="team">
                <img src="<c:url value='/resources/img/sophie.png' />" alt="Sophie Martin" class="team">
                <p class="team-name"><strong>Sophie Martin</strong></p>
                <p class="team-role">Responsable Clientèle</p>
            </div>
        </div>
        <!-- Paul Leroy -->
        <div class="col-md-4 text-center">
            <div class="team">
                <img src="<c:url value='/resources/img/paul.png' />" alt="Paul Leroy" class="team">
                <p class="team-name"><strong>Paul Leroy</strong></p>
                <p class="team-role">Développeur</p>
            </div>
        </div>
        
    </div>
</div>
</div>

	<footer class="container-fluid fc-main bg-main">
		<jsp:include page="../common/footer.jsp" />
	</footer>

	<script	src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
	<script	src="<c:url value='/resources/js/amappli/loading-when-visible.js' />"></script>
	<script	src="<c:url value='/resources/js/amappli/theme-swap.js' />"></script>
	 <script	src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script	src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>  

</body>
</html>