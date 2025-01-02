package isika.p3.amappli.controllers.amap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.dto.amappli.RoleDTO;
import isika.p3.amappli.entities.auth.Permission;
import isika.p3.amappli.entities.auth.Role;
import isika.p3.amappli.repo.amappli.PermissionRepository;
import isika.p3.amappli.service.amap.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	private final RoleService roleService;

	private final PermissionRepository permissionRepository;


	public RoleController(RoleService roleService, PermissionRepository permissionRepository) {
		this.roleService = roleService;
		this.permissionRepository = permissionRepository;
	}

	@GetMapping
	public String listRoles(Model model) {
		List<Role> roles = roleService.findAllRoles();
		model.addAttribute("roles", roles);
		return "roles/roleslist";
	}

	@GetMapping("/manage")
	public String showRolesWithPermissions(Model model) {
		// Loading existing roles and permissions
		List<Role> roles = roleService.findAllRoles();
		model.addAttribute("roles", roles);
		Iterable<Permission> permissions = permissionRepository.findAll();
        model.addAttribute("permissions", permissions);

		// Creating role/permission map because we can't check if an item is in a set with the jsp tags
		Map<Long, Set<Long>> rolePermissionsMap = new HashMap<>();
        for (Role role : roles) {
            Set<Long> permissionIds = role.getPermissions().stream()
                .map(Permission::getPermissionId)
                .collect(Collectors.toSet());
            rolePermissionsMap.put(role.getRoleId(), permissionIds);
        }
		model.addAttribute("rolePermissionsMap", rolePermissionsMap);
		// Adding an empty DTO for the form
		model.addAttribute("roleDTO", new RoleDTO());
		return "/amappli/back/roles/roles_creation";
	}

	@PostMapping("/manage")
	public String editRolesWithPermissions(RoleDTO roleDTO) {
		
		//Role r = Role.builder().name(name).permissions().build();
		roleService.manageRoleFromRoleManagmentPage(roleDTO);
		return "redirect:/roles/manage";
	}

	@GetMapping("/create")
	public String showCreateRoleForm(Model model) {
		model.addAttribute("role", new Role());
		return "roles/create"; // Vue JSP pour le formulaire de création
	}

	@PostMapping("/create")
	public String createRole(@ModelAttribute Role role, Model model) {
		try {
			roleService.createRole(role);
			return "redirect:/roles";
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
			return "roles/create";
		}
	}

	@PostMapping("/delete/{id}")
	public String deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
		return "redirect:/roles";
	}

	// Endpoint pour ajouter des rôles de test
	@GetMapping("/test-roles")
	public String addTestRoles(Model model) {
		try {
			roleService.addtestRoles();
			model.addAttribute("message", "Rôles ajoutés avec succès dans la Bdd !");
		} catch (Exception e) {
			model.addAttribute("error", "Erreur lors de l'ajout des rôles : " + e.getMessage());
		}
		return "roles/roleslist"; // Vue JSP pour afficher le résultat
	}

}
