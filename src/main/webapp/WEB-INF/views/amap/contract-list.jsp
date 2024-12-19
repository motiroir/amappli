<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liste des Contrats</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<style>
body {
	background-color: #f9f9f9;
	font-family: Arial, sans-serif;
}

.table-container {
	background-color: #fff;
	border-radius: 8px;
	padding: 20px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.table {
	border-collapse: collapse;
	width: 100%;
}

.table th {
	font-weight: bold;
	font-size: 16px;
	border-bottom: 2px solid #000;
	text-align: left;
	padding: 10px;
}

.table td {
	font-size: 16px;
	padding: 10px;
	border-bottom: 1px solid #e0e0e0;
}

.btn-modify {
	font-size: 16px;
	font-weight: bold;
	color: #FFA570;
	border: 2px solid #FFA570;
	border-radius: 5px;
	padding: 5px 10px;
	background: none;
	cursor: pointer;
}

.btn-modify:hover {
	background-color: #FFE4C2;
}

.btn-delete {
	width: 30px;
	height: 30px;
	display: flex;
	justify-content: center;
	align-items: center;
	border: 2px solid #FFA570;
	border-radius: 50%;
	font-size: 20px;
	color: #FFA570;
	background: none;
	cursor: pointer;
}

.btn-delete:hover {
	background-color: #FFE4C2;
}

@media ( max-width : 768px) {
	.table-container {
		overflow-x: auto;
	}
	.table th, .table td {
		white-space: nowrap;
	}
	.d-md-none {
		display: block !important;
	}
	.d-md-table-cell {
		display: none !important;
	}
}
</style>
</head>
<body>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-12">
				<div class="table-container">
					<h2 style="font-weight: bold;">Liste des contrats</h2>

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
												<button class="btn-modify me-2"
													onclick="window.location.href='<c:url value='/amap/contracts/edit/${contract.id}' />'">Modifier</button>
												<form method="POST"
													action="<c:url value='/amap/contracts/delete/${contract.id}' />"
													style="display: inline;">
													<button type="submit" class="btn-delete"
														onclick="return confirm('Voulez-vous vraiment supprimer le contrat ${contract.contractName} ?');">-</button>
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
										<button class="btn-modify">Modifier</button>
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
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
</body>
</html>



<!-- <html> -->
<!-- <head> -->
<!-- <title>Liste des Contrats</title> -->
<!-- </head> -->
<!-- <body> -->
<!-- 	<h2>Liste des Contrats</h2> -->
<!-- 	<table border="1"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<th>ID</th> -->
<!-- 				<th>Nom</th> -->
<!-- 				<th>Type de Contrat</th> -->
<!-- 				<th>Description</th> -->
<!-- 				<th>Poids</th> -->
<!-- 				<th>Prix</th> -->
<!-- 				<th>Date de Début</th> -->
<!-- 				<th>Date de Fin</th> -->
<!-- 				<th>Date de Création</th> -->
<!-- 				<th>Image URL</th> -->
<!-- 				<th>Action</th> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 		<tbody> -->
<%-- 			<c:forEach var="contract" items="${contracts}"> --%>
<!-- 				<tr> -->
<%-- 					<td>${contract.id}</td> --%>
<%-- 					<td>${contract.contractName}</td> --%>
<%-- 					<td>${contract.contractType.displayName}</td> --%>
<%-- 					<td>${contract.contractDescription}</td> --%>
<%-- 					<td>${contract.contractWeight.displayName}</td> --%>
<%-- 					<td>${contract.contractPrice}</td> --%>
<%-- 					<td>${contract.startDate}</td> --%>
<%-- 					<td>${contract.endDate}</td> --%>
<%-- 					<td>${contract.dateCreation}</td> --%>
<%-- 					<td>${contract.imageUrl}</td> --%>
<%-- 					<td><a href="/Amappli/amap/contracts/edit/${contract.id}"> --%>
<!-- 							<button type="button">Modifier</button> -->
<!-- 					</a> -->
<%-- 						<form action="/Amappli/amap/contracts/delete/${contract.id}" --%>
<!-- 							method="POST"> -->
<!-- 							<button type="submit" -->
<%-- 								onclick="return confirm('Voulez-vous vraiment supprimer le contrat ${contract.contractName} ?');"> --%>
<!-- 								Supprimer</button> -->
<!-- 						</form></td> -->
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
<!-- 		</tbody> -->
<!-- 	</table> -->
<!-- </body> -->
<!-- </html> -->
