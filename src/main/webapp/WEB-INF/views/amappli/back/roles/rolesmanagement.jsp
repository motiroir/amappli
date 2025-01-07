<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Gestion des roles</title>
            <link href="https://api.mapbox.com/mapbox-gl-js/v2.15.0/mapbox-gl.css" rel="stylesheet" />
            <link rel="stylesheet" href="<c:url value='/resources/bootstrap/bootstrap.min.css' />">
            <link rel="stylesheet" href="<c:url value='/resources/css/amap/rolesmanagement.css' />">
        </head>

        <body class="${cssStyle} light ${font}-title ${font}-button">
            <header class="fc-main bg-main">
                <c:choose>
                    <c:when test="${amappli == true}">
                        <jsp:include page="../../common/header.jsp" />
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="../../../amap/front/common/header-amap.jsp" />
                    </c:otherwise>
                </c:choose>
            </header> 

            <div id="map"></div>

            <div class="fc-main">
                <h2>Rôles et Permissions</h2>

                <table id="rolesTable">
                    <thead>
                        <tr>
                            <th>Rôle</th>
                            <c:forEach var="permission" items="${permissions}">
                                <th>${permission.name}</th>
                            </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="role" items="${roles}">
                            <tr data-role-id="${role.roleId}" data-role-name="${role.name}">
                                <td>${role.name}</td>
                                <c:forEach var="permission" items="${permissions}">
                                    <td data-permission-id="${permission.permissionId}">
                                        <c:choose>
                                            <c:when
                                                test="${rolePermissionsMap[role.roleId] != null && rolePermissionsMap[role.roleId].contains(permission.permissionId)}">&#x2713;</c:when>
                                            <c:otherwise></c:otherwise>
                                        </c:choose>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <h2>Créer ou Modifier un Rôle</h2>

                <c:choose>
                    <c:when test="${amappli == true}">
                        <form action="${pageContext.request.contextPath}/amappli/roles/manage" method="post">
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/amap/roles/manage" method="post">
                    </c:otherwise>
                </c:choose>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" id="roleId" name="roleId">
                    <div>
                        <label for="roleName">Role Name</label>
                        <input type="text" class="form-control" id="roleName" name="roleName"
                            placeholder="Enter role name" required>
                    </div>

                    <div>
                        <label for="permissions">Available Permissions</label>
                        <div id="permissions">
                            <c:forEach var="permission" items="${permissions}">
                                <div>
                                    <input type="checkbox" id="perm-${permission.permissionId}" name="permissions"
                                        value="${permission.permissionId}">
                                    <label for="perm-${permission.permissionId}">
                                        ${permission.name}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <button type="submit">Save Role</button>
                </form>
            </div>

            <footer class="container-fluid fc-main bg-main">
                <c:choose>
                    <c:when test="${amappli == true}">
                        <jsp:include page="../../common/footer.jsp" />
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="../../../amap/front/common/footer-amap.jsp" />
                    </c:otherwise>
                </c:choose>
            </footer> 

            <script src="<c:url value='/resources/bootstrap/bootstrap.bundle.min.js' />"></script>

        <script>
            var styleMapboxLight = "${mapStyleLight}"
            var styleMapboxDark = "${mapStyleDark}"
            var latitude = "${latitude}"
            var longitude = "${longitude}"
        </script>

	<script src="<c:url value='/resources/js/common/mapbox/mapbox-gl.js' />"></script>
	<script src="<c:url value='/resources/js/common/mapbox/map.js' />"></script> 
	<script src="<c:url value='/resources/js/common/theme-swap.js' />" type="text/javascript"></script>
	<script src="<c:url value='/resources/js/common/palette-swap.js' />" type="text/javascript"></script>
    <script src="<c:url value='/resources/js/amap/admin/rolesmanagement.js' />" type="text/javascript"></script>

        </body>

        </html>