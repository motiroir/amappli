package isika.p3.amappli.controllers.amap;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.repo.amappli.PermissionRepository;
import isika.p3.amappli.security.CustomUserDetails;
import isika.p3.amappli.service.amap.RoleService;
import isika.p3.amappli.service.amap.UserService;

@Controller
public class RoleController {

	private final RoleService roleService;

	private final PermissionRepository permissionRepository;

	private final UserService userService;


	public RoleController(RoleService roleService, PermissionRepository permissionRepository, UserService userService) {
		this.roleService = roleService;
		this.permissionRepository = permissionRepository;
		this.userService = userService;
	}

	

	@PreAuthorize("hasAuthority('gestion plateforme')")
	@GetMapping
	public String listRoles(Model model) {
		List<Role> roles = roleService.findAllRoles();
		model.addAttribute("roles", roles);
		return "/amappli/back/roles/roleslist";
	}

	@PreAuthorize("hasAuthority('gestion plateforme')")
	@GetMapping("/amappli/roles/manage")
	public String showGeneralRolesWithPermissions(Model model) {

		// Loading existing roles and permissions
		List<Role> roles = roleService.findAllGenericRoles();
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
		return "/amappli/back/roles/rolesmanagement";
	}

	@PreAuthorize("hasAuthority('gestion plateforme')")
	@PostMapping("//amappli/roles/manage")
	public String editGeneralRolesWithPermissions(RoleDTO roleDTO) {
		
		//Role r = Role.builder().name(name).permissions().build();
		roleService.manageRoleFromRoleManagmentPage(roleDTO);
		return "redirect:/roles/rolesmanagement";
	}

	@PreAuthorize("hasAuthority('gestion utilisateurs amap') and (hasAuthority(#alias) or hasAuthority('gestion plateforme'))")
	@GetMapping("/amap/{tenancyAlias}/roles/manage")
	public String showRolesWithPermissions(@PathVariable("tenancyAlias") String alias, Model model) {

		// Get logged user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails loggedUserInfo = (CustomUserDetails) authentication.getPrincipal();

		User user = userService.findById((Long) loggedUserInfo.getAdditionalInfoByKey("userId"));
		
		// Get applicable roles and permissions
		List<Role> roles = roleService.findAmapExclusiveRoles(alias);
		model.addAttribute("roles", roles);
		// An user can only manage permissions he already has himself
		Collection<Permission> permissions = user.getPermissions();
        model.addAttribute("permissions", permissions);

		// Creating role/permission map because we can't check if an item is in a set with the jsp tags
		Map<Long, Set<Long>> rolePermissionsMap = new HashMap<>();
        for (Role role : roles) {
            Set<Long> permissionIds = role.getPermissions().stream()
                .map(Permission::getPermissionId)
				.filter( permissionId -> permissions.stream().anyMatch(p -> p.getPermissionId().equals(permissionId)))
                .collect(Collectors.toSet());
            rolePermissionsMap.put(role.getRoleId(), permissionIds);
        }
		model.addAttribute("rolePermissionsMap", rolePermissionsMap);

		// Adding an empty DTO for the form
		model.addAttribute("roleDTO", new RoleDTO());
		return "/amappli/back/roles/rolesmanagement";

	}


	// @GetMapping("/create")
	// public String showCreateRoleForm(Model model) {
	// 	model.addAttribute("role", new Role());
	// 	return "roles/create"; // Vue JSP pour le formulaire de création
	// }

	// @PostMapping("/create")
	// public String createRole(@ModelAttribute Role role, Model model) {
	// 	try {
	// 		roleService.createRole(role);
	// 		return "redirect:/roles";
	// 	} catch (IllegalArgumentException e) {
	// 		model.addAttribute("error", e.getMessage());
	// 		return "roles/create";
	// 	}
	// }

	// @PostMapping("/delete/{id}")
	// public String deleteRole(@PathVariable Long id) {
	// 	roleService.deleteRole(id);
	// 	return "redirect:/roles";
	// }

	// // Endpoint pour ajouter des rôles de test
	// @GetMapping("/test-roles")
	// public String addTestRoles(Model model) {
	// 	try {
	// 		roleService.addtestRoles();
	// 		model.addAttribute("message", "Rôles ajoutés avec succès dans la Bdd !");
	// 	} catch (Exception e) {
	// 		model.addAttribute("error", "Erreur lors de l'ajout des rôles : " + e.getMessage());
	// 	}
	// 	return "roles/roleslist"; // Vue JSP pour afficher le résultat
	// }

}
