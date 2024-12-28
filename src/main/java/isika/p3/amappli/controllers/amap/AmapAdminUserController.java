package isika.p3.amappli.controllers.amap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.dto.amap.SupplierDTO;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.service.amap.AmapAdminUserService;
import isika.p3.amappli.service.amap.RoleService;


@Controller
@RequestMapping("{tenancyAlias}/backoffice")
public class AmapAdminUserController {
    
	@Autowired
	private AmapAdminUserService adminUserService;
	
    @Autowired
    private final RoleService roleService;
	
	public AmapAdminUserController(AmapAdminUserService adminUserService, RoleService roleService) {
		this.adminUserService = adminUserService;
		this.roleService = roleService;
	}

	@GetMapping("/users/list")
	public String usersList(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		List<User> users = adminUserService.findAll(tenancyAlias);
		model.addAttribute("users", users);
		model.addAttribute("tenancyAlias", tenancyAlias);
		return "amap/back/users/users-list";
	}
	
	
	@GetMapping("/users/details/{userId}")
	public String usersDetails(@PathVariable("userId") Long userId, Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		User supplier = adminUserService.findById(userId);
		model.addAttribute("user", supplier);
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
		return "amap/back/users/users-details";
	}
	
	@GetMapping("/users/form")
	public String usersForm(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		model.addAttribute("user", new User());
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
		return "amap/back/users/users-form";
	}
	
	@PostMapping("/users/delete/{userId}")
	public String usersHide(@PathVariable("userId") Long userId) {
		adminUserService.hideById(userId);
		return "redirect:../list";
	}
	
	@GetMapping("/users/generateFakes")
	public String usersAddFake(@PathVariable("tenancyAlias") String tenancyAlias) {
		adminUserService.generateUsers(tenancyAlias);
		return "redirect:/users-list";
	}
	
	@GetMapping("/suppliers/list")
	public String suppliersList(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		List<User> suppliers = adminUserService.findSuppliers(tenancyAlias);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("tenancyAlias", tenancyAlias);
		return "amap/back/users/suppliers-list";
	}
	

	@GetMapping("/suppliers/form")
	public String suppliersForm(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		model.addAttribute("supplier", new User());
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
		return "amap/back/users/suppliers-form";
	}
	

	@PostMapping("/suppliers/add")
	public String SuppliersAdd(@ModelAttribute("supplierDTO") SupplierDTO supplierDTO, @PathVariable("tenancyAlias") String tenancyAlias) {
	    adminUserService.addTenancySupplier(supplierDTO, tenancyAlias);
	    return "redirect:list";
	}
	

	@GetMapping("/suppliers/delete/{userId}")
	public String suppliersHide(@PathVariable("userId") Long userId, @PathVariable("tenancyAlias") String tenancyAlias) {
		adminUserService.hideById(userId);
		return "redirect:../list";
	}
	

	@GetMapping("/suppliers/edit/{userId}")
	public String suppliersEdit(@PathVariable("userId") Long userId, Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		User supplier = adminUserService.findById(userId);
		model.addAttribute("supplier", supplier);
		model.addAttribute("tenancyAlias", tenancyAlias);
		return "amap/back/users/suppliers-edit";
	}
	
	@GetMapping("/suppliers/details/{userId}")
	public String suppliersDetails(@PathVariable("userId") Long userId, Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
	    User supplier = adminUserService.findById(userId);
	    model.addAttribute("user", supplier);
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
	    return "amap/back/users/suppliers-details";
	}
	
}
