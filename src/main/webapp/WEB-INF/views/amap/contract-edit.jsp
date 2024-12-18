<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Modifier un Contrat</title>
</head>
<body>
	<h2>Modifier un Contrat</h2>
	<form:form name="contractForm" method="POST"
		action="/Amappli/amap/contracts/update" modelAttribute="contract"
		onsubmit="return confirmUpdate();">
		<form:hidden path="id" />
        Nom du contrat : <form:input path="contractName" />
		<br>
        Type de Contrat :
        <form:select path="contractType">
			<c:forEach var="type" items="${contractTypes}">
				<form:option value="${type}" label="${type.displayName}" />
			</c:forEach>
		</form:select>
		<br>
        Description : <form:textarea path="contractDescription" />
		<br>
        Poids :
        <form:select path="contractWeight">
			<c:forEach var="weight" items="${contractWeights}">
				<form:option value="${weight}" label="${weight.displayName}" />
			</c:forEach>
		</form:select>
		<br>
        Prix : <form:input path="contractPrice" type="number"
			step="0.01" />
		<br>
        Date de début : <form:input path="startDate" type="date" />
		<br>
        Date de fin : <form:input path="endDate" type="date" />
		<br>
        Image URL : <form:input path="imageUrl" />
		<br>
		<br>
		<button type="submit">Valider les modifications</button>
		<a href="/Amappli/amap/contracts/list">
			<button type="button">Annuler</button>
		</a>
	</form:form>
	<script src="<c:url value='/resources/js/contract-edit.js' />"></script>
</body>
</html>
