<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String currentMainMenu="users" ; // Détermine la rubrique active
	String currentPage="users" ; // Détermine la sous-rubrique active
	request.setAttribute("currentMainMenu", currentMainMenu);
	request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Liste des adhérents</title>
	<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />" rel="stylesheet">
	<%-- <link href="<c:url value='/resources/bootstrap/bootstrap-icons.css' />" rel="stylesheet"> --%>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="row theme-1 light">

	<header class="fc-main bg-main">
	<!-- Inclusion du header -->
			<jsp:include page="../common/headerAdmin.jsp" />
	</header>
	<!-- Inclusion de la sidebar -->
		<jsp:include page="../common/sidebarAdmin.jsp" />

<div id="map"></div>

	<!-- Contenu principal -->
	<div class="content col">
		<div class="container-fluid mt-2">
			<div class="row justify-content-center">
				<div class="col-12">
					<div class="search-bar d-flex align-items-center mb-3">
						<!-- Nombre total d'adhérents -->
						<div class="me-4" style="font-size: 22px; font-weight: 400;">
							<span>${users.size()} éléments</span><br/>
							<a href="<c:url value='/${tenancyAlias}/backoffice/users/generateFakes' />">ajouter 20 users</a>
						</div>
						<!-- Dropdown pour trier -->
						<div class="d-flex align-items-center me-4">
							<label for="sortBy" class="me-2" style="font-size: 22px; font-weight: 400;">Trié par</label>
							<select id="sortBy" class="form-select custom-select" style="width: auto;">
								<option value="name">Nom du domaine</option>
								<option value="producer">Nom du producteur</option>
							</select>
						</div>
						<!-- Barre de recherche -->
						<div>
							<input type="text" id="searchBar" class="form-control custom-input"
								placeholder="Rechercher..." style="width: 200px;">
						</div>
					</div>
					<div class="table-container d-flex justify-content-between align-items-center">
						<h2 style="font-weight: bold;">Liste des adhérents</h2>
						<a href="<c:url value='/${tenancyAlias}/backoffice/users/form'/>" class="btn-create">
							<span class="icon">+ </span>Créer un adhérent
						</a>
					</div>
					<!-- Mode tableau -->
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Nom</th>
								<th>Email</th>
								<th>Credit Balance</th>
								<th>Rôle</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${users}">
								<tr>
									<td>${user.contactInfo.firstName}
										${user.contactInfo.name}</td>
									<td class="text-break">${user.email}</td>
									<td>${user.creditBalance == null ? 0 : user.creditBalance}</td>
									<td>
										<c:forEach var="role" items="${user.roles}">
											<c:choose>
	                                            <c:when test="${role.roleId == 1}">
	                                            	<span>Admin</span>
	                                            </c:when>
	                                            <c:when test="${role.roleId == 2}">
	                                            	<span>Adhérent</span>
	                                            </c:when>
	                                            <c:when test="${role.roleId == 3}">
	                                            	<span>Producteur</span>
	                                            </c:when>
	                                            <c:otherwise>
	                                            	<span>${role.name.toLowerCase()}</span>
	                                            </c:otherwise>
                                            </c:choose>
										</c:forEach>
									</td>
									<td>
										<div class='d-flex justify-content-start align-items-center'>
											<a href="<c:url value='/${tenancyAlias}/backoffice/users/details/${user.userId}' />"
												class="btn-view"> <i class="bi bi-eye"></i>
											</a>
											<form:form action="delete/${user.userId}" style="display: inline;" onsubmit="return confirm('Voulez-vous vraiment supprimer l'adhérent ${user.contactInfo.firstName} ${user.contactInfo.name} ?');">
												<button type="submit" class="btn btn-delete">
													<i class="bi bi-trash"></i>
												</button>
											</form:form>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
	<script src="<c:url value='/resources/js/amap/admin/user-list.js' />"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />"></script>
</body>

</html>