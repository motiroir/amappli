<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/amap/homePage.css' />">

<title>Page d'accueil - ${tenancyName}</title>

</head>

<body class="${cssStyle} light ${font}-title ${font}-button">
<div class="d-flex flex-column min-vh-100">
		<header class="fc-main bg-main">
			<jsp:include page="common/header-amap.jsp" />
			
		</header>

<div id="map"></div>   
	 
	
   <main class="hero">
		<div class="row g-0">
			<div class="col-12 col-lg-6 order-lg-2">
				<img src="<c:url value='/resources/img/imageHomePage.png' />" alt="Légumes frais" class="image-box">
			</div>
			<div class="col-12 col-lg-6 order-lg-1 hero-content min-vh-100 py-4 py-md-5">
				<h1 class="hero-text h1 fc-main">Bienvenue sur le site de ${tenancyName}</h1>
				<h2 class="hero-text h2 fc-main">${tenancySlogan}</h2>
				<div class="buttons mt-5 mt-md-4">
					<a href="#presentation-section" class="btn rounded-pill btn-500 px-4 mb-2 me-2">Qui Sommes-nous?</a>
					<a href="<c:url value='/amap/${tenancyAlias}/signup'/>" class="btn rounded-pill btn-500 px-4 mb-2 me-2">Adhérer à l' AMAP</a>
				</div>
			</div>
		</div>
	</main>

	<!-- Affichage du block de présentation -->
<div id="presentation-section" class="presentation-wrapper">
    <div class="presentation-section">
        <h2 class="h3 fw-bold fc-300">Qui sommes-nous ?</h2>
        <div class="presentation-block fc-main">
            <div class="presentation-text">
                <h3 class="h4 fw-bold fc-300">${presentationBlock.contentTitle}</h3>
                <p class="text-justify">${presentationBlock.contentText}</p>
            </div>
            <div class="presentation-image-container">
                <c:if test="${not empty presentationBlock.contentImg}">
                    <img src="data:${presentationBlock.contentImgTypeMIME};base64,${presentationBlock.contentImg}" 
                         alt="${presentationBlock.contentTitle}" 
                         class="presentation-image" />
                </c:if>
            </div>
        </div>
    </div>
</div>


<!-- Affichage des ContentBlock avec isValue == true -->
<c:if test="${not empty valueBlocks}">
    <div class="values-section ">
        <h2 class="h3 fw-bold fc-300">Nos Valeurs</h2>
        <div class="row justify-content-evenly">
            <c:forEach items="${valueBlocks}" var="block">
                <div class="col-2 shadow-sm bg-100 rounded-3 p-3">
                    <c:if test="${not empty block.contentImg}">
                        <!-- Affichage de l'image encodée en Base64 pour chaque ContentBlock -->
                       <img class="object-fit-contain col-12" src="data:${block.contentImgTypeMIME};base64,${block.contentImg}" 
     alt="${block.contentTitle}" 
     class="value-image" />

                    </c:if>
                    <h3 class="h4 fw-bold fc-300">${block.contentTitle}</h3>
                    <p class="text-justify">${block.contentText}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

		<footer class="fc-main bg-main">
			<jsp:include page="common/footer-amap.jsp" />
		</footer>
		</div>
		
		
	<script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>

	<script>
		var styleMapboxLight = "${mapStyleLight}";
		var styleMapboxDark = "${mapStyleDark}";
		var latitude = "${latitude}";
		var longitude = "${longitude}";
	</script>
	<script src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/js/common/mapbox/map.js' />" type="text/javascript"></script>  
	<script src="<c:url value='/resources/js/common/theme-swap.js' />" type="text/javascript"></script>  
		
</body>
</html>