<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar h-100 position-sticky p-0 top-0 start-0 w-auto">
	<div id="padded-div" class="vh-100 position-sticky bg-main p-4 border-1 border-end">
		<div id="sidebar" class="collapse collapse-horizontal text-secondary fch-main show">
 			<ul id="accordion-parent" class="nav flex-column accordion">
 			
			<li class="accordion-item">
    <h2 id="profile" class="accordion-header">
        <a href="<c:url value='/amap/${tenancyAlias}/${userId}/profile' />" 
           class="accordion-button fw-bold ${currentPage.equals('profile') ? 'active' : ''}">Mon Profil</a>
    </h2>
			</li>

			<!-- Vos commandes -->
				<li class="accordion-item">
					<h2 id="products" class="accordion-header">
						<button
							class="accordion-button fw-bold ${currentMainMenu.equals('orders') ? 'active' : 'collapsed'}"
							type="button" data-bs-toggle="collapse"
							aria-expanded="${currentMainMenu.equals('orders')}"
							aria-controls="submenu-product"
							data-bs-target="#submenu-products">Mes commandes</button>
					</h2>
					<div id="submenu-products" data-bs-parent="#accordion-parent"
						class="accordion-collapse collapse bg-main ${currentMainMenu.equals('orders') ? 'show' : ''}">
						<ul class="accordion-body list-unstyled">
							<li><a
								href="<c:url value='/amap/${tenancyAlias}/account/my-orders/${userId}'/>"
								class="${currentPage.equals('orders') ? 'active' : ''} text-decoration-none fch-600">Mes commandes passées</a></li>
							<li><a
								href="<c:url value='/amap/${tenancyAlias}/backoffice/contracts/list'/>"
								class="${currentPage.equals('contracts') ? 'active' : ''} text-decoration-none fch-600">Mon point de collecte</a></li>
							
							<li><a class="disabled text-decoration-none">Mes
									ateliers</a> &emsp;<span
								class="badge rounded-pill text-secondary fw-bold border border-2 border-secondary bg-transparent">PRO</span>
							</li>
						</ul>
					</div>
				</li>
				
				
				<!-- Vos fonctionnalités -->
				<li class="accordion-item">
					<h2 id="features" class="accordion-header"
						aria-expanded="${currentMainMenu.equals('features')}"
						aria-controls="submenu-features"
						data-bs-target="#submenu-features">
						<button class="accordion-button fw-bold collapsed" type="button"
							data-bs-toggle="collapse">Mon adhésion&emsp;&emsp;&emsp;</button>
					</h2>
					<div id="submenu-features" data-bs-parent="#accordion-parent"
						class="accordion-collapse collapse bg-main ${currentMainMenu.equals('features') ? 'show' : ''}">
						<ul class="accordion-body list-unstyled">
							<!-- Pas de sous-rubrique pour l'instant -->
						</ul>
					</div>
				</li>
				
			
				<!-- Rubriques PRO -->
				<li class="disabled accordion-item pro">
					<h2
						class="accordion-header ${currentMainMenu.equals('stats') ? 'show' :''}">
						<button class="accordion-button collapsed fw-bold disabled"
							type="button" data-bs-toggle="collapse" data-bs-target=""
							aria-expanded="false" aria-controls="">
							Mes favoris &emsp;<span
								class="badge rounded-pill text-secondary fw-bold border border-2 border-secondary bg-transparent">PRO</span>
						</button>
					</h2>
				</li>
			
					<!-- Rubriques PRO -->
				<li class="disabled accordion-item pro">
					<h2
						class="accordion-header ${currentMainMenu.equals('stats') ? 'show' :''}">
						<button class="accordion-button collapsed fw-bold disabled"
							type="button" data-bs-toggle="collapse" data-bs-target=""
							aria-expanded="false" aria-controls="">
							Mes favoris &emsp;<span
								class="badge rounded-pill text-secondary fw-bold border border-2 border-secondary bg-transparent">PRO</span>
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