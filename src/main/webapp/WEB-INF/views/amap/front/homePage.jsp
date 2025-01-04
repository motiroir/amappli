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
	href="<c:url value='/resources/css/amap/homePage.css' />">

<title>Page d'accueil - ${tenancyName}</title>

</head>

<body class="${cssStyle} light ${font}-title ${font}-button">
<div class="d-flex flex-column min-vh-100">
<header class="fc-main bg-main">
			<jsp:include page="common/header-amap.jsp" />
		</header>

<!-- 	 <div id="map"></div>  -->
	
	
	<div class="hero fc-main">
		<div class="hero-content">
			<h1 class="hero-text">Bienvenue sur le site de ${tenancyName}</h1>
			<h2 class="hero-text">${tenancySlogan}</h2>

			<div class="buttons">
				<button class="btn btn-info">Qui Sommes-nous ?</button>
				<a class="btn btn-info" href="amap/amaplogin/signup">Adhérer à
					l'AMAP</a>
			</div>
		</div>
		<div class="hero-image fc-main">
			<img src="<c:url value='/resources/img/imageHomePage.png' />"
				alt="Légumes frais" class="image-box">
		</div>
		
		
	</div>

	<!-- Affichage du block de présentation -->
<c:if test="${not empty presentationBlock}">
    <div class="presentation-section">
        <h2>Qui sommes-nous ?</h2>
        <div class="presentation-block">
            <div class="presentation-text">
                <h3>${presentationBlock.contentTitle}</h3>
                <p>${presentationBlock.contentText}</p>
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
</c:if>

<!-- Affichage des ContentBlock avec isValue == true -->
<c:if test="${not empty valueBlocks}">
    <div class="values-section ">
        <h2>Nos Valeurs</h2>
        <div class="value-blocks">
            <c:forEach items="${valueBlocks}" var="block">
                <div class="value-block">
                    <c:if test="${not empty block.contentImg}">
                        <!-- Affichage de l'image encodée en Base64 pour chaque ContentBlock -->
                       <img src="data:${block.contentImgTypeMIME};base64,${block.contentImg}" 
     alt="${block.contentTitle}" 
     class="value-image" />

                    </c:if>
                    <h3>${block.contentTitle}</h3>
                    <p>${block.contentText}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

		<footer class="fc-main bg-main">
			<jsp:include page="common/footer-amap.jsp" />
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
	<%-- <script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>  
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"></script> --%>
		
</body>
</html>