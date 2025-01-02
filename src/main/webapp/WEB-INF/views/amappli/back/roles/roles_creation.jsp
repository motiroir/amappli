<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Création/Modification de roles</title>
        </head>

        <body>
            <div>
                <h2>Roles et Permissions</h2>

                <table id="rolesTable">
                    <thead>
                        <tr>
                            <th>Role Name</th>
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

                <h2>Create or Update Role</h2>

                <form action="${pageContext.request.contextPath}/roles/manage" method="post">
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

            <script>
                // Populate the form with data when a row is clicked
                document.addEventListener('DOMContentLoaded', function () {
    let rows = document.querySelectorAll('#rolesTable tbody tr');

    rows.forEach(row => {
        row.addEventListener('click', function () {
            let roleId = this.getAttribute('data-role-id');
            let roleName = this.querySelector('td:first-child').textContent.trim();

            // Set the form values
            document.getElementById('roleId').value = roleId;
            document.getElementById('roleName').value = roleName;

            // Clear all checkboxes
            document.querySelectorAll('#permissions input[type=checkbox]').forEach(checkbox => {
                checkbox.checked = false;
            });

            // Find checked permissions from table cells
            let cells = this.querySelectorAll('td[data-permission-id]');
            cells.forEach(cell => {
                console.log(cell.textContent.trim());
                if (cell.textContent.trim() === '✓') { // Check if the cell contains a checkmark
                    let permissionId = cell.getAttribute('data-permission-id');
                    let checkboxId = "perm-"+permissionId;
                    let checkbox = document.getElementById(checkboxId);
                    if (checkbox) {
                        checkbox.checked = true;
                    }
                }
            });
        });
    });
});
            </script>

        </body>

        </html>