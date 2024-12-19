<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création de votre espace AMAP</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
    body {
        background-image: url("<c:url value='/resources/img/peach_lines.svg'/>");
    }
</style>
</head>
<body>
    <div class="container w-75 p-5 h-75">

        <div class="title d-flex">
            <h1 class="display-4 w-100">Faisons connaissance</h1>
            <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
                <div class="progress-bar" style="width: 0%" id="the-progress-bar"></div>
            </div>
        </div>
        <hr>

        <div id="form-part-0">
            <p>Pour vous aider à créer le site de votre AMAP, nous allons vous poser une série de questions pour mieux vous connaître. Cela devrait prendre moins de 5 minutes. Veuillez préparer les informations concernant votre AMAP.</p>
            <h1 class="display-6">Etes-vous prêts ?</h1>
            <button type="button" class="rounded-pill bg-primary" id="continue-button-0">c'est parti !</button>
        </div>

        <form:form action="${pageContext.request.contextPath}/start/creation" method="post" modelAttribute="newTenancyDTO" enctype="multipart/form-data">
            <div id="form-part-1">

                <div class="mb-3">
                    <form:label for="input-tenancy-name" class="form-label display-6" path="tenancyName">Quel est le nom de votre amap ?</form:label>
                    <form:input type="text" class="form-control" id="input-tenancy-name" aria-describedby="input-tenancy-name" path="tenancyName" required="true" aria-required="true"/>
                    <form:errors path="tenancyName" class="invalid-feedback d-block" />


                <button type="button" class="rounded-pill bg-primary" id="go-back-button-1">
                        Retour
                </button>
                <button type="button" class="rounded-pill bg-primary" id="continue-button-1">
                        Continuer
                </button>
                </div>
            </div>

            <div id="form-part-2">
                <h1 class="display-6">Quelles sont vos valeurs ?</h1>
                <p>changed again again!</p>
                <div id="value-form" class="d-flex flex-row justify-content-center">
                <c:forEach items="${newTenancyDTO.values}" var="valueDTO" varStatus="status">
                    <div class="mb-3 d-flex flex-column">
                        <form:input class="form-control" id="input-value-name-${status.index}" aria-describedby="input-value-name" path="values[${status.index}].name" />
                        <form:textarea class="form-control" id="input-value-description-${status.index}" aria-describedby="input-value-description" path="values[${status.index}].description" />
                        <form:input type="file" class="form-control" id="input-value-description-${status.index}" aria-describedby="input-value-file" path="values[${status.index}].file" accept="image/png,image/jpeg,image/svg"/>
                    </div>
                </c:forEach>
                </div>
                <button type="button" class="rounded-pill bg-primary" id="go-back-button-1">
                    Retour
                </button>
                <button type="button" class="rounded-pill bg-primary" id="continue-button-1">
                    Continuer
                </button>
                
            </div>

            <div id="form-part-3">
                <div id="palette-choices" class="d-flex flex-row justify-content-center">
                    <c:forEach items="${colorPalettes}" var="palette">
                        <img src="<c:url value='/resources/img/logo_amappli_peach.png' />" with="100" height="100"/>
                        <form:radiobutton path="colorPalette" value="${palette}" label="${palette}" name="palette-selection" />
                    </c:forEach>
                </div>
                <button type="button" class="rounded-pill bg-primary" id="go-back-button-1">
                    Retour
                </button>
                <button type="button" class="rounded-pill bg-primary" id="continue-button-1">
                    Continuer
                </button>
            </div>

            <div id="form-part-4">
                <h1 class="display-6">Des paniers oui, mais quoi d'autres ?</h1>

                <ul>
                    <li>
                        <span>Est-ce que vous vendez des produits à l'unité en plus de vos paniers ?</span>
                        <form:radiobutton path="marketPlace" value="true" name="question-1" class="btn-check" id="question-1-true"/>
                        <label class="btn btn-outline-primary" for="question-1-true">Oui</label>
                        <form:radiobutton path="marketPlace" value="false" name="question-1" class="btn-check" id="question-1-false"/>
                        <label class="btn btn-outline-primary" for="question-1-false">Non</label>
                    </li>
                    <li>
                        <span>Est-ce que vous souhaitez proposer des lots et des promos à vos adhérents ?</span>
                        <form:radiobutton path="promos" value="true" name="question-1" class="btn-check" id="question-2-true"/>
                        <label class="btn btn-outline-primary" for="question-2-true">Oui</label>
                        <form:radiobutton path="promos" value="false" name="question-1" class="btn-check" id="question-2-false"/>
                        <label class="btn btn-outline-primary" for="question-2-false">Non</label> 
                    </li>
                    <li>
                        <span>Organisez-vous des ateliers que vous souhaiteriez mettre à disposition de vos adhérents sur ce site ?</span>
                        <form:radiobutton path="events" value="true" name="question-1" class="btn-check" id="question-3-true"/>
                        <label class="btn btn-outline-primary" for="question-3-true">Oui</label>
                        <form:radiobutton path="events" value="false" name="question-1" class="btn-check" id="question-3-false"/>
                        <label class="btn btn-outline-primary" for="question-3-false">Non</label> 
                    </li>
                </ul>
            </div>

            <div id="form-part-x">

              
                <button type="submit" class="btn btn-primary">Valider</button>
            </div>

        </form:form>

    </div>
    <script>
    document.addEventListener("DOMContentLoaded", function () {
        
    });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>