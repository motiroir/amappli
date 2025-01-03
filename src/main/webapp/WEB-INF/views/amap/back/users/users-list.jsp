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
	<link href="<c:url value='/resources/css/common/utils.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/bootstrap/bootstrap-icons.min.css' />" rel="stylesheet">
</head>
<body class="row ${cssStyle} light ${font}-title ${font}-button">

	<header class="fc-main bg-main border-1 border-alt">
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
						<div class="me-4 fs-5 fc-main">
							<span>${users.size()} éléments</span><br/>
							<a href="<c:url value='/${tenancyAlias}/backoffice/users/generateFakes' />">ajouter 20 users</a>
						</div>
						<!-- Dropdown pour trier -->
						<div class="d-flex align-items-center me-4">
							<label for="sortBy" class="me-2 fw-400 fs-3 text-nowrap fc-main">Trié par</label>
							<select id="sortBy" class="form-select custom-select border-main">
								<option value="name">Nom</option>
								<option value="role">Role</option>
							</select>
						</div>
						<!-- Barre de recherche -->
						<div>
							<input type="text" id="searchBar" class="form-control custom-input border-main"
								placeholder="Rechercher...">
						</div>
						<div class="mx-3">
							<label class="fc-main">Opacité du tableau</label>
    						<input type="range" class="form-range" min="0" max="100" value="100" id="bg-range">
						</div>
					</div>
					<div class="table-container d-flex justify-content-between align-items-center">
						<h2 class="fw-bold fc-main">Liste des adhérents</h2>
						<a href="<c:url value='/${tenancyAlias}/backoffice/users/form'/>" class="btn btn-outline-300 rounded-pill fch-main fw-bold border-2">
							<span class="icon">+ </span>Créer un adhérent
						</a>
					</div>
					<!-- Mode tableau -->
					<table class="table table-hover fc-main" style="--bs-table-bg: color-mix(in srgb, #ffffff, transparent 100%);">
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
	                                            <c:when test="${role.name.equals('ADMIN')}">
	                                            	<span>Admin</span>
	                                            </c:when>
	                                            <c:when test="${role.name.equals('MEMBER USER')}">
	                                            	<span>Adhérent</span>
	                                            </c:when>
	                                            <c:when test="${role.name.equals('SUPPLIER')}">
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
												class="btn rounded-circle border-2 border-300 fc-main px-1 py-0 mx-1"> <i class="bi bi-eye"></i>
											</a>
											<form:form action="delete/${user.userId}" class="d-inline" onsubmit="return confirm('Voulez-vous vraiment supprimer l'adhérent ${user.contactInfo.firstName} ${user.contactInfo.name} ?');">
												<button type="submit" class="btn rounded-circle border-2 border-300 fc-main px-1 py-0 mx-1">
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
		<script>
		var styleMapboxLight = "${mapStyleLight}"
		var styleMapboxDark = "${mapStyleDark}"

		/* 		REMPLACER par les coordinates -> à mettre en place dans la database du tenancy
		 const tenancyCity = "${tenancy.getAddress().getCity()}"
		 const tenancyPostCode = "${tenancy.getAddress().getPostCode()}" 
		 */
	</script>
	<script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/user-list.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/bg-table.js' />" type="text/javascript"></script>
</body>

</html>