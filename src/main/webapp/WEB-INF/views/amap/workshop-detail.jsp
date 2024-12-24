<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String currentMainMenu = "products"; // Détermine la rubrique active
String currentPage = "workshops"; // Détermine la sous-rubrique active
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Détails de l'Atelier</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<style>
    .header-container {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .form-control:read-only {
        background-color: #f8f9fa;
        color: #6c757d;
        border: none;
        cursor: not-allowed;
    }
</style>
</head>
<body>
    <div>
        <%@ include file="/WEB-INF/views/amap/common/sidebarAdmin.jsp"%>
    </div>
    <div class="content" style="margin-left: 150px;">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-lg-10">
                    <div class="form-container">
                        <div class="header-container">
                            <a href="<c:url value='/amap/workshops/list' />" class="btn-back">
                                <i class="bi bi-arrow-left-circle"></i>
                            </a>
                            <h2 class="mb-4" style="font-weight: bold; text-align: left;">Détails de l'atelier</h2>
                            <a href="/Amappli/amap/workshops/edit/${workshop.id}" class="btn btn-primary">Modifier l'atelier</a>
                        </div>
                        <form>
                            <div class="row">
                                <!-- Première colonne -->
                                <div class="col-md-4">
                                    <div class="mb-3">
                                        <label class="form-label">Nom de l'atelier</label>
                                        <input type="text" class="form-control" value="${workshop.workshopName}" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Description</label>
                                        <textarea class="form-control" rows="3" readonly>${workshop.workshopDescription}</textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Prix</label>
                                        <input type="text" class="form-control" value="${workshop.workshopPrice}€" readonly>
                                    </div>
                                </div>

                                <!-- Deuxième colonne -->
                                <div class="col-md-4">
                                    <div class="mb-3">
                                        <label class="form-label">Date et Heure</label>
                                        <input type="text" class="form-control" value="${workshop.workshopDateTime}" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Durée</label>
                                        <input type="text" class="form-control" value="${workshop.workshopDuration} minutes" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Lieu</label>
                                        <input type="text" class="form-control" value="${workshop.location}" readonly>
                                    </div>
                                </div>

                                <!-- Troisième colonne -->
                                <div class="col-md-4">
                                    <div class="mb-3">
                                        <label class="form-label">Participants Minimum</label>
                                        <input type="text" class="form-control" value="${workshop.minimumParticipants}" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Participants Maximum</label>
                                        <input type="text" class="form-control" value="${workshop.maximumParticipants}" readonly>
                                    </div>
                                    <div class="mb-3 text-center">
                                        <c:if test="${not empty workshop.imageData}">
                                            <img src="data:${workshop.imageType};base64,${workshop.imageData}" alt="Image de l'atelier" style="max-width: 100%; border-radius: 8px; object-fit: cover;">
                                        </c:if>
                                    </div>
									<div class="mb-3">
										<label class="form-label">Date de création le
											${workshop.dateCreation}</label>
									</div>
                                </div>
                            </div>
                            <div class="text-center">
                                <button type="button" class="btn btn-primary" id="editButton">Modifier</button>
                                <button type="submit" class="btn btn-success d-none" id="saveButton">Enregistrer</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
</body>
</html>
