<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Liste des Contrats</title>
</head>
<body>
	<h2>Liste des Contrats</h2>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Type de Contrat</th>
				<th>Description</th>
				<th>Poids</th>
				<th>Prix</th>
				<th>Date de Début</th>
				<th>Date de Fin</th>
				<th>Date de Création</th>
				<th>Image URL</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="contract" items="${contracts}">
				<tr>
					<td>${contract.id}</td>
					<td>${contract.contractName}</td>
					<td>${contract.contractType.displayName}</td>
					<td>${contract.contractDescription}</td>
					<td>${contract.contractWeight.displayName}</td>
					<td>${contract.contractPrice}</td>
					<td>${contract.startDate}</td>
					<td>${contract.endDate}</td>
					<td>${contract.dateCreation}</td>
					<td>${contract.imageUrl}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>