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
 
 <style>
 /* Styles de base */
body {
  margin: 0;
  padding: 0;
}

.hero {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
  background-color: #f9f9f9;
}

.hero-content {
  flex: 1;
  max-width: 50%;
  text-align: center;

}

.hero-content h1 {
  font-size: 2rem;
  margin-bottom: 10px;
}

.hero-content h2 {
  font-size: 1.5rem;
  color: #e67e22;
  margin-bottom: 20px;
}

.hero-content p {
  font-size: 1rem;
  line-height: 1.6;
  margin-bottom: 20px;
}

.buttons {
  display: flex;
  gap: 10px;
}
 .btn-info {
      background-color: #FFBE98 !important;
      color: black !important;
      border: none !important;
      border-radius: 20px !important;
      padding: 5px 25px !important;
      margin-right: 200px;
    }
    .btn-info :hover {
      background-color: #f2f2f2 !important;
    }
.hero-image {
  flex: 1;
  text-align: right;
}

.hero-image img {
  max-width: 100%;
  height: auto;
}

/* Responsive styles */
@media (max-width: 768px) {
  .hero {
    flex-direction: column;
    text-align: center;
  }

  .hero-content {
    max-width: 100%;
  }

  .hero-content h1 {
    font-size: 1.5rem;
  }

  .hero-content h2 {
    font-size: 1.2rem;
  }

  .hero-content p {
    font-size: 0.9rem;
  }

  .buttons {
    justify-content: center;
  }

}
 
 </style>

</head>

<body>
 
    <!-- Inclure le header -->
    <jsp:include page="common/header.jsp" />
    
  
<div class="hero">
  <div class="hero-content">
    <h1 class="hero-text">Bienvenue sur le site de  ${tenancyName}</h1>
     <h2 class="hero-text">${homePageContent.subTitle}</h2>
    <p>
      Si vous avez envie de manger sain, frais et local... des produits de saison, si vous avez envie d'apporter votre soutien aux producteurs de notre AMAP, au travers d’un partenariat, alors vous êtes sur le bon site !
    </p>
    <div class="buttons">
      <button class="btn btn-info">Qui Sommes-nous ?</button>
      <button class="btn btn-info">Adhérer à l'AMAP</button>
    </div>
  </div>
  <div class="hero-image">
  <img src="<c:url value='/resources/img/imageHomePage.png' />" alt="Légumes frais" class="image-box">
    
  </div>
</div>

    
    
    
    
  
                   
  <!-- Inclure le footer -->
    <jsp:include page="common/footer.jsp" />
</body>
</html>