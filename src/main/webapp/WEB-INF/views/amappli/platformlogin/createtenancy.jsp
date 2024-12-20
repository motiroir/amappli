<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création de votre espace AMAP</title>
<link href="<c:url value='/resources/bootstrap/bootstrap.min.css'/>" rel="stylesheet">
<style>
    body {
        background-image: url("<c:url value='/resources/img/peach_lines.svg'/>");
    }
</style>
</head>
<body class="d-flex justify-content-center">
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
                    <form:label for="input-tenancy-slogan" class="form-label display-6" path="tenancyName">Quel est le nom de votre AMAP ?</form:label>
                    <form:input type="text" class="form-control" id="input-tenancy-slogan" aria-describedby="input-tenancy-slogan" path="tenancySlogan" required="true" aria-required="true"/>
                    <form:errors path="tenancySlogan" class="invalid-feedback d-block" />
                </div>
            </div>

            <div class="form-part">

                <div class="mb-3">
                    <form:label for="input-tenancy-name" class="form-label display-6" path="tenancyName">Quel est le slogan de votre AMAP ?</form:label>
                    <form:input type="text" class="form-control" id="input-tenancy-name" aria-describedby="input-tenancy-name" path="tenancyName" required="true" aria-required="true"/>
                    <form:errors path="tenancyName" class="invalid-feedback d-block" />
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
                <div id="font-choices" class="d-flex flex-row justify-content-center">
                    <c:forEach items="${fontChoices}" var="font">
                        <img src="<c:url value='/resources/img/logo_amappli_peach.png' />" with="100" height="100"/>
                        <form:radiobutton path="fontChoice" value="${font}" label="${font}" name="font-selection" />
                    </c:forEach>
                </div>
                
            </div>

            <div class="form-part">
                <div id="palette-choices" class="d-flex flex-row justify-content-center">
                    <c:forEach items="${colorPalettes}" var="palette">
                        <img src="<c:url value='/resources/img/logo_amappli_peach.png' />" with="100" height="100"/>
                        <form:radiobutton path="colorPalette" value="${palette}" label="${palette}" name="palette-selection" />
                    </c:forEach>
                </div>
                
            </div>

            <div class="form-part">
                <h1 class="display-6">Des paniers oui, mais quoi d'autre ?</h1>

                <ul>
                    <li class="d-flex flex-row">
                        <span class="float-start">Est-ce que vous vendez des produits à l'unité en plus de vos paniers ?</span>
                        <input type="radio" value="true" name="question-1" class="btn-check float-end" id="question-1-true" checked/>
                        <label class="btn btn-outline-primary float-end" for="question-1-true">Oui</label>
                        <input type="radio" value="false" name="question-1" class="btn-check float-end" id="question-1-false"/>
                        <label class="btn btn-outline-primary float-end" for="question-1-false">Non</label>
                    </li>
                    <li>
                        <span>Est-ce que vous souhaitez proposer des lots et des promos à vos adhérents ?</span>
                        <input type="radio" value="true" name="question-2" class="btn-check" id="question-2-true" checked/>
                        <label class="btn btn-outline-primary" for="question-2-true">Oui</label>
                        <input type="radio" value="false" name="question-2" class="btn-check" id="question-2-false"/>
                        <label class="btn btn-outline-primary" for="question-2-false">Non</label> 
                    </li>
                    <li>
                        <span>Organisez-vous des ateliers que vous souhaiteriez mettre à disposition de vos adhérents sur ce site ?</span>
                        <input type="radio" value="true" name="question-3" class="btn-check" id="question-3-true" checked/>
                        <label class="btn btn-outline-primary" for="question-3-true">Oui</label>
                        <input type="radio" value="false" name="question-3" class="btn-check" id="question-3-false"/>
                        <label class="btn btn-outline-primary" for="question-3-false">Non</label> 
                    </li>
                </ul>
            </div>

            <div class="form-part">
                <h1 class="display-6">Des paniers oui, mais quoi d'autre ?</h1>

                <ul>
                    <li class="d-flex flex-row">
                        <span class="float-start">Voulez-vous activer le paiement en ligne ?</span>
                        <input type="radio" value="true" name="question-4" class="btn-check" id="question-4-true" checked/>
                        <label class="btn btn-outline-primary float-end" for="question-4-true">Oui</label>
                        <input type="radio" value="false" name="question-4" class="btn-check" id="question-1-false"/>
                        <label class="btn btn-outline-primary float-end" for="question-4-false">Non</label>
                    </li>
                </ul>
            </div>

            <div class="form-part">
                <h1 class="display-6">Des paniers oui, mais quoi d'autre ?</h1>

                <ul>
                    <li class="d-flex flex-row">
                        <span class="float-start">Souhaitez-vous que vos producteurs puissent ajouter eux-même leurs produits sur le site ?</span>
                        <input type="radio" value="true" name="question-5" class="btn-check float-end" id="question-5-true" checked/>
                        <label class="btn btn-outline-primary float-end" for="question-5-true">Oui</label>
                        <input type="radio" value="false" name="question-5" class="btn-check float-end" id="question-5-false"/>
                        <label class="btn btn-outline-primary float-end" for="question-5-false">Non</label>
                    </li>
                    <li>
                        <span>Est-ce que vous souhaitez donner des accès personnalisés à vos bénévoles ? Par exemple leur permettre d’accéder à la liste des commandes de vos adhérents mais sans leur donner accès à leurs informations personnelles.</span>
                        <input type="radio" value="true" name="question-6" class="btn-check" id="question-6-true" checked/>
                        <label class="btn btn-outline-primary" for="question-6-true">Oui</label>
                        <input type="radio" value="false" name="question-6" class="btn-check" id="question-6-false"/>
                        <label class="btn btn-outline-primary" for="question-6-false">Non</label> 
                    </li>
                </ul>
            </div>

            <div class="form-part">
                <h1 class="display-6">Des paniers oui, mais quoi d'autre ?</h1>

                <ul>
                    <li>
                        <span>Souhaitez-vous avoir la possibilité d’afficher des statistiques à partir de vos données ?</span>
                        <input type="radio" value="true" name="question-7" class="btn-check" id="question-7-true" checked/>
                        <label class="btn btn-outline-primary" for="question-7-true">Oui</label>
                        <input type="radio" value="false" name="question-7" class="btn-check" id="question-7-false"/>
                        <label class="btn btn-outline-primary" for="question-7-false">Non</label> 
                    </li>
                </ul>
            </div>

            <div class="form-part">
                <h1 class="display-6">Des paniers oui, mais quoi d'autre ?</h1>

                <ul>
                    <li>
                        <span>Souhaitez-vous améliorer le quotidien de vos adhérents en leur proposant des options de favoris, et la possibilité de choisir entre un thème dark et un thème light ?</span>
                        <input type="radio" value="true" name="question-8" class="btn-check" id="question-8-true" checked/>
                        <label class="btn btn-outline-primary" for="question-8-true">Oui</label>
                        <input type="radio" value="false" name="question-8" class="btn-check" id="question-8-false"/>
                        <label class="btn btn-outline-primary" for="question-8-false">Non</label> 
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
                <button type="submit" class="btn btn-primary">Valider</button>
            </div>

            <div id="nav-button">
                <button type="button" class="rounded-pill bg-primary" id="go-back-button">
                    Retour
                </button>
                <button type="button" class="rounded-pill bg-primary" id="continue-button">
                    Continuer
                </button>
            </div>
        </form:form>

    </div>
    <script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js'/>"></script>
    <script src="<c:url value='/resources/js/tenancycreation.js'/>"></script>
</body>
</html>