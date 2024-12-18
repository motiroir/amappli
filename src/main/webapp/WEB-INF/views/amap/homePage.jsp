<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil </title>
<link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/resources/css/amap/homePage.css' />">
</head>
<body>
    <header>
     </header>
     
    <!-- Inclure le header -->
<%--     <jsp:include page="/WEB-INF/views/common/header.jsp" /> --%>
    <jsp:include page="common/header.jsp" />
    
    
    
        <h1>Bienvenue sur notre AMAP</h1>
        </br>
        
        
        </br>
   
    
    
      
    <h2>${homePageContent.subTitle}</h2>
    
      </br>
   
   
     </br>
   
    
    
    
    

  <!-- Inclure le footer -->
    <jsp:include page="common/footer.jsp" />
</body>
</html>