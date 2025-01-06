package isika.p3.amappli.controllers.amap;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import isika.p3.amappli.dto.amap.UserDTO;
import isika.p3.amappli.dto.amappli.LoginDTO;
import isika.p3.amappli.entities.tenancy.Graphism;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amap.UserService;
import isika.p3.amappli.service.amappli.TenancyService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("{tenancyAlias}/amap/amaplogin")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TenancyService tenancyService;
    @Autowired
    private GraphismService graphismService;

    // Afficher le formulaire d'inscription
    @GetMapping("/signup")
    public String showRegistrationForm(@PathVariable("tenancyAlias") String alias, Model model) {
    	Tenancy tenancy = tenancyService.getTenancyByAlias(alias);
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("tenancyAlias", alias);
        
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
        
        // Récupérer et ajouter les styles dynamiques via GraphismService
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
        
        return "amap/amaplogin/signup-form";
    }

    // Page de confirmation après l'inscription réussie
    @GetMapping("/login-done")
    public String showLoginDone(@PathVariable("tenancyAlias") String alias, Model model) {
    	
    	Tenancy tenancy = tenancyService.getTenancyByAlias(alias);
    	
        model.addAttribute("tenancyAlias", alias);
     // Ajouter les informations graphiques
        Graphism graphism = tenancy.getGraphism();
        String logoBase64 = graphism != null ? graphism.getLogoImg() : null;
        String logoImgType = graphism != null ? graphism.getLogoImgType() : null;
        model.addAttribute("logoBase64", logoBase64);
        model.addAttribute("logoImgType", logoImgType);
        
        // Ajouter les informations générales de la tenancy
        model.addAttribute("tenancy", tenancy);
        model.addAttribute("tenancyName", tenancy.getTenancyName());
        model.addAttribute("tenancySlogan", tenancy.getTenancySlogan());
        
        // Récupérer et ajouter les styles dynamiques via GraphismService
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
        return "amap/amaplogin/login-done";
    }

 // Enregistrer l'utilisateur
    @PostMapping("/signup")
    public String registerUser(@PathVariable("tenancyAlias") String alias,
                               @ModelAttribute @Valid UserDTO userDTO, 
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            // Collecter les erreurs spécifiques aux champs
            Map<String, String> fieldErrors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                    FieldError::getField,
                    FieldError::getDefaultMessage
                ));

            model.addAttribute("errors", fieldErrors);
            model.addAttribute("userDTO", userDTO); // Conserver les données saisies
            
         	Tenancy tenancy = tenancyService.getTenancyByAlias(alias);
         	
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

            // Récupérer et ajouter les styles dynamiques via GraphismService
            model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
            model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
            model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
            model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
            

            return "amap/amaplogin/signup-form";
        }

        try {
            Long tenancyId = tenancyService.getTenancyByAlias(alias).getTenancyId();
            userService.addTenancyUser(userDTO, tenancyId);
            return "redirect:/tenancies/" + alias + "/amap/amaplogin/login-done";
        } catch (RuntimeException e) {
            // Gérer les erreurs générales
            model.addAttribute("error", e.getMessage());
            model.addAttribute("userDTO", userDTO);
            
         	Tenancy tenancy = tenancyService.getTenancyByAlias(alias);
            
            // Ajouter les informations graphiques
            Graphism graphism = tenancy.getGraphism();
            String logoBase64 = graphism != null ? graphism.getLogoImg() : null;
            String logoImgType = graphism != null ? graphism.getLogoImgType() : null;
            model.addAttribute("logoBase64", logoBase64);
            model.addAttribute("logoImgType", logoImgType);


            // Récupérer et ajouter les styles dynamiques via GraphismService
            model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
            model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
            model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
            model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));

            return "amap/amaplogin/signup-form";
        }
    }


    
    
    // Afficher le formulaire de connexion
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @PathVariable("tenancyAlias") String alias,
                                Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("loginDTO", loginDTO);
        model.addAttribute("tenancyAlias", alias);
        if (error != null) {
            model.addAttribute("error", "Email ou mot de passe incorrect.");
        }
        Tenancy tenancy = tenancyService.getTenancyByAlias(alias);
        
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
        
        // Récupérer et ajouter les styles dynamiques via GraphismService
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));
        
        return "amap/amaplogin/login";
    }

    // Traitement de la connexion
    // @PostMapping("/login")
    // public String loginUser(@PathVariable("tenancyId") Long tenancyId, 
    //                         @Valid LoginDTO loginDTO, 
    //                         BindingResult bindingResult, 
    //                         Model model) {
    //     if (bindingResult.hasErrors()) {
    //         return "amap/amaplogin/login";
    //     }

    //     try {
    //         User user = userService.authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());
    //         // Authentification réussie, 
    //         return "redirect:/tenancies/" + tenancyId + "/home"; 
    //     } catch (RuntimeException e) {
    //         model.addAttribute("error", e.getMessage());
    //         return "amap/amaplogin/login";
    //     }
    // }
    
    

}


