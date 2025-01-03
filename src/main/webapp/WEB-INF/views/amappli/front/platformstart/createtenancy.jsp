<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création de votre espace AMAP</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/common/theme/theme-1-light.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/amappli/tenancycreation.css'/>" rel="stylesheet">
<!-- Mapbox CSS -->
<link href="https://api.mapbox.com/mapbox-gl-js/v2.15.0/mapbox-gl.css" rel="stylesheet" />
</head>
<body class="d-flex justify-content-center theme-1 light">
    <!-- Conteneur pour la carte -->
    <div id="map"></div>

    <div class="container w-75 p-5 h-75">

        <div class="title">
            <h1 class="display-4">Faisons connaissance</h1>
            <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
                <div class="progress-bar" style="width: 0%" id="the-progress-bar"></div>
            </div>
        </div>
        <hr>

        <div class="form-part">
            <p>Pour vous aider à créer le site de votre AMAP, nous allons vous poser une série de questions pour mieux vous connaître. Cela devrait prendre moins de 5 minutes. Veuillez préparer les informations concernant votre AMAP.</p>
            <h1 class="display-6">Etes-vous prêts ?</h1>
        </div>

        <form:form action="${pageContext.request.contextPath}/start/creation" method="post" modelAttribute="newTenancyDTO" enctype="multipart/form-data">
            
            <div class="form-part">

                <div class="mb-3">
                    <form:label for="input-tenancy-name" class="form-label display-6" path="tenancyName">Quel est le nom de votre AMAP ?</form:label>
                        <form:input type="text" class="form-control" id="input-tenancy-name" aria-describedby="input-tenancy-name" path="tenancyName" required="true" aria-required="true"/>
                    <form:errors path="tenancyName" class="invalid-feedback d-block" />
                </div>
            </div>

            <div class="form-part">

                <div class="mb-3">
                    <form:label for="input-tenancy-alias" class="form-label display-6" path="tenancyAlias">Quelle URL souhaitez-vous pour votre AMAP ?</form:label>
                        <form:input type="text" class="form-control" id="input-tenancy-alias" aria-describedby="input-tenancy-alias" path="tenancyAlias" required="true" aria-required="true"/>
                    <form:errors path="tenancyAlias" class="invalid-feedback d-block" />
                    <c:if test="${not empty aliasError}">
                            <div class="invalid-feedback d-block">${aliasError}</div>
                    </c:if>
                </div>
                <div class="mb-3">
                    Votre AMAP sera accessible à l'adresse "www.amappli.fr/<span id="amap-url-example">...</span>
                </div>
            </div>

            <div class="form-part">

                <div class="mb-3">
                    <form:label for="input-tenancy-slogan" class="form-label display-6" path="tenancyName">Quel est le slogan de votre AMAP ?</form:label>
                    <form:input type="text" class="form-control" id="input-tenancy-slogan" aria-describedby="input-tenancy-slogan" path="tenancySlogan" required="true" aria-required="true"/>
                    <form:errors path="tenancySlogan" class="invalid-feedback d-block" />
                
                </div>
            </div>

            <div class="form-part">

                <div class="mb-3">
                    <form:label for="input-membership-price" class="form-label display-6" path="membershipFeePrice">Combien coûte la cotisation annuelle dans votre AMAP ?</form:label>
                    <form:input type="text" class="form-control" id="input-membership-price" aria-describedby="input-tenancy-name" path="membershipFeePrice" required="true" aria-required="true"/>
                    <form:errors path="membershipFeePrice" class="invalid-feedback d-block" />
                </div>
            </div>

            <div class="form-part">
                <h1 class="display-6">Quel est le créneau de collecte de votre AMAP?</h1>
                <div class="mb-3">
                    <label for="dayOfWeek">Jour de la Semaine:</label>
                    <form:select path="pickUpSchedule.dayOfWeek" id="dayOfWeek">
                        <form:option value="">Sélectionnez un Jour</form:option>
                        <form:option value="MONDAY">Lundi</form:option>
                        <form:option value="TUESDAY">Mardi</form:option>
                        <form:option value="WEDNESDAY">Mercredi</form:option>
                        <form:option value="THURSDAY">Jeudi</form:option>
                        <form:option value="FRIDAY">Vendredi</form:option>
                        <form:option value="SATURDAY">Samedi</form:option>
                        <form:option value="SUNDAY">Dimanche</form:option>
                    </form:select>
                    <form:errors path="pickUpSchedule.dayOfWeek" class="invalid-feedback d-block"/>

                    <label for="startHour">Heure de Début:</label>
                    <form:input path="pickUpSchedule.startHour" type="time" id="startHour" required="true" />
                    <form:errors path="pickUpSchedule.startHour" class="invalid-feedback d-block"/>

                    <label for="endHour">Heure de Fin:</label>
                    <form:input path="pickUpSchedule.endHour" type="time" id="endHour" required="true" />
                    <form:errors path="pickUpSchedule.endHour" class="invalid-feedback d-block"/>
                </div>
            </div>

            <div class="form-part">
                <h1 class="display-6">Où se situe votre AMAP?</h1>
                <div class="mb-3">
                    <form:label for="address-line1" class="form-label" path="address.line1">Ligne 1</form:label>
                    <form:input type="text" class="form-control" id="address-line1" path="address.line1"/>
                    <form:errors path="address.line1" class="invalid-feedback d-block" />
                </div>

                <div class="mb-3">
                    <form:label for="address-line2" class="form-label" path="address.line2">Ligne 2</form:label>
                    <form:input type="text" class="form-control" id="address-line2" path="address.line2" required="true" aria-required="true"/>
                    <form:errors path="address.line2" class="invalid-feedback d-block" />
                </div>

                <div class="mb-3">
                    <form:label for="address-postcode" class="form-label" path="address.postCode">Code Postal</form:label>
                    <form:input type="text" class="form-control" id="address-postcode" path="address.postCode" required="true" aria-required="true"/>
                    <form:errors path="address.postCode" class="invalid-feedback d-block" />
                </div>

                <div class="mb-3">
                    <form:label for="address-city" class="form-label" path="address.city">Ville</form:label>
                    <form:input type="text" class="form-control" id="address-city" path="address.city" required="true" aria-required="true"/>
                    <form:errors path="address.city" class="invalid-feedback d-block" />
                </div>
            </div>

            <div class="form-part">
                <h1 class="display-6">Avez-vous un logo ?</h1>
                <form:input type="file" class="form-control" id="input-logo" aria-describedby="input-logo" path="logo" accept="image/png,image/jpeg,image/svg"/>
            </div>

            <div class="form-part">
                <h1 class="display-6">Quelles sont vos valeurs ?</h1>
                <p>Elles apparaîtront sur votre page d'accueil.</p>
                <div id="value-form" class="d-flex flex-row justify-content-center">
                <c:forEach items="${newTenancyDTO.values}" var="valueDTO" varStatus="status">
                    <div class="mb-3 d-flex flex-column">
                        <form:input class="form-control" id="input-value-name-${status.index}" aria-describedby="input-value-name" path="values[${status.index}].name" />
                        <form:textarea class="form-control" id="input-value-description-${status.index}" aria-describedby="input-value-description" path="values[${status.index}].description" />
                        <form:input type="file" class="form-control" id="input-value-description-${status.index}" aria-describedby="input-value-file" path="values[${status.index}].file" accept="image/png,image/jpeg,image/svg"/>
                    </div>
                </c:forEach>
                </div>               
            </div>

            <div class="form-part">
                <h1 class="display-6">Présentez-vous à vos adhérents!</h1>
                <p>Vous pourrez ajouter plus de contenu sur votre page d'accueil par la suite.</p>
                <form:input class="form-control" id="input-hp-title" aria-describedby="input-hp-title" path="firstHomePageTitle" />
                <form:textarea class="form-control" id="input-hp-text" aria-describedby="input-hp-text" path="firstHomePageText" />
                <form:input type="file" class="form-control" id="input-hp-image" aria-describedby="input-hp-image" path="firstHomePagePic" accept="image/png,image/jpeg,image/svg"/>
            </div>

            <div class="form-part">
                <h1 class="display-6">Choississez une police pour vos titres et boutons</h1>
                <div id="font-choices" class="d-flex flex-row justify-content-around">
                    <c:forEach items="${fontChoices}" var="font">
                        <input type="radio" id="font-${font}" value="${font}" name="font-selection"/>
                        <label for="font-${font}" class="enum-choices">
                            <h2 class="${fn:toLowerCase(font)}">${font}</h2>
                        </label>
                    </c:forEach>
                </div>
                
            </div>

            <div class="form-part">
                <h1 class="display-6">Choississez une palette de couleurs.</h1>
                <div id="palette-choices" class="container w-100 d-flex flex-row justify-content-around">
                    <c:forEach items="${colorPalettes}" var="palette" varStatus="status">
                        <input type="radio" id="palette-${status.index + 1}" name="palette-selection" value="${palette}" />
                        <label for="palette-${status.index+1}" class="enum-choices">
                            <img src="<c:url value='/resources/img/palettes_samples/PALETTE${status.index + 1}.svg' />"/>
                        </label>
                    </c:forEach>
                </div>
                
            </div>

            <div class="form-part">
                <h1 class="display-6">Des paniers oui, mais quoi d'autre ?</h1>

                <ul>
                    <li class="d-flex flex-row">
                        <span class="float-start">Est-ce que vous vendez des produits à l'unité en plus de vos paniers ?</span>
                        <input type="radio" value="true" name="question-1" class="btn-check float-end" id="question-1-true" checked/>
                        <label class="btn btn-outline-700 float-end" for="question-1-true">Oui</label>
                        <input type="radio" value="false" name="question-1" class="btn-check float-end" id="question-1-false"/>
                        <label class="btn btn-outline-700 float-end" for="question-1-false">Non</label>
                    </li>
                    <li>
                        <span>Est-ce que vous souhaitez proposer des lots et des promos à vos adhérents ?</span>
                        <input type="radio" value="true" name="question-2" class="btn-check" id="question-2-true" checked/>
                        <label class="btn btn-outline-700" for="question-2-true">Oui</label>
                        <input type="radio" value="false" name="question-2" class="btn-check" id="question-2-false"/>
                        <label class="btn btn-outline-700" for="question-2-false">Non</label> 
                    </li>
                    <li>
                        <span>Organisez-vous des ateliers que vous souhaiteriez mettre à disposition de vos adhérents sur ce site ?</span>
                        <input type="radio" value="true" name="question-3" class="btn-check" id="question-3-true" checked/>
                        <label class="btn btn-outline-700" for="question-3-true">Oui</label>
                        <input type="radio" value="false" name="question-3" class="btn-check" id="question-3-false"/>
                        <label class="btn btn-outline-700" for="question-3-false">Non</label> 
                    </li>
                </ul>
            </div>

            <div class="form-part">
                <h1 class="display-6">Des paniers oui, mais quoi d'autre ?</h1>

                <ul>
                    <li class="d-flex flex-row">
                        <span class="float-start">Voulez-vous activer le paiement en ligne ?</span>
                        <input type="radio" value="true" name="question-4" class="btn-check" id="question-4-true" checked/>
                        <label class="btn btn-outline-700 float-end" for="question-4-true">Oui</label>
                        <input type="radio" value="false" name="question-4" class="btn-check" id="question-4-false"/>
                        <label class="btn btn-outline-700 float-end" for="question-4-false">Non</label>
                    </li>
                </ul>
            </div>

            <div class="form-part">
                <h1 class="display-6">Des paniers oui, mais quoi d'autre ?</h1>

                <ul>
                    <li class="d-flex flex-row">
                        <span class="float-start">Souhaitez-vous que vos producteurs puissent ajouter eux-même leurs produits sur le site ?</span>
                        <input type="radio" value="true" name="question-5" class="btn-check float-end" id="question-5-true" checked/>
                        <label class="btn btn-outline-700 float-end" for="question-5-true">Oui</label>
                        <input type="radio" value="false" name="question-5" class="btn-check float-end" id="question-5-false"/>
                        <label class="btn btn-outline-700 float-end" for="question-5-false">Non</label>
                    </li>
                    <li>
                        <span>Est-ce que vous souhaitez donner des accès personnalisés à vos bénévoles ? Par exemple leur permettre d’accéder à la liste des commandes de vos adhérents mais sans leur donner accès à leurs informations personnelles.</span>
                        <input type="radio" value="true" name="question-6" class="btn-check" id="question-6-true" checked/>
                        <label class="btn btn-outline-700" for="question-6-true">Oui</label>
                        <input type="radio" value="false" name="question-6" class="btn-check" id="question-6-false"/>
                        <label class="btn btn-outline-700" for="question-6-false">Non</label> 
                    </li>
                </ul>
            </div>

            <div class="form-part">
                <h1 class="display-6">Des paniers oui, mais quoi d'autre ?</h1>

                <ul>
                    <li>
                        <span>Souhaitez-vous avoir la possibilité d’afficher des statistiques à partir de vos données ?</span>
                        <input type="radio" value="true" name="question-7" class="btn-check" id="question-7-true" checked/>
                        <label class="btn btn-outline-700" for="question-7-true">Oui</label>
                        <input type="radio" value="false" name="question-7" class="btn-check" id="question-7-false"/>
                        <label class="btn btn-outline-700" for="question-7-false">Non</label> 
                    </li>
                </ul>
            </div>

            <div class="form-part">
                <h1 class="display-6">Des paniers oui, mais quoi d'autre ?</h1>

                <ul>
                    <li class="d-flex align-items-center justify-content-between">
                        <span>Souhaitez-vous améliorer le quotidien de vos adhérents en leur proposant des options de favoris, et la possibilité de choisir entre un thème dark et un thème light ?</span>
                        <div class="d-flex">
                            <input type="radio" value="true" name="question-8" id="question-8-true" checked/>
                            <label class="btn btn-700" for="question-8-true">Oui</label>
                            <input type="radio" value="false" name="question-8" id="question-8-false"/>
                            <label class="btn btn-700" for="question-8-false">Non</label> 
                        </div>
                    </li>
                </ul>
            </div>

            <div class="form-part">
                <h1 class="display-6">Choississez maintenant votre abonnement à Amappli:</h1>

                <ul>
                    <li><form:radiobutton path="option1" value="true" label="Petit potager" id="option-1" name="option-selection" /></li>
                    <li><form:radiobutton path="option2" value="true" label="Verger" id="option-2" name="option-selection" /></li>
                    <li><form:radiobutton path="option3" value="true" label="Ferme" id="option-3" name="option-selection" /></li>
                </ul>
                <button type="submit" class="btn btn-400">Valider</button>
            </div>

            <div id="nav-button" class="container w-75 d-flex justify-content-between">
                <button type="button" class="rounded-pill btn-green" id="go-back-button">
                    Retour
                </button>
                <button type="button" class="rounded-pill btn-orange" id="continue-button">
                    Continuer
                </button>
            </div>
        </form:form>

    </div>
    <script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js'/>"></script>
    <script src="<c:url value='/resources/js/amappli/tenancycreation.js'/>"></script>
    <!-- <script	src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script	src="<c:url value='/resources/js/common/mapbox/map.js' />"></script> -->
</body>
</html>