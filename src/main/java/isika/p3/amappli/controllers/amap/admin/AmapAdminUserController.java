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


@Controller
@RequestMapping("tenancies/{tenancyId}/amap/admin/backoffice")
public class AmapAdminUserController {
    
	@Autowired
	private AmapAdminUserService adminUserService;
	
	private Long tenancyId;
	
	public AmapAdminUserController(AmapAdminUserService adminUserService, @PathVariable("tenancyId") Long tenancyId) {
		this.adminUserService = adminUserService;
		this.tenancyId = tenancyId;
	}
	
	@GetMapping("/users/list")
	public String listUsers(Model model, @PathVariable("tenancyId") Long tenancyId) {
		List<User> users = adminUserService.findAll(tenancyId);
		model.addAttribute("users", users);
		return "amap/admin/users/users-list";
	}
	
	@GetMapping("/user/generateFakes")
	public String addFakeUsers() {
		adminUserService.generateUsers();
		return "redirect:/users/list";
	}
	
	@GetMapping("/suppliers/list")
	public String listSuppliers(Model model) {
		Long tenancyId = this.tenancyId;
		List<User> suppliers = adminUserService.findSuppliers(tenancyId);
		model.addAttribute("suppliers", suppliers);
		return "amap/admin/users/suppliers-list";
	}
	

	@GetMapping("/suppliers/form")
	public String showForm(Model model) {
		model.addAttribute("supplier", new User());
		return "amap/admin/users/suppliers-form";
	}
	

	@PostMapping("/suppliers/add")
	public String addContract(@ModelAttribute("supplierDTO") SupplierDTO supplierDTO) {
		Long tenancyId = this.tenancyId;
	    adminUserService.addTenancyUser(supplierDTO, tenancyId);
	    return "redirect:/suppliers/list";
	}
	

	@PostMapping("/suppliers/delete/{userId}")
	public String deleteContract(@PathVariable("userId") Long userId) {
		adminUserService.hideById(userId);
		return "redirect:/suppliers/list";
	}
	

	@GetMapping("/suppliers/edit/{userId}")
	public String editUserForm(@PathVariable("userId") Long userId, Model model) {
		User supplier = adminUserService.findById(userId);
		model.addAttribute("supplier", supplier);
		return "amap/admin/users/supplier-edit";
	}
	
	@GetMapping("/suppliers/details/{userId}")
	public String viewUserDetail(@PathVariable("userId") Long userId, Model model) {
	    User supplier = adminUserService.findById(userId);
	    model.addAttribute("supplier", supplier);
	    return "amap/admin/users/contract-detail";
	}
	
}
