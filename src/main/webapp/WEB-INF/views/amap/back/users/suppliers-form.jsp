<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String currentMainMenu = "users"; // Détermine la rubrique active
String currentPage = "suppliers"; // Détermine la sous-rubrique active
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ajouter un producteur</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet" />
<link
	href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />"
	rel="stylesheet" />
<link href="<c:url value='/resources/bootstrap/bootstrap-icons.css' />"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
.header-container {
	display: flex;
	align-items: center;
	gap: 10px;
}
</style>
</head>
<body>
	<nav>
		<%@ include file="/WEB-INF/views/amap/back/common/sidebarAdmin.jsp"%>
	</nav>
	<div class="content" style="margin-left: 150px;">
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="form-container">
						<div class="header-container">
							<a
								href="<c:url value='/${tenancyAlias}/backoffice/suppliers/list' />"
								class="btn-back"> <i class="bi bi-arrow-left-circle"></i>
							</a>
							<h2 class="mb-4" style="font-weight: bold; text-align: left;">Ajouter
								un producteur</h2>
						</div>
						<form:form
							action="/Amappli/${tenancyAlias}/backoffice/suppliers/add"
							enctype="multipart/form-data">
							<div class="row">
								<!-- Première colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label class="form-label" for="name">Nom de famille</label>
										<form:input	path="name" type="text" class="form-control" name="name"
											placeholder="Dupont"/>
									</div>
									<div class="mb-3">
										<label class="form-label" for="firstName">Prénom</label>
										<form:input path="firstName" type="text" class="form-control" name="firstName"
											placeholder="Anaïs"/>
									</div>
									<div class="mb-3">
										<label class="form-label" for="email">E-mail</label>
										<form:input path="email" type="text" class="form-control" name="email"
											placeholder="anais.dupont@gmail.com"/>
									</div>
									<div class="mb-3">
										<label class="form-label" for="password">Mot de passe</label>
										<form:input path="password" type="password" class="form-control" name="password"/>
									</div>
									<div class="mb-3">
										<label class="form-label" for="confirmPassword">Confirmer
											le mot de passe</label>
											<form:input path="" type="password" class="form-control"
											name="confirmPassword"/>
									</div>
									<div class="mb-3">
										<label class="form-label" for="creditBalance">Balance
											crédit</label>
											<form:input path="creditBalance" type="number" class="form-control"
											name="creditBalance" value=0/>
									</div>
								</div>

								<!-- Deuxième colonne -->
								<div class="col-md-4">
									<div class="mb-3">
										<label class="form-label" for="line2">Adresse</label>
										<form:input path="line2" type="text" class="form-control" name="line2"
											placeholder="151" />
									</div>
									<div class="mb-3">
										<label class="form-label" for="line1">Complément
											d'adresse</label>
											<form:input path="line1" type="text" class="form-control"
											name="line1" placeholder="avenue de la République"/>
									</div>
									<div class="mb-3">
										<label class="form-label" for="postCode">Code Postal</label>
										<form:input path="postCode" type="text" class="form-control" name="postCode"
											placeholder="44190"/>
									</div>
									<div class="mb-3">
										<label class="form-label" for="city">Ville</label>
										<form:input path="city" type="text" class="form-control" name="city"
											placeholder="Clisson"/>
									</div>
									<div class="mb-3">
										<label class="form-label" for="phoneNumber">Numéro de
											téléphone</label>
											<form:input path="phoneNumber" type="text" class="form-control"
											name="phoneNumber" placeholder="0605040302"/>
									</div>
								</div>

								<!-- Troisième colonne -->
								<div class="col-md-4">
									<div class="mb-3 text-center">
										<%--         <c:if test="${not empty user.imageData}">
                                            <img src="data:${user.imageType};base64,${user.imageData}" alt="Image du contrat" style="max-width: 100%; border-radius: 8px; object-fit: cover;">
                                        </c:if> --%>
										<div class="mb-3">
											<label class="form-label" for="">Roles :</label><br />
											<c:forEach var="role" items="${allRoles }">
												<c:choose>
													<c:when test="${role.roleId == 1}">
														<label class="form-label" for="role-box-${role.roleId }">Admin</label>
													</c:when>
													<c:when test="${role.roleId == 2}">
														<label class="form-label" for="role-box-${role.roleId }">Adhérent</label>
													</c:when>
													<c:when test="${role.roleId == 3}">
														<label class="form-label" for="role-box-${role.roleId }">Producteur</label>
													</c:when>
													<c:otherwise>
														<label class="form-label" for="role-box-${role.roleId }">${role.name.toLowerCase()}</label>
													</c:otherwise>
												</c:choose>
												<input id="role-box-${role.roleId }" type="checkbox"
													class="me-3" name="userRole"
													<c:if test="${role.roleId == 3}"> checked </c:if> />
											</c:forEach>
										</div>
										<section id="supplier-section" class="d-none">
											<div class="mb-3">
												<label class="form-label" for="">Exploitation</label> <input
													type="text" class="form-control"
													value="${user.companyDetails.companyName}">
											</div>
											<div class="mb-3">
												<label class="form-label" for="">N° Siret</label> <input
													type="text" class="form-control"
													value="${user.companyDetails.siretNumber}">
											</div>
										</section>
									</div>
								</div>
							</div>
							<div class="d-flex justify-content-evenly my-5">
								<div class="col text-center">
									<button id="submit-button" type="submit"
										class="btn btn-success rounded-pill"
										<c:if test="${isAdmin }">onclick="return confirm('Vous êtes sur le point d'enregistrer un nouvel utilisateur avec les droits d'administrateur, êtes vous sûr ?');"</c:if>>Valider
										la création</button>
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
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
	<script
		src="<c:url value='/resources/js/amap/admin/user-details.js' />"></script>
	<script src="<c:url value='/resources/js/amap/admin/user-form.js' />"></script>
</body>
</html>
