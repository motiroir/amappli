<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier votre page d'accueil</title>


    <!-- Mapbox CSS -->
    <link href="https://api.mapbox.com/mapbox-gl-js/v2.15.0/mapbox-gl.css" rel="stylesheet" />
    <link href="<c:url value='/resources/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/bootstrap/bootstrap-icons.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/css/amappli/tenancycreation.css'/>" rel="stylesheet">

</head>

<body class="${cssStyle} light ${font}-title ${font}-button">
    <header class="fc-main bg-main">
        <jsp:include page="../../../amap/back/common/headerAdmin.jsp" />
    </header>

    <!-- <div id="map"></div> -->

    <div class="fc-main content col d-flex">
        <jsp:include page="../../../amap/back/common/sidebarAdmin.jsp" />

        <div class="content col container-fluid row mt-2">
            <!-- Main Title -->
            <h1 class="text-center mb-4">Modifier votre AMAP</h1>

             <!-- Accordion for Collapsible Sections -->
            <div class="accordion" id="accordionExample">
                <!-- Nom ou adresse internet -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingOne">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                            <i class="bi bi-chevron-right me-2"></i> Nom ou adresse internet
                        </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <form action="${pageContext.request.contextPath}/amap/${tenancyAlias}/admin/editthenameandalias" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <div class="mb-3">
                                    <label class="form-label" for="input-tenancyName">Nom de l'AMAP</label>
                                    <input class="form-control" type="text" class="form-control" id="input-tenancyName" name="tenancyName"
                                        value="${tenancyUpdateNameAliasDTO.tenancyName}" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="input-tenancyAlias">Adresse internet de l'AMAP</label>
                                    <input class="form-control" type="text" class="form-control" id="input-tenancyAlias" name="tenancyAlias"
                                        value="${tenancyUpdateNameAliasDTO.tenancyAlias}" required>
                                </div>
                                <div class="mb-3">
                                    Votre AMAP sera accessible à l'adresse <i>www.amappli.fr/<span id="amap-url-example">...</span></i>
                                </div>
                                <button type="submit" class="btn btn-700">Sauvegarder</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Slogan -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingTwo">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            <i class="bi bi-chevron-right me-2"></i> Slogan
                        </button>
                    </h2>
                    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <form action="${pageContext.request.contextPath}/amap/${tenancyAlias}/admin/edittheslogan" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <div class="mb-3">
                                    <label class="form-label" for="input-tenancySlogan">Slogan de l'AMAP</label>
                                    <input class="form-control" type="text" class="form-control" id="input-tenancySlogan" name="slogan"
                                        value="${tenancyUpdateSloganDTO.slogan}" required>
                                </div>
                                <button type="submit" class="btn btn-700">Sauvegarder</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Logo -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingThree">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            <i class="bi bi-chevron-right me-2"></i> Logo
                        </button>
                    </h2>
                    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <form action="${pageContext.request.contextPath}/amap/${tenancyAlias}/admin/editthelogo" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <div class="mb-3">
                                    <img id="preview-logo" class="w-30" src="data:${tenancyUpdateLogo.logoImgType};base64,${tenancyUpdateLogo.logoImg}" alt="logo"/>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="input-tenancyLogo">Logo de l'AMAP</label>
                                    <input class="form-control" type="file" class="form-control" id="input-tenancyLogo" name="file"
                                        required accept="image/png,image/jpeg,image/svg">
                                </div>
                                <button type="button" id="cancel-preview-logo" class="btn btn-900" style="display: none;">Annuler</button>
                                <button type="submit" class="btn btn-700">Sauvegarder</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Adresse -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingFour">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                            <i class="bi bi-chevron-right me-2"></i> Adresse
                        </button>
                    </h2>
                    <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <form action="${pageContext.request.contextPath}/amap/${tenancyAlias}/admin/edittheaddress" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <p>Cela changera les courbes de niveau en arrière-plan de votre site.</p>
                                <div class="mb-3">
                                    <label class="form-label" for="input-tenancyAddressLine1">Complément d'adresse</label>
                                    <input class="form-control" type="text" class="form-control" id="input-tenancyAddressLine1" name="address.line1"
                                        value="${tenancyUpdateAddressDTO.address.line1}">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="input-tenancyAddressLine2">Numéro et Rue</label>
                                    <input class="form-control" type="text" class="form-control" id="input-tenancyAddressLine2" name="address.line2"
                                        value="${tenancyUpdateAddressDTO.address.line2}" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="input-tenancyAddressPostCode">Code Postal</label>
                                    <input class="form-control" type="text" class="form-control" id="input-tenancyAddressPostCode" name="address.postCode"
                                        value="${tenancyUpdateAddressDTO.address.postCode}" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="input-tenancyAddressCity">Ville</label>
                                    <input class="form-control" type="text" class="form-control" id="input-tenancyAddressCity" name="address.city"
                                        value="${tenancyUpdateAddressDTO.address.city}" required>
                                </div>
                                <button type="submit" class="btn btn-700">Sauvegarder</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Modifier votre créneau d'ouverture -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingFive">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                            <i class="bi bi-chevron-right me-2"></i> Modifier votre créneau d'ouverture
                        </button>
                    </h2>
                    <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <form action="${pageContext.request.contextPath}/amap/${tenancyAlias}/admin/editthepickupschedule" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <p>Vos utilisateurs seront avertis à partir de leurs prochaines commandes.</p>
                                <div class="mb-3">
                                    <label for="dayOfWeek" class="form-label">Jour de la Semaine</label>
                                    <select class="from-control form-select" name="pickUpSchedule.dayOfWeek" id="dayOfWeek">
                                        <option value="">Sélectionnez un Jour</option>
                                            <c:choose>
                                        <c:when test="${tenancyUpdatePickUpDTO.dayOfWeek.name() == 'MONDAY'}">
                                            <option value="MONDAY" selected>Lundi</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="MONDAY">Lundi</option>
                                        </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${tenancyUpdatePickUpDTO.dayOfWeek.name() == 'TUESDAY'}">
                                                <option value="TUESDAY" selected>Mardi</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="TUESDAY">Mardi</option>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${tenancyUpdatePickUpDTO.dayOfWeek.name() == 'WEDNESDAY'}">
                                                <option value="WEDNESDAY" selected>Mercredi</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="WEDNESDAY">Mercredi</option>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${tenancyUpdatePickUpDTO.dayOfWeek.name() == 'THURSDAY'}">
                                                <option value="THURSDAY" selected>Jeudi</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="THURSDAY">Jeudi</option>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${tenancyUpdatePickUpDTO.dayOfWeek.name() == 'FRIDAY'}">
                                                <option value="FRIDAY" selected>Vendredi</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="FRIDAY">Vendredi</option>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${tenancyUpdatePickUpDTO.dayOfWeek.name() == 'SATURDAY'}">
                                                <option value="SATURDAY" selected>Samedi</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="SATURDAY">Samedi</option>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${tenancyUpdatePickUpDTO.dayOfWeek.name() == 'SUNDAY'}">
                                                <option value="SUNDAY" selected>Dimanche</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="SUNDAY">Dimanche</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </div>
                            
                                <div class="mb-3">
                                    <label for="startHour" class="form-label">Heure de Début</label>
                                    <input value="${tenancyUpdatePickUpDTO.startHour}" name="pickUpSchedule.startHour" type="time" class="form-control" id="startHour" required="true" />
                                </div>
                                <div class="mb-3">
                                    <label for="endHour" class="form-label">Heure de Fin</label>
                                    <input value="${tenancyUpdatePickUpDTO.endHour}" name="pickUpSchedule.endHour" type="time" class="form-control" id="endHour" required="true" />
                                </div>
                                <button type="submit" class="btn btn-700">Sauvegarder</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Palette de couleurs et police -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingSix">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                            <i class="bi bi-chevron-right me-2"></i> Palette de couleurs et police
                        </button>
                    </h2>
                    <div id="collapseSix" class="accordion-collapse collapse" aria-labelledby="headingSix" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            Place your content for "Palette de couleurs et police" here.
                        </div>
                    </div>
                </div>

                <!-- Contenu de la page d'accueil -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingSeven">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                            <i class="bi bi-chevron-right me-2"></i> Contenu de la page d'accueil
                        </button>
                    </h2>
                    <div id="collapseSeven" class="accordion-collapse collapse" aria-labelledby="headingSeven" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            Place your content for "Contenu de la page d'accueil" here.
                        </div>
                    </div>
                </div>

                <!-- Valeurs -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingEight">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
                            <i class="bi bi-chevron-right me-2"></i> Valeurs
                        </button>
                    </h2>
                    <div id="collapseEight" class="accordion-collapse collapse" aria-labelledby="headingEight" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            Place your content for "Valeurs" here.
                        </div>
                    </div>
                </div>

                <!-- Abonnement -->
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingNine">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseNine" aria-expanded="false" aria-controls="collapseNine">
                            <i class="bi bi-chevron-right me-2"></i> Abonnement
                        </button>
                    </h2>
                    <div id="collapseNine" class="accordion-collapse collapse" aria-labelledby="headingNine" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            Place your content for "Abonnement" here.
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>
    <script>
        var styleMapboxLight = "${mapStyleLight}"
        var styleMapboxDark = "${mapStyleDark}"
        var latitude = "${latitude}"
        var longitude = "${longitude}"
    </script>
    <!-- 
    <script src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
    <script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script>  -->
    <script src="<c:url value='/resources/js/common/theme-swap.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/js/amap/admin/bg-table.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/js/amap/admin/sidebar.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/js/amap/admin/homepageedit.js' />" type="text/javascript"></script>
</body>
</html>