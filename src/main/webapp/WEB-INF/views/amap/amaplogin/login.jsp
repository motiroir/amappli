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
	href="<c:url value='/resources/css/amap/login-amap.css' />">
	<title>Connexion</title>
</head>
<body class="${cssStyle} light ${font}-title ${font}-button">
<div class="d-flex flex-column min-vh-100">
 <header class="fc-main bg-main">
			<jsp:include page="../front/common/header-amap.jsp" />
		</header> 
		 <div id="map"></div>  

	<div class="container py-5">
		<div class="d-flex justify-content-between align-items-center mb-4">
			<h1 class="h3 fw-bold fc-300">Se connecter</h1>
			<a href="/Amappli/tenancies/${tenancyAlias}/home" class="btn-close" aria-label="Retour"></a>
		</div>

		<form
			action="${pageContext.request.contextPath}/tenancies/${tenancyAlias}/amap/amaplogin/login"
			method="post">

			<!-- Affichage des erreurs générales -->
			<c:if test="${not empty error}">
				<div class="alert alert-danger">
					<strong>Erreur :</strong> ${error}
				</div>
			</c:if>

			<!-- Champs pour le formulaire -->
			<div class="mb-3">
				<input type="email" class="form-control" id="email" name="email"
					placeholder="Votre adresse mail" required value="${loginDTO.email}">
			</div>

			<div class="mb-3">
				<input type="password" class="form-control" id="motDePasse"
					name="password" placeholder="Votre mot de passe" required>
			</div>

			<div class="text-center">
				<a type="submit" class="btn btn-500 rounded-pill">Se connecter</a>
			</div>
		</form>
		<br>
		<div class="text-center mt-4">
			<p class="fc-300" >Pas encore inscrit.e ?</p>
			<hr class="bg-300">
			<a
				href="${pageContext.request.contextPath}/tenancies/${tenancyAlias}/amap/amaplogin/signup"
				class="btn btn-500 px-4 rounded-pill">S'inscrire</a>
		</div>

	</div>
	 <footer class="fc-main bg-main">
			<jsp:include page="../front/common/footer-amap.jsp" />
		</footer> 
	<script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>

	<script>
		var styleMapboxLight = "${mapStyleLight}"
		var styleMapboxDark = "${mapStyleDark}"

		/* 		REMPLACER par les coordinates -> à mettre en place dans la database du tenancy
		 const tenancyCity = "${tenancy.getAddress().getCity()}"
		 const tenancyPostCode = "${tenancy.getAddress().getPostCode()}" 
		 */
	</script>

	<script src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>  
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"></script>  
</body>
</html>
