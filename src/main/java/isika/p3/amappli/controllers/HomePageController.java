package isika.p3.amappli.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import isika.p3.amappli.entities.tenancy.HomePageContent;
import isika.p3.amappli.service.HomePageContentService;

@Controller
@RequestMapping("/")
public class HomePageController {
	
	   @Autowired
	    private HomePageContentService homePageContentService;

//	    @GetMapping("/tenancyHome")
//	    public String displayHomePage(Model model) {
//	        List<HomePageContent> contentList = homePageContentService.findAll();
//	        model.addAttribute("contents", contentList);
//	        return "tenancyHome";
//	    }


	    // Page d'accueil
	    @GetMapping
	    public String getHomePage(Model model) {
	        HomePageContent homePageContent = homePageContentService.getCurrentHomePageContent();
	        model.addAttribute("homePageContent", homePageContent);
	        return "homePage"; // La vue homePage.jsp
	    }

	    // Formulaire pour mettre à jour le sous-titre de la page d'accueil
	    @PostMapping("/home/updateTitle")
	    public String updateHomePageTitle(@RequestParam("homePageContentId") Long homePageContentId,
	                                      @RequestParam("newSubTitle") String newSubTitle,
	                                      Model model) {
	        HomePageContent updatedHomePageContent = homePageContentService.updateHomePageTitle(homePageContentId, newSubTitle);
	        if (updatedHomePageContent != null) {
	            model.addAttribute("homePageContent", updatedHomePageContent);
	            model.addAttribute("message", "Titre mis à jour avec succès!");
	        } else {
	            model.addAttribute("message", "Erreur lors de la mise à jour du titre.");
	        }
	        return "homePage"; // Retourne à la vue homePage.jsp
	    }
}
