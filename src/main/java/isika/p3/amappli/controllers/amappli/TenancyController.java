package isika.p3.amappli.controllers.amappli;

import java.io.IOException;
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
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amappli.TenancyService;

@Controller
//@RequestMapping("")
public class TenancyController {

	@Autowired
	private TenancyService tenancyService;
	@Autowired
	private GraphismService graphismService;
	


	@GetMapping("/getall")
	public String getAllTenancies(Model model) {
		List<Tenancy> tenancies = tenancyService.getAllTenancies();
		model.addAttribute("tenancies", tenancies);
		System.out.println(tenancies.size());
		return "amappli/back/tenancy/tenancy-list";
	}
	
	@GetMapping("/{tenancyAlias}/home")
	public String getHomePageContentByAlias(@PathVariable("tenancyAlias") String alias, Model model) {
	    // Récupérer le contenu de la page d'accueil
	    HomePageContent homePageContent = tenancyService.getHomePageContentByTenancyAlias(alias);
	    Tenancy tenancy = tenancyService.getTenancyByAlias(alias);

	    // Vérifier si les données existent
	    if (homePageContent != null) {
	    	  // Ajouter les informations générales de la tenancy
	        model.addAttribute("tenancy", tenancy);
	        model.addAttribute("tenancyName", tenancy.getTenancyName());
	        model.addAttribute("tenancySlogan", tenancy.getTenancySlogan());

	        // Récupérer les informations de graphisme (logo, etc.)
	        Graphism graphism = tenancy.getGraphism();
	        String logoBase64 = graphism != null ? graphism.getLogoImg() : null;
	        String logoImgType = graphism != null ? graphism.getLogoImgType() : null;
	        model.addAttribute("logoBase64", logoBase64);
	        model.addAttribute("logoImgType", logoImgType);

	        // Ajouter l'adresse de la tenancy au modèle
	        Address address = tenancy.getAddress();
	        model.addAttribute("addressLine1", address != null ? address.getLine1() : null);
	        model.addAttribute("addressLine2", address != null ? address.getLine2() : null);
	        model.addAttribute("addressPostCode", address != null ? address.getPostCode() : null);
	        model.addAttribute("addressCity", address != null ? address.getCity() : null);

	        // Récupérer et filtrer les ContentBlocks pour la page d'accueil
	        List<ContentBlock> contentBlocks = homePageContent.getContents();
	        
	        // Filtrer les ContentBlocks avec isValue == true
	        List<ContentBlock> valueBlocks = contentBlocks.stream()
	            .filter(ContentBlock::isValue)
	            .collect(Collectors.toList());

	        model.addAttribute("valueBlocks", valueBlocks);

	        // Block de présentation
	        ContentBlock presentationBlock = contentBlocks.stream()
	            .filter(block -> !block.isValue())
	            .findFirst()
	            .orElse(null);

	        model.addAttribute("presentationBlock", presentationBlock);
	        
	        // Récupérer les informations de style via GraphismService
	        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
	        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
	        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
	        model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));

	    } else {
	        model.addAttribute("message", "Page d'accueil non trouvée.");
	    }

	    return "amap/front/homePage"; // Vue JSP correspondante
	}


	
	
	

	@GetMapping("/{id}")
	public String getTenancyById(@PathVariable Long id, Model model) {
		Tenancy tenancy = tenancyService.getTenancyById(id);
		model.addAttribute("tenancy", tenancy);
		  // Ajouter les informations générales de la tenancy
        model.addAttribute("tenancy", tenancy);
        model.addAttribute("tenancyName", tenancy.getTenancyName());
        model.addAttribute("tenancySlogan", tenancy.getTenancySlogan());
		return "tenancy-details";
	}
	
	@GetMapping("/{tenancyAlias}")
	public String getTenancyByAlias(@PathVariable String alias, Model model) {
	    // Récupérer la tenancy via son alias
	    Tenancy tenancy = tenancyService.getTenancyByAlias(alias);
	    
	    if (tenancy != null) {
	        // Ajouter la tenancy au modèle
	        model.addAttribute("tenancy", tenancy);
	        
	        // Ajouter les informations générales de la tenancy
	        model.addAttribute("tenancy", tenancy);
	        model.addAttribute("tenancyName", tenancy.getTenancyName());
	        model.addAttribute("tenancySlogan", tenancy.getTenancySlogan());

	        // Récupérer les informations dynamiques liées au graphisme
	        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
	        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
	        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
	        model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
	        
	        // Retourner la vue pour afficher les détails de la tenancy
	        return "tenancy-details";
	    } else {
	        // Si la tenancy n'est pas trouvée, ajouter un message d'erreur
	        model.addAttribute("message", "Tenancy not found with alias: " + alias);
	        return "error";
	    }
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
	
	
	@GetMapping("/{tenancyAlias}/amapPage")
	public String amapPage(@PathVariable("tenancyAlias") String alias, Model model) {
	    // Récupérer la tenancy
	    Tenancy tenancy = tenancyService.getTenancyByAlias(alias);

	    if (tenancy != null) {
	        // Ajouter les informations générales de la tenancy
	        model.addAttribute("tenancy", tenancy);
	        model.addAttribute("tenancyName", tenancy.getTenancyName());
	        model.addAttribute("tenancySlogan", tenancy.getTenancySlogan());

	        // Ajouter les informations graphiques
	        Graphism graphism = tenancy.getGraphism();
	        String logoBase64 = graphism != null ? graphism.getLogoImg() : null;
	        String logoImgType = graphism != null ? graphism.getLogoImgType() : null;
	        model.addAttribute("logoBase64", logoBase64);
	        model.addAttribute("logoImgType", logoImgType);

	        // Ajouter les styles et polices
	        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
	        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
	        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
	        model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
	    } else {
	        model.addAttribute("message", "Tenancy non trouvée.");
	    }

	    return "amap/front/amapPage"; // Vue JSP correspondante
	}


	
	@GetMapping("/{tenancyAlias}/contact")
	public String contactPage(@PathVariable("tenancyAlias") String alias, Model model) {
	    // Récupérer la tenancy
	    Tenancy tenancy = tenancyService.getTenancyByAlias(alias);

	    if (tenancy != null) {
	        // Ajouter les informations générales de la tenancy
	        model.addAttribute("tenancy", tenancy);
	        model.addAttribute("tenancyName", tenancy.getTenancyName());
	        model.addAttribute("tenancySlogan", tenancy.getTenancySlogan());

	        // Ajouter les informations graphiques
	        Graphism graphism = tenancy.getGraphism();
	        String logoBase64 = graphism != null ? graphism.getLogoImg() : null;
	        String logoImgType = graphism != null ? graphism.getLogoImgType() : null;
	        model.addAttribute("logoBase64", logoBase64);
	        model.addAttribute("logoImgType", logoImgType);

	        // Ajouter les informations de contact et d'adresse
	        Address address = tenancy.getAddress();
	        model.addAttribute("addressLine1", address != null ? address.getLine1() : null);
	        model.addAttribute("addressLine2", address != null ? address.getLine2() : null);
	        model.addAttribute("addressPostCode", address != null ? address.getPostCode() : null);
	        model.addAttribute("addressCity", address != null ? address.getCity() : null);
	        model.addAttribute("email", tenancy.getEmail());
	        model.addAttribute("phoneNumber", tenancy.getContactInfo() != null ? tenancy.getContactInfo().getPhoneNumber() : null);

	        // Ajouter les styles et polices
	        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
	        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
	        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
	        model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
	    } else {
	        model.addAttribute("message", "Tenancy non trouvée.");
	    }

	    return "amap/front/contactPage"; // Vue JSP correspondante
	}




}
