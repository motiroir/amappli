<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Footer Amap</title>
    <link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/amap/common/footer.css' />">
    
   
</head>
<body>
    <footer class="custom-footer-bg text-dark py-4">
        <div class="container">
            <div class="row">
                <!-- Colonne "Notre histoire" -->
                <div class="col-md-4">
                    <h5 class="fw-bold">Notre histoire</h5>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-dark text-decoration-none">A propos</a></li>
                        <li><a href="#" class="text-dark text-decoration-none">Partenaires</a></li>
                        <li><a href="#" class="text-dark text-decoration-none">Nos produits</a></li>
                        <li><a href="#" class="text-dark text-decoration-none">Nos événements</a></li>
                    </ul>
                </div>
                <!-- Colonne "Une question ?" -->
                <div class="col-md-4">
                    <h5 class="fw-bold">Une question ?</h5>
                    <ul class="list-unstyled">
                        <li><a href="#" class="text-dark text-decoration-none">Contact</a></li>
                        <li><a href="#" class="text-dark text-decoration-none">FAQ</a></li>
                    </ul>
                    <!-- RÃ©seaux sociaux -->
                    <div>
                        <h6 class="fw-bold">Réseaux sociaux :</h6>
                        <a href="#" class="text-dark me-2"><i class="bi bi-twitter"></i></a>
                        <a href="#" class="text-dark me-2"><i class="bi bi-facebook"></i></a>
                        <a href="#" class="text-dark me-2"><i class="bi bi-instagram"></i></a>
                    </div>
                </div>
                <!-- Colonne "OÃ¹ nous trouver ?" -->
                <div class="col-md-4">
                    <h5 class="fw-bold">Où nous trouver ?</h5>
                    <p class="mb-0">Adresse:</p>
                    <p>24 rue de Paris<br>94800 Villejuif</p>
                </div>
            </div>
            <!-- Copyright -->
            <div class="text-center mt-4">
                <p class="mb-0">&copy; 2024 Groupe 1 - Projet 3 Isika - Tous droits réservés</p>
            </div>
        </div>
    </footer>
    
    <%-- <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet"> --%>
    <script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js'/>"></script>
</body>
</html>
