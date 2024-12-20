package isika.p3.amappli.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sectest")
public class SecurityController {
    
    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/login")
    public String goToLogin(Model model){
        return "secexamples/login";
    }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/logout")
    public String goToLogout(Model model){
        return "secexamples/logout";
    }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/needanyauth")
    public String goToAuthTest(Model model){
        return "secexamples/needanyauth";
    }
}
