package isika.p3.amappli.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.entities.auth.Role;
import isika.p3.amappli.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	 @GetMapping
	    public String listRoles(Model model) {
	        List<Role> roles = roleService.findAllRoles();
	        model.addAttribute("roles", roles);
	        return "roles/roleslist";
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
