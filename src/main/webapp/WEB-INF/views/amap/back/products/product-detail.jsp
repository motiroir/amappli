<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String currentMainMenu = "products"; // Détermine la rubrique active
String currentPage = "products"; // Détermine la sous-rubrique active
request.setAttribute("currentMainMenu", currentMainMenu);
request.setAttribute("currentPage", currentPage);
%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Détails du produit</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/common/utils.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/amap/image-format.css' />"
	rel="stylesheet">
<link
	href="<c:url value='/resources/bootstrap/bootstrap-icons.min.css' />"
	rel="stylesheet">
</head>
<body class="row ${cssStyle} light ${font}-title ${font}-button">
	<header class="fc-main bg-main border-1 border-alt">
		<!-- Inclusion du header -->
		<jsp:include page="../common/headerAdmin.jsp" />
	</header>
	<!-- Inclusion de la sidebar -->
	<jsp:include page="../common/sidebarAdmin.jsp" />

	<div id="map" class="p-0"></div>

	<!-- Contenu principal -->
	<div class="content col fc-main">
		<div class="container-fluid mt-2">
			<div class="row justify-content-center">
				<div class="form-container">
					<div class="header-container">
						<a
							href="<c:url value='/${tenancyAlias}/backoffice/products/list' />"
							class="${font} text-decoration-none rounded-pill btn btn-outline-300 border border-1 fw-bold fc-300 fch-900">
							<i class="bi bi-arrow-left-circle"></i><span
							class="d-none d-md-inline"> Liste des produits</span>
						</a>
						<div class="d-flex align-items-end gap-3">
						<h2 class="my-4 fw-bold mb-0">Détails du produit</h2>
						<label class="form-label mb-0">Créé le ${formattedDate}</label>
						</div>
						<br>
					</div>
					<form:form method="POST"
						action="${pageContext.request.contextPath}/${tenancyAlias}/backoffice/products/update"
						enctype="multipart/form-data" modelAttribute="product">
						<form:errors path="*" cssClass="errorBox" />
						<form:hidden path="id" value="${product.id }" />
						<div class="row">
							<!-- Première colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label class="form-label">Nom du produit</label>
									<form:input path="productName" class="form-control"
										value="${product.productName}" />
								</div>
								<div class="mb-3">
									<label class="form-label">Fournisseur</label> <select
										id="userId" name="userId" class="form-select">
										<option value=""
											<c:if test="${product.user == null}">selected</c:if>>Choisir
											un fournisseur</option>
										<c:forEach var="user" items="${users}">
											<option value="${user.userId}"
												<c:if test="${product.user != null && product.user.userId == user.userId}">selected</c:if>>
												${user.companyDetails.companyName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="mb-3">
									<label class="form-label">Description</label>
									<form:textarea path="productDescription" class="form-control"
										rows="3"></form:textarea>
								</div>
								<div class="mb-3">
									<label class="form-label">Prix de vente</label>
									<div class="input-group">
										<form:input path="productPrice" type="number" step="0.10"
											class="form-control" placeholder="Prix" />
										<span class="input-group-text">€</span>
									</div>
								</div>
							</div>

							<!-- Deuxième colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label class="form-label">Quantité en stock</label>
									<div class="input-group">
										<form:input path="productStock" type="number" step="1"
											class="form-control" placeholder="Quantité" />
										<span class="input-group-text">unités</span>
									</div>
								</div>
								<div class="mb-3">
									<label class="form-label">Date de fabrication</label>
									<form:input path="fabricationDate" type="date"
										class="form-control" />
								</div>
								<div class="mb-3">
									<label class="form-label">Date d'expiration</label> 
									<form:input
										path="expirationDate" type="date" class="form-control" />

								</div>
								<div class="mb-3">
									<label class="form-label">Fréquence de livraison</label>
									<form:select path="deliveryRecurrence"
										class="form-select form-control">
										<c:forEach var="recurrence" items="${deliveryRecurrence}">
											<form:option value="${recurrence}"
												label="${recurrence.displayName}" />
										</c:forEach>
									</form:select>
								</div>
								<div class="mb-3">
									<label class="form-label">Jour de livraison</label>
									<form:select path="deliveryDay"
										class="form-select form-control">
										<c:forEach var="day" items="${deliveryDay}">
											<form:option value="${day}" label="${day.displayName}" />
										</c:forEach>
									</form:select>
								</div>
							</div>

							<!-- Troisième colonne -->
							<div class="col-md-4">
								<div class="mb-3">
									<label for="image" class="form-label">Photo du produit</label> <input
										type="file" class="form-control" id="imageInput" name="image"
										accept="image/png,image/jpeg,image/svg">
								</div>
								<div class="mb-3 text-center">
									<c:if test="${not empty product.imageData}">
										<img id="imagePreview"
											src="data:${product.imageType};base64,${product.imageData}"
											alt="Aperçu du produit" class="image-preview">
									</c:if>
								</div>
								<div>
									Adresse du point de collecte :<br> ${address.line1}
									${address.line2}, ${address.city} (${address.postCode})
								</div>
							</div>
							<div class="d-flex justify-content-evenly my-5">
								<div class="col text-center">
									<button id="submit-button" type="submit"
										class="btn btn-success rounded-pill">Valider la
										modification</button>
								</div>
								<div class="col text-center">
									<button type="reset" class="btn btn-danger rounded-pill">Annuler</button>
								</div>
							</div>
						</div>
					</form:form>
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
	<script
		src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/image-format.js' />"
		type="text/javascript"></script>

	<script
		src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/sidebar.js' />"
		type="text/javascript"></script>
</body>
</html>
