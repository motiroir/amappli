package isika.p3.amappli.controllers.amappli;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.entities.tenancy.ContentBlock;
import isika.p3.amappli.entities.tenancy.Graphism;
import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.service.amappli.TenancyService;

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
		return "amappli/back/tenancy/tenancy-list";
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
            model.addAttribute("tenancySlogan", tenancy.getTenancySlogan());
            Graphism graphism = tenancy.getGraphism();
            model.addAttribute("logoImg", graphism != null ? graphism.getLogoImg() : null);
            
            Address address = tenancy.getAddress(); 
            model.addAttribute("addressLine1", address != null ? address.getLine1() : null);
            model.addAttribute("addressLine2", address != null ? address.getLine2() : null);
            model.addAttribute("addressPostCode", address != null ? address.getPostCode() : null);
            model.addAttribute("addressCity", address != null ? address.getCity() : null);
            
           
            
            List<ContentBlock> contentBlocks = homePageContent.getContents();
            
            // Filtrer les ContentBlocks avec isValue == true
            List<ContentBlock> valueBlocks = contentBlocks.stream()
                .filter(ContentBlock::isValue)  // Récupérer uniquement les ContentBlock avec isValue == true
                .collect(Collectors.toList());
            
            // Ajouter les ContentBlock à afficher au modèle
            model.addAttribute("valueBlocks", valueBlocks); // Liste des ContentBlock avec isValue = true

            // Récupérer et ajouter le block de présentation
            ContentBlock presentationBlock = contentBlocks.stream()
                .filter(block -> !block.isValue()) // Block de présentation (isValue == false)
                .findFirst()
                .orElse(null); // Si aucun block n'est trouvé, mettre null

            model.addAttribute("presentationBlock", presentationBlock); // Ajouter le block de présentation
            
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
