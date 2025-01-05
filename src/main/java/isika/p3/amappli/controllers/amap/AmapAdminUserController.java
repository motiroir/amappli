package isika.p3.amappli.controllers.amap;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import isika.p3.amappli.dto.amap.ContractDTO;
import isika.p3.amappli.dto.amap.UpdateUserDTO;
import isika.p3.amappli.dto.amap.UserDTO;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.service.amap.AmapAdminUserService;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amap.RoleService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


@Controller
@RequestMapping("{tenancyAlias}/backoffice")
public class AmapAdminUserController {
    
	@Autowired
	private AmapAdminUserService adminUserService;
	
    @Autowired
    private final RoleService roleService;
    
    @Autowired
    private final GraphismService graphismService;
	
	public AmapAdminUserController(AmapAdminUserService adminUserService, RoleService roleService, GraphismService graphismService) {
		this.adminUserService = adminUserService;
		this.roleService = roleService;
		this.graphismService = graphismService;
	}

	@GetMapping("/users/list")
	public String usersList(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		List<User> users = adminUserService.findAll(tenancyAlias);
		model.addAttribute("users", users);
		model.addAttribute("tenancyAlias", tenancyAlias);
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(tenancyAlias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(tenancyAlias));
        model.addAttribute("tenancy", graphismService.getTenancyByAlias(tenancyAlias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(tenancyAlias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(tenancyAlias));
		return "amap/back/users/users-list";
	}
	
	@GetMapping("/users/details/{userId}")
	public String usersDetails(@PathVariable("userId") Long userId, Model model, @PathVariable("tenancyAlias") String tenancyAlias, @ModelAttribute("message") String message) {
		User user = adminUserService.findById(userId);
		model.addAttribute("user", user);
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(tenancyAlias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(tenancyAlias));
        model.addAttribute("tenancy", graphismService.getTenancyByAlias(tenancyAlias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(tenancyAlias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(tenancyAlias));
		return "amap/back/users/users-details";
	}
	
	@PostMapping("/users/update")
	public String usersUpdate(@Valid @ModelAttribute("user") UpdateUserDTO user, BindingResult result, Model model, RedirectAttributes ra) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(user);
		for (ConstraintViolation<UpdateUserDTO> violation : violations) 
        {
			String path = violation.getPropertyPath() + "";
            if (path.contains(".")) {
            	ra.addFlashAttribute(path.substring(path.indexOf('.') +1), violation.getMessage());
			} else {
				ra.addFlashAttribute(path, violation.getMessage());
			}
        } 
			
		if (result.hasErrors()) {
			ra.addFlashAttribute("message", "Le formulaire n'a pas été correctement rempli");
			return "redirect:details/" + user.getUserId();
		}
		
		adminUserService.updateUser(user);
		return "redirect:list";
	}
	
	@GetMapping("/users/form")
	public String usersForm(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		model.addAttribute("user", new User());
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(tenancyAlias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(tenancyAlias));
        model.addAttribute("tenancy", graphismService.getTenancyByAlias(tenancyAlias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(tenancyAlias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(tenancyAlias));
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
		return "redirect:list";
	}
	
	@GetMapping("/suppliers/list")
	public String suppliersList(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		List<User> suppliers = adminUserService.findSuppliers(tenancyAlias);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("tenancyAlias", tenancyAlias);
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(tenancyAlias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(tenancyAlias));
        model.addAttribute("tenancy", graphismService.getTenancyByAlias(tenancyAlias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(tenancyAlias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(tenancyAlias));
		return "amap/back/users/suppliers-list";
	}
	

	@GetMapping("/suppliers/form")
	public String suppliersForm(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
		model.addAttribute("supplier", new User());
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(tenancyAlias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(tenancyAlias));
        model.addAttribute("tenancy", graphismService.getTenancyByAlias(tenancyAlias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(tenancyAlias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(tenancyAlias));
		return "amap/back/users/suppliers-form";
	}
	

	@PostMapping("/suppliers/add")
	public String SuppliersAdd(@ModelAttribute("supplierDTO") UpdateUserDTO supplierDTO, @PathVariable("tenancyAlias") String tenancyAlias) {
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
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(tenancyAlias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(tenancyAlias));
        model.addAttribute("tenancy", graphismService.getTenancyByAlias(tenancyAlias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(tenancyAlias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(tenancyAlias));
		return "amap/back/users/suppliers-edit";
	}
	
	@GetMapping("/suppliers/details/{userId}")
	public String suppliersDetails(@PathVariable("userId") Long userId, Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
	    User supplier = adminUserService.findById(userId);
	    model.addAttribute("user", supplier);
		model.addAttribute("tenancyAlias", tenancyAlias);
		model.addAttribute("allRoles" , this.roleService.findAllRoles());
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(tenancyAlias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(tenancyAlias));
        model.addAttribute("tenancy", graphismService.getTenancyByAlias(tenancyAlias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(tenancyAlias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(tenancyAlias));
	    return "amap/back/users/suppliers-details";
	}
	
}
