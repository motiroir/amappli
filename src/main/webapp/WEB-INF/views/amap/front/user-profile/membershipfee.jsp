<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://api.mapbox.com/mapbox-gl-js/v2.15.0/mapbox-gl.css" rel="stylesheet" />
<link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/resources/css/amap/user-profile/-.css' />">

<title>Mon adhésion</title>

<style>
    body {
        margin: 0;
    }

    .centered-zone {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 100%;
        
    }
</style>
</head>

<body class="row ${cssStyle} light ${font}-title ${font}-button">

	<header class="fc-main bg-main">
		<jsp:include page="common/header-user-account.jsp" />
	</header>
	<jsp:include page="common/sidebar-user-account.jsp" />

	<div id="map"></div>

	<div class="centered-zone">
		<div class="container-zone col-12 col-md-8 col-lg-6 p-4 border rounded shadow">
			<div class="text-container mb-4">
				<p><strong>Statut de l'adhésion :</strong>
					<span th:text="${membershipDTO.status}" 
						th:classappend="${membershipDTO.status == 'Active' ? 'text-success' : 'text-danger'}"></span>
				</p>
				<p><strong>Date d'adhésion :</strong> <span th:text="${membershipDTO.startDate}"></span></p>
				<p><strong>Date d'expiration :</strong> <span th:text="${membershipDTO.endDate}"></span></p>
				<p><strong>Montant :</strong> <span th:text="${membershipDTO.amount} + '€'"></span></p>
			</div>

			<div class="buttons d-flex justify-content-between">
				<button type="button" class="btn btn-primary" onclick="renewMembership()">Renouveler mon adhésion</button>
				<button type="button" class="btn btn-danger" onclick="cancelMembership()">Annuler mon adhésion</button>
			</div>
		</div>
	</div>

	<script>
		function renewMembership() {
			// Logique de renouvellement (appel API ou redirection)
			alert('Renouvellement non encore implémenté.');
		}

		function cancelMembership() {
			// Logique d'annulation (appel API ou redirection)
			alert('Annulation non encore implémentée.');
		}
	</script>

	<script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
	<script>
		var styleMapboxLight = "${mapStyleLight}"
		var styleMapboxDark = "${mapStyleDark}"
		var latitude = "${latitude}"
		var longitude = "${longitude}"
	</script>
	<script src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>
	<script src="<c:url value='/resources/js/common/theme-swap.js' />"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/amap/admin/bg-table.js' />" type="text/javascript"></script>

</body>
</html>
