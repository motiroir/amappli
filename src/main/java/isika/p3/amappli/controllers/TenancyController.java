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

import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.service.TenancyService;

@Controller
@RequestMapping("/tenancies")
public class TenancyController {

	@Autowired
	private TenancyService tenancyService;

	@GetMapping("/test-add")
	public String addTestTenancies(Model model) {
		// Appeler la méthode pour ajouter les tenancies de test
		tenancyService.addTestTenancies();
		model.addAttribute("message", "Test tenancies added successfully!");
		return "amappli/tenancy/tenancy-list";
	}

	@GetMapping
	public String getAllTenancies(Model model) {
		List<Tenancy> tenancies = tenancyService.getAllTenancies();
		model.addAttribute("tenancies", tenancies);
		System.out.println(tenancies.size());
		return "amappli/tenancy/tenancy-list";
	}
	
	@GetMapping("/{id}/home")
    public String getHomePageContent(@PathVariable("id") Long id, Model model) {
        HomePageContent homePageContent = tenancyService.getHomePageContentByTenancyId(id);
        
        Tenancy tenancy = tenancyService.getTenancyById(id);
        
        if (homePageContent != null) {
            model.addAttribute("homePageContent", homePageContent);
            model.addAttribute("tenancyName", tenancy.getTenancyName()); 
        } else {
            model.addAttribute("message", "Page d'accueil non trouvée.");
        }
        
        return "amap/homePage"; 
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
