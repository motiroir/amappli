<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
<link rel="stylesheet"
	href="<c:url value='/resources/css/amap/amapPage.css' />">
<title>C'est quoi une Amap ?</title>
</head>
 <style>
        .circle-diagram {
            max-width: 100%;
            height: auto;
            margin: 0 auto;
        }
        .amap-text {
            text-align: justify;
        }
        .green-text {
            color: #007F00;
            font-weight: bold;
        }
        .ab-logo {
            width: 100px;
        }
    </style>

<body>

	<!-- Inclure le header -->
	<jsp:include page="common/header.jsp" />

 <div class="container my-5">
        <div class="row">
            <div class="col-lg-6 col-md-12 text-center">
                <img src="<c:url value='/resources/img/schemaAmap.png' />" alt="Schéma explicatif AMAP" class="img-fluid circle-diagram">
            </div>
            <div class="col-lg-6 col-md-12">
                <h1 class="text-center green-text">AMAP ?</h1>
                <div class="amap-text">
                    <p><strong>A</strong> comme <strong>Association</strong><br>
                        En AMAP, pas d'intermédiaire commercial. C'est le seul système qui reverse 100% du montant du panier à l'agriculteur·rice.</p>
                    <p><strong>M</strong> comme <strong>Maintien</strong></p>
                    <p><strong>AP</strong> comme <strong>Agriculture Paysanne</strong></p>
                    <p>L'AMAP soutient une agriculture de proximité qui concilie protection de la nature et emploi local agricole.</p>
                </div>
                <div class="text-end mt-4">
                    <img src="<c:url value='/resources/img/logoAB.png' />"  alt="LogoAB" class="ab-logo">
                </div>
            </div>
        </div>
    </div>
    
    
    
    




	<!-- Inclure le footer -->
	<jsp:include page="common/footer.jsp" />
</body>
</html>