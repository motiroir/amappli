package isika.p3.amappli.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.service.UserService;

@Controller
@RequestMapping("/tenancies/{tenancyId}/amap/amaplogin")
public class UserController {

    @Autowired
    private UserService userService;

    // Afficher le formulaire d'inscription
    @GetMapping("/signup")
    public String showRegistrationForm(@PathVariable("tenancyId") Long tenancyId, Model model) {
        User user = new User();
        model.addAttribute("user", user);
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
    public String registerUser(@PathVariable("tenancyId") Long tenancyId, @ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user, tenancyId);
            return "redirect:/tenancies/" + tenancyId + "/amap/amaplogin/login-done";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", user);
            return "amap/amaplogin/signup-form";
        }
    }
}
