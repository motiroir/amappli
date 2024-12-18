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
<link rel="stylesheet" href="/resources/css/header.css">

<style>
body {
	margin: 0;
	font-family: ;
}

.header-bg {
	background-color: #FFBE98;
	padding: 10px 0;
}

.nav-link {
	color: black !important;
	font-weight: bold;
}

.logo {
	max-width: 50px;
}

.signup-btn {
    color: black ;
    background-color: white;
    border-color: #F29F05;
    border-radius: 25px;
    font-size: 15px;
    text-align: center;
    cursor: pointer;
    padding: 10px 30px 10px 30px;
    border: 1px solid #ffffff
}

.signup-btn:hover {
  background-color: white;
        color: #FFBE98;
        border: 1px solid #FFBE98
 
}

.icons i {
	font-size: 1.5rem;
	margin-left: 15px;
	color: black;
	position: relative;
}

.icons .badge {
	position: absolute;
	top: -5px;
	right: -10px;
	font-size: 0.7rem;
	background-color: red;
	color: white;
}


</style>
</head>

<header class="header-bg  py-4">
	<nav class="navbar navbar-expand-md fixed-top ">

		<div class="container">
			
			<div class="col-2 d-flex align-items-center">
				<img src="" alt="Logo" class="logo">
				<span class="me-3 fw-bold">AMAP</span>
			</div>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-center"
				id="navbarNav">

				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="#">Accueil</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Nos
							Valeurs</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Nos
							Agriculteurs</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Nos
							Produits</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Nous
							contacter</a></li>
				</ul>
			</div>
			
            <div class="col-2 text-end">
                <a href="#" class="signup-btn">S'inscrire</a>
            </div>
            
		</div>
	</nav>
	


	
</header>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</html>





<!-- 	<div class="col-2 text-end icons">
		<a href="#" class="text-decoration-none position-relative"> <i
			class="bi bi-person"></i>
		</a> <a href="#" class="text-decoration-none"> <i class="bi bi-heart"></i>
		</a> <a href="#" class="text-decoration-none position-relative"> <i
			class="bi bi-cart"></i>
		</a>
	</div> -->