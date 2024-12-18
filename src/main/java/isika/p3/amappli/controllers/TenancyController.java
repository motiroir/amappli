package isika.p3.amappli.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.service.TenancyService;

@Controller
@RequestMapping("/tenancies")
public class TenancyController {

	
	@Autowired
	private  TenancyService tenancyService;
	
	   @GetMapping("/test-add")
	    public String addTestTenancies(Model model) {
	        // Appeler la méthode pour ajouter les tenancies de test
	        tenancyService.addTestTenancies();
	        model.addAttribute("message", "Test tenancies added successfully!");
	        return "tenancy/tenancy-list"; 
	    }
	
	
	   @GetMapping
	    public String getAllTenancies(Model model) {
	        List<Tenancy> tenancies = tenancyService.getAllTenancies();
	        model.addAttribute("tenancies", tenancies);
	        return "tenancy/tenancy-list"; 
	    }	
		
	   @GetMapping("/{id}")
	    public String getTenancyById(@PathVariable Long id, Model model) {
	        Tenancy tenancy = tenancyService.getTenancyById(id);
	        model.addAttribute("tenancy", tenancy);
	        return "tenancy-details";
	    }

	    @GetMapping("/create")
	    public String showCreateForm(Model model) {
	        model.addAttribute("tenancy", new Tenancy());
	        return "tenancy-form";
	    }

	    @PostMapping("/create")
	    public String createTenancy(@ModelAttribute("tenancy") Tenancy tenancy) {
	        tenancyService.createTenancy(tenancy);
	        return "redirect:/tenancies";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteTenancy(@PathVariable Long id) {
	        tenancyService.deleteTenancy(id);
	        return "redirect:/tenancies";
	    }
		
	
}
