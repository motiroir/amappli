<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.navbar {
    position: sticky; /* Rendre sticky */
    top: 0; /* Fixer au sommet lors du défilement */
    z-index: 1020; /* S'assurer qu'elle reste devant les autres éléments */
    overflow-y: auto; /* Pour gérer le contenu s'il dépasse la hauteur de la fenêtre */
    max-height: 100vh; /* Limite la hauteur pour éviter de dépasser l'écran */
}

</style>
<nav class="navbar h-50 p-5 w-auto">
	<div
		class="bg-100 border border-1 border-main d-flex flex-column justify-content-center mx-auto rounded-top">
		<div id="sidebar" class="flex-grow-1">
			<ul class="nav flex-column text-start">
				<!-- Paniers maraîchers -->
				<li><a
					href="<c:url value='/amap/${tenancyAlias}/shop/contracts'/>"
					class="ps-2 pe-5 pt-3 d-flex align-items-left border-bottom ${currentMainMenu.equals('contracts') ? 'active bg-500 fc-100' : ' fc-500 text-decoration-none'}">
						<i class="bi bi-basket me-2"></i>
						<h4 class="fw-bold">Paniers</h4>
				</a>
					<ul class="list-unstyled ps-2">
						<li class="d-flex justify-content-between pt-2">
							<a id="allBaskets" href="#" class="text-decoration-none fc-300 fch-600 flex-grow-1">Tous
								les paniers</a> <span class="badge bg-none fc-300 rounded-pill ms-5">${contracts.size()}</span>
						</li>
						<br>
						<li class="d-flex justify-content-between">
							<a id="vegetableBaskets" href="#"
							class="text-decoration-none fc-300 fch-600 flex-grow-1">Paniers
								légumes</a> <span class="badge bg-none fc-300 rounded-pill ms-5">${vegetableCount}</span>
						</li>
						<br>
						<li class="d-flex justify-content-between">
							<a id="fruitBaskets" href="#"
							class="text-decoration-none fc-300 fch-600 flex-grow-1">Paniers fruits</a> <span
							class="badge bg-none fc-300 rounded-pill ms-5">${fruitCount}</span>
						</li>
						<br>
						<li class="d-flex justify-content-between border-bottom pb-3">
							<a id="mixedBaskets" href="#"
							class="text-decoration-none fc-300 fch-600 flex-grow-1">Paniers mixtes</a> <span
							class="badge bg-none fc-300 rounded-pill ms-5">${mixedCount}</span><hr class="my-1">
						</li>
					</ul></li>


				<!-- Épicerie -->
				<li><a
					href="<c:url value='/amap/${tenancyAlias}/shop/products'/>"
					class="ps-2 pe-5 pt-3 pb-2 d-flex align-items-left border-bottom ${currentMainMenu.equals('products') ? 'active bg-500 fc-100' : 'fc-500 text-decoration-none'}">
						<i class="bi bi-shop-window me-2"></i>
						<h4 class="fw-bold">Épicerie</h4>
				</a>
					


				<!-- Ateliers -->
				<li><a
					href="<c:url value='/amap/${tenancyAlias}/shop/workshops'/>"
					class="ps-2 pe-5 pt-3 pb-2 d-flex align-items-left border-bottom ${currentMainMenu.equals('workshops') ? 'active bg-500 fc-100' : 'fc-500 text-decoration-none'}">
						<i class="bi bi-people me-2"></i>
						<h4 class="fw-bold">Ateliers</h4>
				</a>
			</ul>
		</div>
	</div>
</nav>

<script>
document.addEventListener('DOMContentLoaded', function () {
    // Récupération des liens de filtre
    const vegetableButton = document.getElementById('vegetableBaskets');
    const fruitButton = document.getElementById('fruitBaskets');
    const mixedButton = document.getElementById('mixedBaskets');
    const allButton = document.getElementById('allBaskets');

    // Récupération des contrats affichés
    const contracts = document.querySelectorAll('.contract-card'); // Chaque contrat doit avoir cette classe

    // Fonction pour filtrer les contrats
    function filterContracts(contractType) {
        contracts.forEach(contract => {
            const type = contract.getAttribute('data-contract-type'); // Récupère le type de contrat
            if (contractType === 'ALL' || type === contractType) {
                contract.style.display = ''; // Affiche le contrat
            } else {
                contract.style.display = 'none'; // Masque le contrat
            }
        });
    }

    // Ajout des événements de clic
    allButton.addEventListener('click', function (e) {
        e.preventDefault();
        filterContracts('ALL');
    });

    vegetableButton.addEventListener('click', function (e) {
        e.preventDefault();
        filterContracts('VEGETABLES_CONTRACT');
    });

    fruitButton.addEventListener('click', function (e) {
        e.preventDefault();
        filterContracts('FRUITS_CONTRACT');
    });

    mixedButton.addEventListener('click', function (e) {
        e.preventDefault();
        filterContracts('MIX_CONTRACT');
    });
});
</script>
