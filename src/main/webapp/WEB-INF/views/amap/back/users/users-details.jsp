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
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Détails de adhérent</title>
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

<div id="map" class="p-0"></div>

	<!-- Contenu principal -->
	<div class="content col fc-main">
		<div class="container-fluid mt-2">
            <div class="row justify-content-center">
                    <div class="form-container">
                        <div class="header-container">
                            <a href="<c:url value='/${tenancyAlias}/backoffice/users/list'/>" class="${font} text-decoration-none rounded-pill btn btn-outline-300 border border-1 fw-bold fc-300 fch-900">
								<i class="bi bi-arrow-left"></i><span class="d-none d-md-inline"> Liste des adhérents</span>
							</a>
						
							<div>
								<h1>${message}</h1>
							</div>
							
							<h2 class="my-4 fw-bold">Détails de l'adhérent</h2>
                        </div>
                        <form:form  method="POST" action="/Amappli/${tenancyAlias}/backoffice/users/update" enctype="multipart/form-data" modelAttribute="user">
                        <form:errors path="*" cssClass="errorBox"/>
                        <form:hidden path="userId" value="${user.userId }"/>
                            <div class="row">
                                <!-- Première colonne -->
                                <div class="col-md-4">
                                    <div class="mb-3">
                                        <label class="form-label">Nom de famille</label>
                                        <form:input path="contactInfo.name" class="form-control" value="${user.contactInfo.name}"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Prénom</label>
                                        <form:input path="contactInfo.firstName" class="form-control" value="${user.contactInfo.firstName}"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">E-mail</label>
                                        <form:input path="email" class="form-control" value="${user.email}"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Envoyer un mail de réinitialisation du mot de passe</label>
                                        <a href="#" class="btn btn-secondary rounded-pill disabled">Envoyer un lien</a>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Balance crédit</label>
                                        <form:input path="creditBalance" type="number" class="form-control" value="${user.creditBalance}"/>
                                    </div>
                                    <%-- <div class="mb-3">
                                        <label class="form-label">Date de fin de cotisation</label>
                                        <form:input path="test" class="form-control" value="PAS ENCORE IMPLÉMENTÉ"/>
                                    </div> --%>
                                </div>

                                <!-- Deuxième colonne -->
                                <div class="col-md-4">
                                    <div class="mb-3">
                                        <label class="form-label">Adresse</label>
                                        <form:input path="address.line2" class="form-control" value="${user.address.line2}" />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Complément d'adresse</label>
                                        <form:input path="address.line1" class="form-control" value="${user.address.line1}"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Code Postal</label>
                                        <form:input path="address.postCode" class="form-control" value="${user.address.postCode}"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Ville</label>
                                        <form:input path="address.city" class="form-control" value="${user.address.city}"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Numéro de téléphone</label>
                                        <form:input path="contactInfo.phoneNumber" class="form-control" value="${user.contactInfo.phoneNumber}"/>
                                    </div>
                                </div>

                                <!-- Troisième colonne -->
                                <div class="col-md-4">
                                    <div class="mb-3 text-center">
                                        <div class="mb-3">
                                            <label class="form-label">Roles :</label><br/>
                                            <c:forEach var="role" items="${allRoles }" >
	                                            <c:choose>
													<c:when test="${role.name.equals('ADMIN')}">
														<label class="form-label" for="role-box-${role.name }">Admin</label>
													</c:when>
													<c:when test="${role.name.equals('MEMBER USER')}">
														<label class="form-label" for="role-box-${role.name }">Adhérent</label>
													</c:when>
													<c:when test="${role.name.equals('SUPPLIER')}">
														<label class="form-label" for="role-box-${role.name }">Producteur</label>
													</c:when>
													<c:otherwise>
														<label class="form-label" for="role-box-${role.name }">${role.name.toLowerCase()}</label>
													</c:otherwise>
												</c:choose>
                                                <form:checkbox id="role-box-${role.name }" class="me-3" path="roles" value="${role}" />
                                            </c:forEach>
                                        </div>
                                        <section id="supplier-section" class="d-none">
                                            <div class="mb-3">
                                                <label class="form-label">Exploitation</label>
                                                <form:input path="companyDetails.companyName" class="form-control" value="${user.companyDetails.companyName}"/>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">N° Siret</label>
                                                <form:input path="companyDetails.siretNumber" class="form-control" value="${user.companyDetails.siretNumber}"/>
                                            </div>
                                            <%-- <div class="mb-3">
                                                <label class="form-label">Contrat en cours</label>
                                                <form:input path="test2" class="form-control" value="PAS ENCORE IMPLÉMENTÉ" readonly="true"/>
                                            </div> --%>
                                        </section>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex justify-content-evenly my-5">
								<div class="col text-center">
									<button id="submit-button" type="submit" class="btn btn-success rounded-pill" 
									<c:if test="${isAdmin }">onclick="return confirm('Vous êtes sur le point d'enregistrer un utilisateur avec les droits d'administrateur, êtes vous sûr ?');"</c:if> >Valider la modification</button>
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
    	<script>
		var styleMapboxLight = "${mapStyleLight}"
		var styleMapboxDark = "${mapStyleDark}"

		/* 		REMPLACER par les coordinates -> à mettre en place dans la database du tenancy
		 const tenancyCity = "${tenancy.getAddress().getCity()}"
		 const tenancyPostCode = "${tenancy.getAddress().getPostCode()}" 
		 */
	</script>
	<script	src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/sidebar.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/user-details.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/user-form.js' />" type="text/javascript"></script>
</body>
</html>
