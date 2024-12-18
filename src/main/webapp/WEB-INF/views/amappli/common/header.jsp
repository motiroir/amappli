<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<div class="container-fluid d-flex flex-row-reverse flex-md-row justify-content-between justify-content-md-evenly align-items-center">
    <!-- <h1>amappli</h1> -->
    <img id="logo-header" class="col-2 col-md-1 mb-2" src="<c:url value='/resources/img/logo_amappli_peach_2.png' />" />
    <nav class="navbar navbar-expand-md d-flex justify-content-md-evenly align-content-center">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav-content"
            aria-controls="nav-content" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="nav-content">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="#" class="nav-link" data-bs-toggle="collapse"
                        data-bs-target=".navbar-collapse.show">Fonctionnalités</a>
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
                        data-bs-target=".navbar-collapse.show">Se connecter</a>
                </li>
                <li class="nav-item m-1 ">
                    <a href="#" class="btn btn-success" data-bs-toggle="collapse"
                        data-bs-target=".navbar-collapse.show">Créer mon site</a>
                </li>
            </ul>
        </div>
    </nav>
</div>