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

import isika.p3.amappli.dto.amap.MembershipFeeDTO;
import isika.p3.amappli.dto.amap.UpdateProfileDTO;
import isika.p3.amappli.dto.amap.UserDTO;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amap.MemberShipService;
import isika.p3.amappli.service.amap.UserService;
import isika.p3.amappli.service.amappli.TenancyService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("amap/{tenancyAlias}")
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
        
        graphismService.setUpModel(alias, model);
        
        return "amap/amaplogin/signup-form";
    }

    // Page de confirmation après l'inscription réussie
    @GetMapping("/login-done")
    public String showLoginDone(@PathVariable("tenancyAlias") String alias, Model model) {
    	
    	Tenancy tenancy = tenancyService.getTenancyByAlias(alias);
    	
        model.addAttribute("tenancyAlias", alias);
        
        graphismService.setUpModel(alias, model);

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
         	
            graphismService.setUpModel(alias, model);

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
            
            graphismService.setUpModel(alias, model);

            return "amap/amaplogin/signup-form";
        }
    }


    
//    
//    // Afficher le formulaire de connexion
//    @GetMapping("/login")
//    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
//                                @PathVariable("tenancyAlias") String alias,
//                                Model model) {
//        LoginDTO loginDTO = new LoginDTO();
//        model.addAttribute("loginDTO", loginDTO);
//        model.addAttribute("tenancyAlias", alias);
//        if (error != null) {
//            model.addAttribute("error", "Email ou mot de passe incorrect.");
//        }
//        Tenancy tenancy = tenancyService.getTenancyByAlias(alias);
//        
//        // Ajouter les informations générales de la tenancy
//        model.addAttribute("tenancy", tenancy);
//        model.addAttribute("tenancyName", tenancy.getTenancyName());
//        model.addAttribute("tenancySlogan", tenancy.getTenancySlogan());
//        
//     // Ajouter les informations graphiques
//        Graphism graphism = tenancy.getGraphism();
//        String logoBase64 = graphism != null ? graphism.getLogoImg() : null;
//        String logoImgType = graphism != null ? graphism.getLogoImgType() : null;
//        model.addAttribute("logoBase64", logoBase64);
//        model.addAttribute("logoImgType", logoImgType);
//        
//        // Récupérer et ajouter les styles dynamiques via GraphismService
//        graphismService.setUpModel(alias, model);
//        
//        
//        
//        
//        return "amap/amaplogin/login";
//    }

    
    
    
    /**
     * Affiche le profil utilisateur.
     */
    @GetMapping("/account/my-profile")
    public String viewProfile(@PathVariable("tenancyAlias") String alias,
                              Model model) {
    	Long userId = graphismService.getUserIdFromContext();
        // Récupérer les informations de l'utilisateur
        UserDTO userDTO = userService.getUserProfile(userId);

        // Mapper les données de UserDTO vers UpdateProfileDTO
        UpdateProfileDTO updateProfileDTO = new UpdateProfileDTO();
        updateProfileDTO.setEmail(userDTO.getEmail());
        updateProfileDTO.setAddress(userDTO.getAddress());
        updateProfileDTO.setContactInfo(userDTO.getContactInfo());

        // Ajouter UpdateProfileDTO au modèle
        model.addAttribute("updateProfileDTO", updateProfileDTO);
        model.addAttribute("tenancyAlias", alias);

        graphismService.setUpModel(alias, model);

        return "amap/front/user-profile/profile";
    }
    
    

    /**
     * Met à jour le profil utilisateur.
     */
    @PostMapping("/account/profile")
    public String updateProfile(@PathVariable("tenancyAlias") String alias,
                                @ModelAttribute @Valid UpdateProfileDTO updateProfileDTO,
                                BindingResult bindingResult,
                                Model model) {
    	Long userId = graphismService.getUserIdFromContext();
        // Gestion des erreurs de validation
        if (bindingResult.hasErrors()) {
            model.addAttribute("updateProfileDTO", updateProfileDTO);
            StringBuilder errorMessages = new StringBuilder("Certains champs sont invalides : ");
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append(" ");
            });
            model.addAttribute("error", errorMessages.toString());
            model.addAttribute("tenancyAlias", alias);

    	        graphismService.setUpModel(alias, model);
            
            return "amap/front/user-profile/profile"; // Retourne à la vue du formulaire avec erreurs
        }

        try {
            // Appelle le service pour mettre à jour les informations utilisateur
            userService.updateUserProfile(userId, updateProfileDTO);
            model.addAttribute("success", "Profil mis à jour avec succès.");
        } catch (RuntimeException e) {
            // Gère les exceptions liées à la logique métier ou à la base de données
            model.addAttribute("error", e.getMessage());
        }

        graphismService.setUpModel(alias, model);
        
        // Ré-affiche le formulaire (avec ou sans succès/erreur)
        model.addAttribute("updateProfileDTO", updateProfileDTO);
        return "amap/front/user-profile/profile";
    }
    
    
    @GetMapping("/account/membership")
    public String viewMembership(@PathVariable("tenancyAlias") String alias,
                                 Model model) {
    	Long userId = graphismService.getUserIdFromContext();
        // Récupérer l'adhésion via le service
        MembershipFeeDTO membershipFeeDTO = MemberShipService.getMembershipForUser(userId);

        // Ajouter les données au modèle
        model.addAttribute("membershipFeeDTO", membershipFeeDTO);
        model.addAttribute("tenancyAlias", alias);
        graphismService.setUpModel(alias, model);

        return "amap/front/user-profile/membershipfee";
    }


}


