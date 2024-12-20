<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String currentMainMenu = "products"; // Détermine la rubrique active
String currentPage = "contracts"; // Détermine la sous-rubrique active
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liste des Contrats</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/css/amap/common/sidebarAdmin.css' />"
	rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
	
<style>
</style>
</head>
<body>
	<!-- Inclusion de la sidebar -->
	<div>
		<%@ include file="/WEB-INF/views/amap/common/sidebarAdmin.jsp"%>
	</div>

	<!-- Contenu principal -->
	<div class="content" style="margin-left: 240px;">
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-12">
					<div
						class="table-container d-flex justify-content-between align-items-center">
						<h2 style="font-weight: bold;">Liste des contrats</h2>
						<a href="<c:url value='/amap/contracts/form' />"
							class="btn-create"><span class="icon">+</span>Créer un
							contrat</a>
					</div>
					<!-- Mode tableau pour desktop -->
					<div class="d-none d-md-block">
						<table class="table">
							<thead>
								<tr>
									<th>Nom</th>
									<th>Type</th>
									<th class="d-none d-lg-table-cell">Producteur</th>
									<th>Description</th>
									<th>Taille</th>
									<th class="d-none d-md-table-cell">Quantité</th>
									<th>Prix</th>
									<th>Date de début</th>
									<th>Date de fin</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="contract" items="${contracts}">
									<tr>
										<td>${contract.contractName}</td>
										<td>${contract.contractType.displayName}</td>
										<td class="d-none d-lg-table-cell">Producteur exemple</td>
										<td>${contract.contractDescription}</td>
										<td>${contract.contractWeight.displayName}</td>
										<td class="d-none d-md-table-cell">10</td>
										<td>${contract.contractPrice}€</td>
										<td>${contract.startDate}</td>
										<td>${contract.endDate}</td>
										<td>
											<div class='d-flex justify-content-start align-items-center'>
												<a href="<c:url value='/amap/contracts/detail/${contract.id}' />" class="btn-view">
													<i class="bi bi-eye"></i>
												</a>
												<form method="POST"
													action="<c:url value='/amap/contracts/delete/${contract.id}' />"
													style="display: inline;">
													<button type="submit" class="btn-delete"
														onclick="return confirm('Voulez-vous vraiment supprimer le contrat ${contract.contractName} ?');">
														<i class="bi bi-trash"></i>
													</button>
												</form>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- Mode liste pour mobile -->
					<div class="d-block d-md-none">
						<c:forEach var="contract" items="${contracts}">
							<div class="card mb-2">
								<div class="card-body">
									<h5>${contract.contractName}</h5>
									<p>
										<strong>Type :</strong> ${contract.contractType.displayName}
									</p>
									<p>
										<strong>Prix :</strong> ${contract.contractPrice}€
									</p>
									<p>
										<strong>Date de début :</strong> ${contract.startDate}
									</p>
									<div class="d-flex justify-content-between">
										<!--                                             <button class="btn-modify">Modifier</button> -->
										<form method="POST"
											action="<c:url value='/amap/contracts/delete/${contract.id}' />">
											<button type="submit" class="btn-delete">-</button>
										</form>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
</body>
</html>
