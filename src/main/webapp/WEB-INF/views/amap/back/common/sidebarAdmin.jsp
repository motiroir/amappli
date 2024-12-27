<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c"%>
<div class="sidebar">
    <!-- Vos utilisateurs -->
    <div>
        <div id="users" class="menu-title ${currentMainMenu == 'users' ? 'active' : ''}" onmouseover="toggleMenu('users')">
            Vos utilisateurs
        </div>
        <ul id="users-submenu" class="submenu ${currentMainMenu == 'users' ? 'active' : ''}">
            <li>
                <a href="<c:url value='/tenancies/${tenancyId}/amap/admin/backoffice/users/list'/>" class="menu-item ${currentPage == 'users' ? 'active' : ''}">Vos adhérents</a>
            </li>
            <li>
                <a href="<c:url value='/tenancies/${tenancyId}/amap/admin/backoffice/suppliers/list'/>" class="menu-item ${currentPage == 'suppliers' ? 'active' : ''}">Vos fournisseurs</a>
            </li>
            <li>
                <a class="menu-item disabled">Vos rôles personnalisés<span class="pro-badge">PRO</span></a>
            </li>
        </ul>
    </div>
    <div class="separator"></div>

    <!-- Vos produits -->
    <div>
        <div id="products" class="menu-title ${currentMainMenu == 'products' ? 'active' : ''}" onmouseover="toggleMenu('products')">
            Vos produits
        </div>
        <ul id="products-submenu" class="submenu ${currentMainMenu == 'products' ? 'active' : ''}">
            <li>
                <a href="<c:url value='/amap/contracts/list' />" class="menu-item ${currentPage == 'contracts' ? 'active' : ''}">Les contrats</a>
            </li>
            <li>
                <a class="menu-item disabled">L'épicerie<span class="pro-badge">PRO</span></a>
            </li>
            <li>
                <a class="menu-item disabled">Les ateliers<span class="pro-badge">PRO</span></a>
            </li>
        </ul>
    </div>
    <div class="separator"></div>

    <!-- Votre site -->
    <div>
        <div id="site" class="menu-title ${currentMainMenu == 'site' ? 'active' : ''}" onmouseover="toggleMenu('site')">
            Votre site
        </div>
        <ul id="site-submenu" class="submenu ${currentMainMenu == 'site' ? 'active' : ''}">
            <!-- Pas de sous-rubrique pour l'instant -->
        </ul>
    </div>
    <div class="separator"></div>

    <!-- Vos fonctionnalités -->
    <div>
        <div id="features" class="menu-title ${currentMainMenu == 'features' ? 'active' : ''}" onmouseover="toggleMenu('features')">
            Vos fonctionnalités
        </div>
        <ul id="features-submenu" class="submenu ${currentMainMenu == 'features' ? 'active' : ''}">
            <!-- Pas de sous-rubrique pour l'instant -->
        </ul>
    </div>
    <div class="separator"></div>

    <!-- Rubriques PRO -->
    <div class="menu-title pro">Statistiques<span class="pro-badge">PRO</span></div>
    <div class="menu-title pro">Messagerie<span class="pro-badge">PRO</span></div>
    <div class="menu-title pro">Newsletter<span class="pro-badge">PRO</span></div>
</div>

<script>
    // Script pour gérer l'accordéon
    function toggleMenu(menuId) {
        const menu = document.getElementById(menuId);
        const subMenu = document.getElementById(menuId + "-submenu");
        console.log(subMenu);
/*         if (menu.classList.contains('active')) {
            menu.classList.remove('active');
            subMenu.classList.remove('active');
        } else { */
            document.querySelectorAll('.submenu').forEach(everySubmenu => everySubmenu.classList.remove('active'));
            document.querySelectorAll('.menu-title').forEach(menuTitle => menuTitle.classList.remove('active'));
            menu.classList.add('active');
            subMenu.classList.add('active');
     /*    } */
    }
</script>


