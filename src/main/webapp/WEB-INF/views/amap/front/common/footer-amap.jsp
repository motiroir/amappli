<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">



<div class="container">
	<div class="row">
		<!-- Colonne "Notre histoire" -->
		<div class="nav-item col-md-4">
			<h5 class="fw-bold">Notre histoire</h5>
			<ul class="list-unstyled">
				<li><a href="#" class="nav-link">A propos</a></li>
				<li><a href="#" class="nav-link">Partenaires</a></li>
				<li><a href="#" class="nav-link">Nos produits</a></li>
				<li><a href="#" class="nav-link">Nos événements</a></li>
			</ul>
		</div>
		<!-- Colonne "Une question ?" -->
		<div class="nav-item col-md-4">
			<h5 class="fw-bold">Une question ?</h5>
			<ul class="list-unstyled">
				<li><a href="#" class="nav-link">Contact</a></li>
				<li><a href="#" class="nav-link">FAQ</a></li>
			</ul>
			<!-- RÃ©seaux sociaux -->
			<div>
				<h6 class="fw-bold">Réseaux sociaux :</h6>
				<a href="#" class="nav-link me-2"><i class="bi bi-facebook"></i></a>
				<a href="#" class="nav-link me-2"><i class="bi bi-instagram"></i></a>
			</div>
		</div>

		<div class="nav-item col-md-4">
			<h5 class="fw-bold">Où nous trouver ?</h5>
			<ul class="list-unstyled">
			<li>${tenancy.getTenancyName()}</li>
			<li>${tenancy.getAddress().getLine1()}</li>
			<li>${tenancy.getAddress().getLine2()}</li>
			<li>${tenancy.getAddress().getPostCode()} ${tenancy.getAddress().getCity()}</li>
			</ul>
		</div>
		<!-- Copyright -->
		<div class="text-center mt-4">
			<p class="mb-0">&copy; 2024 Groupe 1 - Projet 3 Isika - Tous
				droits réservés</p>
		</div>
	</div>
</div>