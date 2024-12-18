<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
	
<link rel="stylesheet" href="<c:url value='/resources/css/header.css' />">

</head>
<div class="container-fluid d-flex flex-row-reverse flex-md-row justify-content-between justify-content-md-evenly align-items-center">
  <div>
    <img id="logo-header" class="col-2 col-md-1 mb-2" src="<c:url value='/resources/img/' />" />
    <span class="me-3 fw-bold">AMAP</span></div>
    <nav class="navbar navbar-expand-md d-flex justify-content-md-evenly align-content-center">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav-content"
            aria-controls="nav-content" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="nav-content">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="#" class="nav-link" data-bs-toggle="collapse"
                        data-bs-target=".navbar-collapse.show">La Boutique</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link" data-bs-toggle="collapse"
                        data-bs-target=".navbar-collapse.show">Qui sommes-nous ?</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link" data-bs-toggle="collapse"
                        data-bs-target=".navbar-collapse.show">Contact</a>
                </li>
                <li class="nav-item m-1">
                    <a href="#" class="btn btn-outline-light" class="" data-bs-toggle="collapse"
                        data-bs-target=".navbar-collapse.show">S'inscrire</a>
                </li>
            </ul>
        </div>
    </nav>
</div>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</html>