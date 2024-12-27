package isika.p3.amappli.controllers.amap.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import isika.p3.amappli.dto.SupplierDTO;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.service.AmapAdminUserService;
import isika.p3.amappli.service.RoleService;


@Controller
@RequestMapping("tenancies/{tenancyId}/amap/admin/backoffice")
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
	public String usersList(Model model, @PathVariable("tenancyId") Long tenancyId) {
		List<User> users = adminUserService.findAll(tenancyId);
		model.addAttribute("users", users);
		model.addAttribute("tenancyId", tenancyId);
		return "amap/admin/users/users-list";
	}
	
	
	@GetMapping("/users/details/{userId}")
	public String usersDetails(@PathVariable("userId") Long userId, Model model, @PathVariable("tenancyId") Long tenancyId) {
		User supplier = adminUserService.findById(userId);
		model.addAttribute("user", supplier);
		model.addAttribute("tenancyId", tenancyId);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
		return "amap/admin/users/users-details";
	}
	
	@GetMapping("/users/form")
	public String usersForm(Model model, @PathVariable("tenancyId") Long tenancyId) {
		model.addAttribute("supplier", new User());
		model.addAttribute("tenancyId", tenancyId);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
		return "amap/admin/users/users-form";
	}
	
	@PostMapping("/users/delete/{userId}")
	public String usersHide(@PathVariable("userId") Long userId) {
		adminUserService.hideById(userId);
		return "redirect:../list";
	}
	
	@GetMapping("/users/generateFakes")
	public String usersAddFake(@PathVariable("tenancyId") Long tenancyId) {
		adminUserService.generateUsers(tenancyId);
		return "redirect:/users/users-list";
	}
	
	@GetMapping("/suppliers/list")
	public String suppliersList(Model model, @PathVariable("tenancyId") Long tenancyId) {
		List<User> suppliers = adminUserService.findSuppliers(tenancyId);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("tenancyId", tenancyId);
		return "amap/admin/users/suppliers-list";
	}
	

	@GetMapping("/suppliers/form")
	public String suppliersForm(Model model, @PathVariable("tenancyId") Long tenancyId) {
		model.addAttribute("supplier", new User());
		model.addAttribute("tenancyId", tenancyId);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
		return "amap/admin/users/suppliers-form";
	}
	

	@PostMapping("/suppliers/add")
	public String SuppliersAdd(@ModelAttribute("supplierDTO") SupplierDTO supplierDTO, @PathVariable("tenancyId") Long tenancyId) {
	    adminUserService.addTenancySupplier(supplierDTO, tenancyId);
	    return "redirect:/suppliers/list";
	}
	

	@GetMapping("/suppliers/delete/{userId}")
	public String suppliersHide(@PathVariable("userId") Long userId, @PathVariable("tenancyId") Long tenancyId) {
		adminUserService.hideById(userId);
		return "redirect:../list";
	}
	

	@GetMapping("/suppliers/edit/{userId}")
	public String suppliersEdit(@PathVariable("userId") Long userId, Model model, @PathVariable("tenancyId") Long tenancyId) {
		User supplier = adminUserService.findById(userId);
		model.addAttribute("supplier", supplier);
		model.addAttribute("tenancyId", tenancyId);
		return "amap/admin/users/suppliers-edit";
	}
	
	@GetMapping("/suppliers/details/{userId}")
	public String suppliersDetails(@PathVariable("userId") Long userId, Model model, @PathVariable("tenancyId") Long tenancyId) {
	    User supplier = adminUserService.findById(userId);
	    model.addAttribute("user", supplier);
		model.addAttribute("tenancyId", tenancyId);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
	    return "amap/admin/users/suppliers-details";
	}
	
}
