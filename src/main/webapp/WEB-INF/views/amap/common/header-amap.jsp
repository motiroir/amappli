<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> header Amap</title>
    <link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light">
    <div class="container">
      <a class="navbar-brand" href="#">
      <img src="<c:url value='/resources/img/${graphism.logo}' />" alt="Logo" style="max-width: 150px;" />
        <span>${tenancyName}</span>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <a class="nav-link" href="#">La boutique</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Qui sommes-nous ?</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contact</a>
          </li>
          <li class="nav-item">
            <a class="btn rounded-pill btn-500 px-4" href="#">Se connecter</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>


</body>
</html>

