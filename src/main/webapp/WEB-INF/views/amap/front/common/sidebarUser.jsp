<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.sidebar {
    background-color: #FFF;
    padding: 20px;
    border: 1px solid #E6E6E6;
}

/* Conteneur de rubrique */
.container-rubrique {
    margin-bottom: 20px;
    border: 1px solid #FFBE98;
    background-color: #FFFAF8;
    overflow: hidden;
    transition: max-height 0.3s ease;
}

/* En-tête de rubrique */
.rubrique-header {
    font-size: 24px;
    font-weight: bold;
    padding: 15px 20px;
    cursor: pointer;
    border-bottom: 1px solid #FFBE98;
}

/* Soulignement de l’en-tête actif */
.rubrique-header.active {
    text-decoration: underline;
}

/* Contenu des sous-rubriques */
.rubrique-content {
    display: none;
    padding: 15px 20px;
}

.rubrique-content ul {
    padding-left: 0;
}

.rubrique-content li {
    font-size: 15px;
    font-weight: normal;
    list-style: none;
    margin-bottom: 10px;
}

.rubrique-content li a {
    text-decoration: none;
    color: black;
}

.rubrique-content li a.active {
    color: #FFBE98;
}

/* Afficher les sous-rubriques */
.container-rubrique.expanded .rubrique-content {
    display: block;
}
</style>
    <!-- Rubrique Paniers -->
    				<li class="accordion-item"> 
					<h2 id="contracts" class="accordion-header">
						<button class="accordion-button fw-bold ${currentMainMenu.equals('contracts') ? 'active' : 'collapsed'}" type="button" data-bs-toggle="collapse" aria-expanded="${currentMainMenu.equals('contracts')}" aria-controls="submenu-contracts" data-bs-target="#submenu-contracts">
							Paniers
						</button>
					</h2>
					<div id="submenu-contracts" data-bs-parent="#accordion-parent"class="accordion-collapse collapse bg-main ${currentMainMenu.equals('contracts') ? 'show' : ''}">
						<ul class="accordion-body list-unstyled">
							<li>
								<a href="<c:url value='/${tenancyAlias}/shop/contracts'/>" class="${currentPage.equals('contracts') ? 'active' : ''} text-decoration-none fch-600">Tous les paniers</a>
							</li>
							<li>
								<a href="<c:url value='/${tenancyAlias}/shop/contracts'/>" class="${currentPage.equals('contracts') ? 'active' : ''} text-decoration-none fch-600">Paniers de légumes</a>
							</li>
							<li>
								<a href="<c:url value='/${tenancyAlias}/shop/contracts'/>" class="${currentPage.equals('contracts') ? 'active' : ''} text-decoration-none fch-600">Paniers de fruits</a>
							</li>
							<li>
								<a href="<c:url value='/${tenancyAlias}/shop/contracts'/>" class="${currentPage.equals('contracts') ? 'active' : ''} text-decoration-none fch-600">Paniers mixtes</a>
							</li>
						</ul>
					</div>
				</li>
<!--     <div class="container-rubrique"> -->
<!--         <div class="rubrique-header" data-target="paniers-container"> -->
<!--             Paniers -->
<!--         </div> -->
<!--         <div id="paniers-container" class="rubrique-content"> -->
<!--             <ul class="list-unstyled"> -->
<%--                 <li><a href="#" class="${currentPage == 'all' ? 'active' : ''}">Tous les paniers --%>
<%--                     <span class="badge bg-secondary">${counts.all}</span></a></li> --%>
<%--                 <li><a href="#" class="${currentPage == 'vegetables' ? 'active' : ''}">Paniers légumes --%>
<%--                     <span class="badge bg-secondary">${counts.vegetables}</span></a></li> --%>
<%--                 <li><a href="#" class="${currentPage == 'fruits' ? 'active' : ''}">Paniers fruits --%>
<%--                     <span class="badge bg-secondary">${counts.fruits}</span></a></li> --%>
<%--                 <li><a href="#" class="${currentPage == 'mixed' ? 'active' : ''}">Paniers mixtes --%>
<%--                     <span class="badge bg-secondary">${counts.mixed}</span></a></li> --%>
<!--             </ul> -->
<!--         </div> -->
<!--     </div> -->

    <!-- Rubrique Epicerie -->
    <div class="container-rubrique">
        <div class="rubrique-header" data-target="epicerie-container">
            Epicerie
        </div>
        <div id="epicerie-container" class="rubrique-content">
            <ul class="list-unstyled">
                <li><a href="#">Produits</a></li>
            </ul>
        </div>
    </div>

    <!-- Rubrique Ateliers -->
    <div class="container-rubrique">
        <div class="rubrique-header" data-target="ateliers-container">
            Ateliers
        </div>
        <div id="ateliers-container" class="rubrique-content">
            <ul class="list-unstyled">
                <li><a href="#">Workshops</a></li>
            </ul>
        </div>
    </div>
<script>
document.addEventListener("DOMContentLoaded", () => {
    const rubriqueHeaders = document.querySelectorAll(".rubrique-header");

    rubriqueHeaders.forEach(header => {
        header.addEventListener("click", () => {
            const targetId = header.getAttribute("data-target");
            const container = document.getElementById(targetId);

            // Fermer les autres conteneurs
            document.querySelectorAll(".container-rubrique").forEach(rubrique => {
                rubrique.classList.remove("expanded");
                rubrique.querySelector(".rubrique-content").style.display = "none";
            });

            // Ouvrir ou fermer le conteneur sélectionné
            if (container) {
                const parentContainer = header.parentElement;
                parentContainer.classList.add("expanded");
                container.style.display = "block";
                header.classList.add("active");
            }
        });
    });
});
</script>

