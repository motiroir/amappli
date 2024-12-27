package isika.p3.amappli.controllers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.service.PermissionService;

@Controller
@RequestMapping("/sectest")
public class SecurityController {
    
    // private final PermissionService permissionService;


    // public SecurityController(PermissionService permissionService) {
    //     this.permissionService = permissionService;
    // }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/login")
    public String goToLogin(Model model){
        // permissionService.createPermissions();
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

    @GetMapping("/showpermissions")
    public String showLoggedUserPermissions(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal().toString());
        model.addAttribute("principal", authentication.getPrincipal());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        model.addAttribute("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
       return "secexamples/showloggeduserpermissions"; 
    }
}
