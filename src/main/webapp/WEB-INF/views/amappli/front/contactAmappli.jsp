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
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
 <link rel="stylesheet"
	href="<c:url value='/resources/css/amap/contactPage.css' />">

<title>Contact Amappli</title>

</head>
<body class="theme-1 dark bg-main">


   <header class="fc-main bg-main">
		<jsp:include page="../common/header.jsp" />
	</header>

  <div id="map"></div>  

	<div class="container mt-5">
		<div class="contact-container">
			<!-- Contact Information -->
			<div class="contact-info fc-100 bg-700">
				<h2>Comment nous contacter ?</h2>
				<div class="row">
					<div class="col-6">
						<p>
							<strong>Téléphone :</strong>
						</p>
						<p>+00 0123456501</p>
						<p>+00 0123456501</p>
					</div>
					<div class="col-6">
						<p>
							<strong>Adresse mail :</strong>
						</p>
						<p>contact@amappli.fr</p>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-6">
						<p>
							<strong>Emplacement :</strong>
						</p>
						<p>
							5 chemin de l'amour<br>12543 Villecity
						</p>
					</div>
					<div class="col-6">
						<p>
							<strong>Réseaux sociaux :</strong>
						</p>
						<p>
							<a href="#" class="text-white me-2"><i class="bi bi-facebook"></i></a>
							<a href="#" class="text-white me-2"><i class="bi bi-instagram"></i></a>
						</p>
					</div>
				</div>
				<div class="map mt-4">
					<img src="<c:url value='/resources/img/carte-contact.png' />"
						alt="Carte" class="img-fluid">
				</div>
			</div>

			<!-- Contact Form -->
			<div class="contact-form fc-100 bg-100">
				<h2>Contactez-nous</h2>
				<form action="/submit" method="POST">
					<div class="row mb-3 fc-main">
						<div class="col-6">
							<label for="nom" class="form-label">Votre nom</label> <input
								type="text" class="form-control" id="nom" name="nom"
								placeholder="Lamant" required>
						</div>
						<div class="col-6">
							<label for="prenom" class="form-label">Votre prénom</label> <input
								type="text" class="form-control" id="prenom" name="prenom"
								placeholder="Marie" required>
						</div>
					</div>
					<div class="mb-3 fc-main">
						<label for="email" class="form-label">Votre email</label> <input
							type="email" class="form-control" id="email" name="email"
							placeholder="exemple@mail.fr" required>
					</div>
					<div class="mb-3 fc-main">
						<label for="message" class="form-label">Votre message</label>
						<textarea class="form-control" id="message" name="message"
							rows="4" required></textarea>
					</div>
					<button type="submit" class="btn rounded-pill btn-500 ">Envoyer</button>
				</form>
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
