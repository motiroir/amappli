package isika.p3.amappli.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import isika.p3.amappli.dto.LoginDTO;
import isika.p3.amappli.dto.UserDTO;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.service.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tenancies/{tenancyId}/amap/amaplogin")
public class UserController {

    @Autowired
    private UserService userService;

    // Afficher le formulaire d'inscription
    @GetMapping("/signup")
    public String showRegistrationForm(@PathVariable("tenancyId") Long tenancyId, Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("tenancyId", tenancyId);
        return "amap/amaplogin/signup-form";
    }

    // Page de confirmation après l'inscription réussie
    @GetMapping("/login-done")
    public String showLoginDone(@PathVariable("tenancyId") Long tenancyId, Model model) {
        model.addAttribute("tenancyId", tenancyId);
        return "amap/amaplogin/login-done";
    }

    // Enregistrer l'utilisateur
    @PostMapping("/signup")
    public String registerUser(@PathVariable("tenancyId") Long tenancyId,
                               @ModelAttribute @Valid UserDTO userDTO, 
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
           
            model.addAttribute("userDTO", userDTO);
            return "amap/amaplogin/signup-form";
        }
        
        try {
            userService.addTenancyUser(userDTO, tenancyId);
            return "redirect:/tenancies/" + tenancyId + "/amap/amaplogin/login-done";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "amap/amaplogin/signup-form";
        }
    }
    
    
    // Afficher le formulaire de connexion
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, 
                                @PathVariable("tenancyId") Long tenancyId, 
                                Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("loginDTO", loginDTO);
        model.addAttribute("tenancyId", tenancyId);
        if (error != null) {
            model.addAttribute("error", "Email ou mot de passe incorrect.");
        }
        return "amap/amaplogin/login";
    }

    // Traitement de la connexion
    @PostMapping("/login")
    public String loginUser(@PathVariable("tenancyId") Long tenancyId, 
                            @Valid LoginDTO loginDTO, 
                            BindingResult bindingResult, 
                            Model model) {
        if (bindingResult.hasErrors()) {
            return "amap/amaplogin/login";
        }

        try {
            User user = userService.authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());
            // Authentification réussie, 
            return "redirect:/tenancies/" + tenancyId + "/home"; 
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "amap/amaplogin/login";
        }
    }
    
}


