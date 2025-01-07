<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar h-100 position-sticky p-0 top-0 start-0 w-auto">
	<div class="vh-100 bg-main p-4 border-1 border-end d-flex flex-column">
		<div id="sidebar">
			<ul class="nav flex-column">
				<!-- Paniers maraîchers -->
				<li class="mb-4">
					<h2 class="fw-bold">Paniers</h2>
					<hr class="my-2">
					<ul class="list-unstyled ps-3">
						<li class="d-flex justify-content-between"><a id="allBaskets"
							href="#" class="text-decoration-none fch-600">Tous les
								paniers</a> <span>${contracts.size()}</span> <!-- Affiche le nombre total de contrats -->
						</li>
						<li class="d-flex justify-content-between"><a
							id="vegetableBaskets" href="#"
							class="text-decoration-none fch-600">Paniers légumes</a> <span>${vegetableCount}</span>
							<!-- Affiche le nombre de contrats légumes --></li>
						<li class="d-flex justify-content-between"><a
							id="fruitBaskets" href="#" class="text-decoration-none fch-600">Paniers
								fruits</a> <span>${fruitCount}</span> <!-- Affiche le nombre de contrats fruits -->
						</li>
						<li class="d-flex justify-content-between"><a
							id="mixedBaskets" href="#" class="text-decoration-none fch-600">Paniers
								mixtes</a> <span>${mixedCount}</span> <!-- Affiche le nombre de contrats mixtes -->
						</li>
					</ul>


				</li>
				<br>

				<!-- Épicerie -->
				<li class="mb-4"><a
					href="<c:url value='/${tenancyAlias}/shop/products'/>"
					class="${currentMainMenu.equals('products') ? 'active' : ''} text-decoration-none">
						<h2 class="fw-bold">Épicerie</h2>
				</a>
					<hr class="my-2"></li><br>

				<!-- Ateliers -->
				<li class="mb-4"><a
					href="<c:url value='/${tenancyAlias}/shop/workshops'/>"
					class="${currentMainMenu.equals('workshops') ? 'active' : ''} text-decoration-none">
						<h2 class="fw-bold">Ateliers</h2>
				</a>
					<hr class="my-2"></li>
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

