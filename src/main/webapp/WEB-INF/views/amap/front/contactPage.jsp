<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
        href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
     <link rel="stylesheet"
	href="<c:url value='/resources/css/amap/contactPage.css' />">
        
    <title>Page de contact</title>

    
    
</head>
<body>

    <!-- Inclure le header -->
    <jsp:include page="common/header-amap.jsp" />

    <div class="container mt-5">
        <div class="contact-container">
            <!-- Contact Information -->
            <div class="contact-info">
                <h2>Comment nous contacter ?</h2>
                <div class="row">
                    <div class="col-6">
                        <p><strong>Téléphone :</strong></p>
                         <p>${phoneNumber}</p>
                        
                    </div>
                    <div class="col-6">
                        <p><strong>Adresse mail :</strong></p>
                        <p>${email}</p>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-6">
                        <p><strong>Emplacement :</strong></p>
                       	<p>${addressLine1}</p>
						<p>${addressLine2}</p>
						<p>${addressPostCode}${addressCity}</p>
                    </div>
                    <div class="col-6">
                        <p><strong>Réseaux sociaux :</strong></p>
                        <p>
                            <a href="#" class="text-dark me-2"><i class="bi bi-facebook"></i></a>
                            <a href="#"  class="text-dark me-2"><i class="bi bi-instagram"></i></a>
                        </p>
                    </div>
                </div>
                <div class="map mt-4">
                    <img src="<c:url value='/resources/img/carte-contact.png' />" alt="Carte" class="img-fluid">
                </div>
            </div>

            <!-- Contact Form -->
            <div class="contact-form">
                <h2>Contactez-nous</h2>
                <form action="/submit" method="POST">
                    <div class="row mb-3">
                        <div class="col-6">
                            <label for="nom" class="form-label">Votre nom</label>
                            <input type="text" class="form-control" id="nom" name="nom" placeholder="Lamant" required>
                        </div>
                        <div class="col-6">
                            <label for="prenom" class="form-label">Votre prénom</label>
                            <input type="text" class="form-control" id="prenom" name="prenom"  placeholder="Marie" required>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Votre email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="exemple@mail.fr"  required>
                    </div>
                    <div class="mb-3">
                        <label for="message" class="form-label">Votre message</label>
                        <textarea class="form-control" id="message" name="message" rows="4" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-submit">Envoyer</button>
                </form>
            </div>
        </div>
    </div>

    <footer class="container-fluid fc-main bg-main">
		<jsp:include page="common/footer-amap.jsp" />
	</footer>

</body>
</html>
