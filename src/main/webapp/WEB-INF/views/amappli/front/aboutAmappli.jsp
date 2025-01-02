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
	href="<c:url value='/resources/css/amap/-.css' />">

<title>Qui sommes-nous ?</title>

<style>
.mission-card {
	background-color: #ffffff;
	border-radius: 25px;
	padding: 20px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	margin-top: 40px;
	height: 100%;
	border: 1.5px solid #FFBE98;
}

.mission-text {
	color: #333333;
}

.mission-title {
	font-weight: bold;
	font-size: 1.2rem;
}

.footer-text {
	font-weight: bold;
}

.text-column {
	display: flex;
	flex-direction: column;
	justify-content: center;
	height: 100%;
}

.image-column img {
	border-radius: 25px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	border: 1.5px solid #FFBE98;
	display: flex;
	align-items: center;
	justify-content: center;
	max-height: 80%;
	object-fit: contain;
	display: block;
	margin-top: 40px;
}

.value-card {
	background-color: #ffffff;
	border-radius: 25px;
	padding: 20px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	margin-top: 40px;
	height: 100%;
	border: 1.5px solid #FFBE98;
}


</style>

</head>
<body>

	<%-- <header class="fc-main bg-main">
		<jsp:include page="common/header.jsp" />
	</header> --%>


	<div class="container py-4">
		<div class="row align-items-stretch">
			<div class="col-lg-7 col-md-12 text-column">
				<div class="mission-card">
					<h2 class="mission-title">Notre mission</h2>
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
			<div class="col-lg-5 col-md-12 image-column">
				<img src="<c:url value='/resources/img/amappli0.png' />"
					alt="Logo Amappli" class="logo">

			</div>
		</div>
	</div>

<div class="container py-4">
    <div class="row align-items-stretch">
        <div class="col-lg-5 col-md-12 image-column">
            <img src="<c:url value='/resources/img/team1.png' />"
                alt="team" class="team">
        </div>
        <div class="col-lg-7 col-md-12 text-column">
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
</div>







	

	<%-- <footer class="container-fluid fc-main bg-main">
		<jsp:include page="common/footer.jsp" />
	</footer> --%>

</body>
</html>