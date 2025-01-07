<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar h-100 position-sticky p-0 top-0 start-0 w-auto">
	<div id="padded-div" class="vh-100 position-sticky bg-main p-4 border-1 border-end">
		<div id="sidebar" class="collapse collapse-horizontal text-secondary fch-main show">
 			<ul id="accordion-parent" class="nav flex-column accordion">
			<!-- Vos utilisateurs -->
				<li class="accordion-item">
					<h2 id="users" class="accordion-header">
						<button class="accordion-button fw-bold ${currentMainMenu.equals('users') ? 'active' : 'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#submenu-users" aria-expanded="${currentMainMenu.equals('users') ? 'true' : 'false'}" aria-controls="submenu-users">
							Vos utilisateurs
						</button>
					</h2>
					<div id="submenu-users" data-bs-parent="#accordion-parent" class="bg-main accordion-collapse collapse ${currentMainMenu.equals('users') ? 'show' : ''}">
						<ul class="accordion-body list-unstyled">
							<li class="text-decoration-none">
								<a href="<c:url value='/amap/${tenancyAlias}/admin/users/list'/>" class="fch-600 text-decoration-none ${currentPage.equals('users') ? 'active' : ''}">Vos adhérents</a>
							</li>
							<li>
								<a href="<c:url value='/amap/${tenancyAlias}/admin/suppliers/list'/>" class="fch-600 text-decoration-none ${currentPage.equals('suppliers') ? 'active' : ''}">Vos fournisseurs</a>
							</li>
							<li class="d-flex justify-content-between">
								<a class="disabled text-decoration-none">Vos rôles personnalisés</a> <span class="badge rounded-pill text-secondary fw-bold border border-2 border-secondary bg-transparent">€</span>
							</li>
						</ul>
					</div>
				</li>
			<!-- Vos produits -->
				<li class="accordion-item"> 
					<h2 id="products" class="accordion-header">
						<button class="accordion-button fw-bold ${currentMainMenu.equals('products') ? 'active' : 'collapsed'}" type="button" data-bs-toggle="collapse" aria-expanded="${currentMainMenu.equals('products')}" aria-controls="submenu-product" data-bs-target="#submenu-products">
							Vos produits
						</button>
					</h2>
					<div id="submenu-products" data-bs-parent="#accordion-parent" class="accordion-collapse collapse bg-main ${currentMainMenu.equals('products') ? 'show' : ''}">
						<ul class="accordion-body list-unstyled">
							<li>
								<a href="<c:url value='/amap/${tenancyAlias}/admin/contracts/list'/>" class="${currentPage.equals('contracts') ? 'active' : ''} text-decoration-none fch-600">Les contrats</a>
							</li>
							<li>
								<a href="<c:url value='/amap/${tenancyAlias}/admin/orders/list'/>" class="${currentPage.equals('orders') ? 'active' : ''} text-decoration-none fch-600">Vos commandes</a>
							</li>
							<li class="d-flex justify-content-between">
								<a class="disabled text-decoration-none">L'épicerie</a><span class="badge rounded-pill text-secondary fw-bold border border-2 border-secondary bg-transparent">€</span>
							</li>
							<li class="d-flex justify-content-between">
								<a class="disabled text-decoration-none">Les ateliers</a><span class="badge rounded-pill text-secondary fw-bold border border-2 border-secondary bg-transparent">€</span>
							</li>
						</ul>
					</div>
				</li>
				<!-- Votre site -->
				<li class="accordion-item">
					<h2 id="site" class="accordion-header">
						<button class="accordion-button fw-bold ${currentMainMenu.equals('site') ? 'active' : 'collapsed'}" type="button" data-bs-toggle="collapse" aria-expanded="${currentMainMenu.equals('site')}" aria-controls="submenu-site" data-bs-target="#submenu-site">
							Votre site
						</button>
					</h2>
					<div id="submenu-site" data-bs-parent="#accordion-parent" class="accordion-collapse collapse bg-main ${currentMainMenu.equals('site') ? 'show' : ''}">
						<ul class="accordion-body list-unstyled">
							<!-- Pas de sous-rubrique pour l'instant -->
							<li class="fc-alt"><a href="<c:url value='/amap/${tenancyAlias}/home'/>" class="text-decoration-none fch-600" >Page d'accueil de <span class="text-capitalize">${tenancyAlias}</span></a></li>
						</ul>
					</div>
				</li>
			
				<!-- Vos fonctionnalités -->
				<li class="accordion-item">
					<h2 id="features" class="accordion-header" aria-expanded="${currentMainMenu.equals('features')}" aria-controls="submenu-features" data-bs-target="#submenu-features">
						<button class="accordion-button fw-bold collapsed" type="button" data-bs-toggle="collapse">
							Votre abonnement&emsp;&emsp;&emsp;
						</button>
					</h2>
					<div id="submenu-features" data-bs-parent="#accordion-parent" class="accordion-collapse collapse bg-main ${currentMainMenu.equals('features') ? 'show' : ''}">
						<ul class="accordion-body list-unstyled">
							<!-- Pas de sous-rubrique pour l'instant -->
						</ul>
					</div>
				</li>
			
				<!-- Rubriques PRO -->
				<li class="accordion-item pro">
					<h2 class="accordion-header ${currentMainMenu.equals('stats') ? 'show' :''}">
						<button class="accordion-button collapsed fw-bold disabled row pe-0 justify-content-between" type="button" data-bs-toggle="collapse" data-bs-target="" aria-expanded="false" aria-controls="">
							<span class="col">Statistiques</span>
							<div class="col-2">
								<span class="badge rounded-circle text-secondary fw-bold float-end border border-2 border-secondary bg-transparent">€</span>
							</div>
						</button>
					</h2>
				</li>
				<li class="accordion-item pro">
					<h2 class="accordion-header ${currentMainMenu.equals('messages') ? 'show' :''}">
						<button class="accordion-button collapsed fw-bold disabled row pe-0 justify-content-between" type="button" data-bs-toggle="collapse" data-bs-target="" aria-expanded="false" aria-controls="">
							<span class="col">Messagerie</span>
							<div class="col-2">
								<span class="badge rounded-circle text-secondary fw-bold float-end border border-2 border-secondary bg-transparent">€</span>
							</div>
						</button>
					</h2>
				</li>
				<li class="accordion-item pro">
					<h2 class="accordion-header ${currentMainMenu.equals('newsletter') ? 'show' :''}">
						<button class="accordion-button collapsed fw-bold disabled row pe-0 justify-content-between" type="button" data-bs-toggle="collapse" data-bs-target="" aria-expanded="false" aria-controls="">
							<span class="col">Newsletter</span>
							<div class="col-2">
								<span class="badge rounded-circle text-secondary fw-bold float-end border border-2 border-secondary bg-transparent">€</span>
							</div>
						</button>
					</h2>
				</li>
			</ul>
		</div>
	</div>
	<button id="button-collapse" class="navbar-toggler align-self-start m-2 d-none d-md-block" type="button" data-bs-toggle="collapse" role="button" aria-expanded="true" aria-controls="sidebar" data-bs-target="#sidebar">
		<span class="navbar-toggler-icon fill-main"></span>
	</button>
</nav>

