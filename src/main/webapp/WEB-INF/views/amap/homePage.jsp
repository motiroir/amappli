<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/amap/homePage.css' />">

 <title>Page d'accueil - ${tenancyName}</title>

</head>

<body>
 
    <!-- Inclure le header -->
    <jsp:include page="common/header.jsp" />
    
  
    <!-- Section principale -->
    <section class="hero-section">
        <div class="container">
            <div class="row align-items-center">
                <!-- Texte -->
                <div class="col-md-6">
                    <h1 class="hero-text">Bienvenue sur le site de  ${tenancyName}</h1>
                    <h2 class="hero-text">${homePageContent.subTitle}</h2>
                    <p class="lead mt-3">
                        Si vous avez envie de manger sain, frais et local, des produits de saison, si vous avez envie d'apporter
                        votre soutien aux producteurs de notre AMAP au travers d'un partenariat, alors vous êtes sur le bon site !
                    </p>
                    <div class="mt-4">
                        <a href="#" class="btn btn-custom me-2">C'est quoi une Amap ?</a>
                        <a href="#" class="btn btn-custom">Adhérer à l'AMAP</a>
                    </div>
                </div>
                <!-- Image -->
                <div class="col-md-6 text-center">
                   <img src="<c:url value='/resources/img/imageHomePage.png' />" alt="Légumes frais" class="image-box">

                </div>
            </div>
        </div>
    </section>
    
    
    
    

  <!-- Inclure le footer -->
    <jsp:include page="common/footer.jsp" />
</body>
</html>