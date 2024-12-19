<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sidebar">
    <!-- Vos utilisateurs -->
    <div>
        <div class="menu-title ${currentMainMenu == 'users' ? 'active' : ''}" onclick="toggleMenu('users')">
            Vos utilisateurs
        </div>
        <ul id="users" class="submenu ${currentMainMenu == 'users' ? 'active' : ''}">
            <!-- Pas de sous-rubrique pour l'instant -->
        </ul>
    </div>
    <div class="separator"></div>

    <!-- Vos produits -->
    <div>
        <div class="menu-title ${currentMainMenu == 'products' ? 'active' : ''}" onclick="toggleMenu('products')">
            Vos produits
        </div>
        <ul id="products" class="submenu ${currentMainMenu == 'products' ? 'active' : ''}">
            <li>
                <a href="/amap/contracts" class="menu-item ${currentPage == 'contracts' ? 'active' : ''}">Les contrats</a>
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
        <div class="menu-title ${currentMainMenu == 'site' ? 'active' : ''}" onclick="toggleMenu('site')">
            Votre site
        </div>
        <ul id="site" class="submenu ${currentMainMenu == 'site' ? 'active' : ''}">
            <!-- Pas de sous-rubrique pour l'instant -->
        </ul>
    </div>
    <div class="separator"></div>

    <!-- Vos fonctionnalités -->
    <div>
        <div class="menu-title ${currentMainMenu == 'features' ? 'active' : ''}" onclick="toggleMenu('features')">
            Vos fonctionnalités
        </div>
        <ul id="features" class="submenu ${currentMainMenu == 'features' ? 'active' : ''}">
            <!-- Pas de sous-rubrique pour l'instant -->
        </ul>
    </div>
    <div class="separator"></div>

    <!-- Rubriques PRO -->
    <div class="menu-title pro">Vos statistiques<span class="pro-badge">PRO</span></div>
    <div class="menu-title pro">Messagerie<span class="pro-badge">PRO</span></div>
    <div class="menu-title pro">Newsletter<span class="pro-badge">PRO</span></div>
</div>

<script>
    // Script pour gérer l'accordéon
    function toggleMenu(menuId) {
        const menu = document.getElementById(menuId);
        if (menu.classList.contains('active')) {
            menu.classList.remove('active');
        } else {
            document.querySelectorAll('.submenu').forEach(submenu => submenu.classList.remove('active'));
            menu.classList.add('active');
        }
    }
</script>


