<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
String currentMainMenu = "users"; // Détermine la rubrique active
String currentPage = "users"; // Détermine la sous-rubrique active
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Détails du Contrat</title>
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
                            <a href="<c:url value='/tenancies/${tenancyId}/amap/admin/backoffice/suppliers/list' />" class="btn-back">
                                <i class="bi bi-arrow-left-circle"></i>
                            </a>
                            <h2 class="mb-4" style="font-weight: bold; text-align: left;">Détails du producteur</h2>
                        </div>
                        <form:form  method="POST" action="<c:url value='/tenancies/${tenancyId}/amap/admin/backoffice/suppliers/${user.userId }/update' />" enctype="multipart/form-data">
                            <div class="row">
                                <!-- Première colonne -->
                                <div class="col-md-4">
                                    <div class="mb-3">
                                        <label class="form-label">Nom de famille</label>
                                        <input type="text" class="form-control" value="${user.contactInfo.name}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Prénom</label>
                                        <input type="text" class="form-control" value="${user.contactInfo.firstName}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">E-mail</label>
                                        <input type="text" class="form-control" value="${user.email}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Envoyer un mail de réinitialisation du mot de passe</label>
                                        <a href="#" class="btn btn-secondary rounded-pill disabled">Envoyer un lien</a>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Balance crédit</label>
                                        <input type="number" class="form-control" value="${user.creditBalance}" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Date de fin de cotisation</label>
                                        <input type="text" class="form-control" value="PAS ENCORE IMPLÉMENTÉ" readonly>
                                    </div>
                                </div>

                                <!-- Deuxième colonne -->
                                <div class="col-md-4">
                                    <div class="mb-3">
                                        <label class="form-label">Adresse</label>
                                        <input type="text" class="form-control" value="${user.address.line2}" />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Complément d'adresse</label>
                                        <input type="text" class="form-control" value="${user.address.line1}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Code Postal</label>
                                        <input type="text" class="form-control" value="${user.address.postCode}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Ville</label>
                                        <input type="text" class="form-control" value="${user.address.city}">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Numéro de téléphone</label>
                                        <input type="text" class="form-control" value="${user.contactInfo.phoneNumber}">
                                    </div>
                                </div>

                                <!-- Troisième colonne -->
                                <div class="col-md-4">
                                    <div class="mb-3 text-center">
                                <%--         <c:if test="${not empty user.imageData}">
                                            <img src="data:${user.imageType};base64,${user.imageData}" alt="Image du contrat" style="max-width: 100%; border-radius: 8px; object-fit: cover;">
                                        </c:if> --%>
                                        <div class="mb-3">
                                            <label class="form-label">Roles :</label><br/>
                                            <c:forEach var="role" items="${allRoles }" >
                                            <c:choose>
	                                            <c:when test="${role.roleId == 1}">
	                                            	<label class="form-label" for="userRole${role.roleId }">Admin</label>
	                                            </c:when>
	                                            <c:when test="${role.roleId == 2}">
	                                            	<label class="form-label" for="userRole${role.roleId }">Adhérent</label>
	                                            </c:when>
	                                            <c:when test="${role.roleId == 3}">
	                                            	<label class="form-label" for="userRole${role.roleId }">Producteur</label>
	                                            </c:when>
	                                            <c:otherwise>
	                                            	<label class="form-label" for="userRole${role.roleId }">${role.name.toLowerCase()}</label>
	                                            </c:otherwise>
                                            </c:choose>
                                                <input id="role-box-${role.roleId }" type="checkbox" class="me-3" name="userRole${role.roleId }" <c:if test="${user.roles.contains(role)}"> checked </c:if> />
                                            </c:forEach>
                                        </div>
                                        <section id="supplier-section" class="d-none">
                                            <div class="mb-3">
                                                <label class="form-label">Exploitation</label>
                                                <input type="text" class="form-control" value="${user.companyDetails.companyName}">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">N° Siret</label>
                                                <input type="text" class="form-control" value="${user.companyDetails.siretNumber}">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Contrat en cours</label>
                                                <input type="text" class="form-control" value="PAS ENCORE IMPLÉMENTÉ" readonly>
                                            </div>
                                        </section>
                                    </div>
                                </div>
                            </div>
							<div class="d-flex justify-content-evenly my-5">
								<div class="col text-center">
									<button id="submit-button" type="submit" class="btn btn-success rounded-pill" >Valider les changements</button>
								</div>
								<div class="col text-center">
									<button type="reset" class="btn btn-danger rounded-pill">Annuler</button>
								</div>
							</div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
    <script src="<c:url value='/resources/js/amap/admin/user-details.js' />"></script>
    <script src="<c:url value='/resources/js/amap/admin/user-form.js' />"></script>
</body>
</html>
